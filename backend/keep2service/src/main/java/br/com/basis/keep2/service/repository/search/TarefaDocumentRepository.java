package br.com.basis.keep2.service.repository.search;

import br.com.basis.keep2.service.domain.document.TarefaDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TarefaDocumentRepository extends ElasticsearchRepository<TarefaDocument, Long> {
}
