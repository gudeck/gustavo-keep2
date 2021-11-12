package br.com.basis.keep2.service.service.elasticsearch;

import br.com.basis.keep2.service.domain.document.ResponsavelDocument;
import br.com.basis.keep2.service.repository.ResponsavelRepository;
import br.com.basis.keep2.service.repository.search.ResponsavelDocumentRepository;
import br.com.basis.keep2.service.service.event.ResponsavelEvent;
import br.com.basis.keep2.service.service.filter.ResponsavelFilter;
import br.com.basis.keep2.service.service.mapper.document.ResponsavelDocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Service
public class ResponsavelDocumentService {

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelDocumentMapper responsavelDocumentMapper;
    private final ResponsavelDocumentRepository responsavelDocumentRepository;

    public Page<ResponsavelDocument> findAll(ResponsavelFilter responsavelFilter, Pageable pageable) {
        return responsavelDocumentRepository.search(responsavelFilter.getQuery(), pageable);
    }

    @TransactionalEventListener(fallbackExecution = true)
    public void indexResponsavel(ResponsavelEvent event) {
        var idEvent = event.getId();

        responsavelRepository.findById(idEvent)
            .map(responsavelDocumentMapper::toDto)
            .ifPresentOrElse(
                responsavelDocumentRepository::save,
                () -> responsavelDocumentRepository.deleteById(idEvent));
    }

}
