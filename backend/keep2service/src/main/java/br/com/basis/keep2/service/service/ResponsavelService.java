package br.com.basis.keep2.service.service;

import br.com.basis.keep2.service.repository.ResponsavelRepository;
import br.com.basis.keep2.service.service.dto.ResponsavelDTO;
import br.com.basis.keep2.service.service.event.ResponsavelEvent;
import br.com.basis.keep2.service.service.mapper.ResponsavelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelMapper responsavelMapper;
    private final ResponsavelRepository responsavelRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void deleteById(Long idResponsavel) {
        responsavelRepository.deleteById(idResponsavel);
        applicationEventPublisher.publishEvent(new ResponsavelEvent(idResponsavel));
    }

    @Transactional(readOnly = true)
    public ResponsavelDTO findById(Long idResponsavel) {
        return responsavelRepository.findById(idResponsavel)
            .map(responsavelMapper::toDto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "registro.nao-encontrado"));
    }

    @Transactional
    public ResponsavelDTO save(ResponsavelDTO responsavelDTO) {
        var responsavel = responsavelRepository.save(responsavelMapper.toEntity(responsavelDTO));
        applicationEventPublisher.publishEvent(new ResponsavelEvent(responsavel.getId()));
        return responsavelMapper.toDto(responsavel);
    }

}
