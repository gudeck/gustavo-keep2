package br.com.basis.keep2.service.service;

import br.com.basis.keep2.service.repository.TarefaRepository;
import br.com.basis.keep2.service.service.dto.TarefaDTO;
import br.com.basis.keep2.service.service.event.TarefaEvent;
import br.com.basis.keep2.service.service.event.TarefaMailEvent;
import br.com.basis.keep2.service.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaMapper tarefaMapper;
    private final TarefaRepository tarefaRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void deleteById(Long idTarefa) {
        tarefaRepository.deleteById(idTarefa);
        applicationEventPublisher.publishEvent(new TarefaEvent(idTarefa));
    }

    @Transactional(readOnly = true)
    public TarefaDTO findById(Long idTarefa) {
        return tarefaRepository.findById(idTarefa)
            .map(tarefaMapper::toDto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "registro.nao-encontrado"));
    }

    @Transactional
    public TarefaDTO save(TarefaDTO tarefaDTO) {
        var tarefa = tarefaRepository.save(tarefaMapper.toEntity(tarefaDTO));
        applicationEventPublisher.publishEvent(new TarefaEvent(tarefa.getId()));
        applicationEventPublisher.publishEvent(new TarefaMailEvent(tarefa.getId()));
        return tarefaMapper.toDto(tarefa);
    }

}
