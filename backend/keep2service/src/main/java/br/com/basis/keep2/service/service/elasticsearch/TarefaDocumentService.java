package br.com.basis.keep2.service.service.elasticsearch;

import br.com.basis.keep2.service.domain.document.TarefaDocument;
import br.com.basis.keep2.service.repository.TarefaRepository;
import br.com.basis.keep2.service.repository.search.TarefaDocumentRepository;
import br.com.basis.keep2.service.service.event.TarefaEvent;
import br.com.basis.keep2.service.service.filter.TarefaFilter;
import br.com.basis.keep2.service.service.mapper.document.TarefaDocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Service
public class TarefaDocumentService {

    private final TarefaRepository tarefaRepository;
    private final TarefaDocumentMapper tarefaDocumentMapper;
    private final TarefaDocumentRepository tarefaDocumentRepository;

    public Page<TarefaDocument> findAll(TarefaFilter tarefaFilter, Pageable pageable) {
        return tarefaDocumentRepository.search(tarefaFilter.getQuery(), pageable);
    }

    @TransactionalEventListener(fallbackExecution = true)
    public void indexTarefa(TarefaEvent event) {
        var idEvent = event.getId();

        tarefaRepository.findById(idEvent)
            .map(tarefaDocumentMapper::toDto)
            .ifPresentOrElse(
                tarefaDocumentRepository::save,
                () -> tarefaDocumentRepository.deleteById(idEvent));
    }

}
