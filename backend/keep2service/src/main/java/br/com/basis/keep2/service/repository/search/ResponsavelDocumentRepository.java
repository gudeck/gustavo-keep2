package br.com.basis.keep2.service.repository.search;

import br.com.basis.keep2.service.domain.document.ResponsavelDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ResponsavelDocumentRepository extends ElasticsearchRepository<ResponsavelDocument, Long> {
}
