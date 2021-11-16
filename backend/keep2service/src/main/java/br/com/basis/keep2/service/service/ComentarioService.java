package br.com.basis.keep2.service.service;

import br.com.basis.keep2.service.repository.ComentarioRepository;
import br.com.basis.keep2.service.service.dto.ComentarioDTO;
import br.com.basis.keep2.service.service.mapper.ComentarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioMapper comentarioMapper;
    private final ComentarioRepository comentarioRepository;

    @Transactional
    public void deleteById(Long idComentario) {
        comentarioRepository.deleteById(idComentario);
    }

    @Transactional(readOnly = true)
    public List<ComentarioDTO> findByIdTarefa(Long idTarefa) {
        return comentarioRepository.findAllByTarefaId(idTarefa).stream()
            .map(comentarioMapper::toDto)
            .collect(toList());
    }

    @Transactional
    public ComentarioDTO save(Long idTarefa, ComentarioDTO comentarioDTO) {
        comentarioDTO.setIdTarefa(idTarefa);

        var comentario = comentarioMapper.toEntity(comentarioDTO);
        return comentarioMapper.toDto(comentarioRepository.save(comentario));
    }

}
