package br.com.basis.keep2.service.config;

import br.com.basis.keep2.service.repository.ResponsavelRepository;
import br.com.basis.keep2.service.repository.TarefaRepository;
import br.com.basis.keep2.service.repository.search.ResponsavelDocumentRepository;
import br.com.basis.keep2.service.repository.search.TarefaDocumentRepository;
import br.com.basis.keep2.service.service.elasticsearch.IndexadorComMapper;
import br.com.basis.keep2.service.service.mapper.document.ResponsavelDocumentMapper;
import br.com.basis.keep2.service.service.mapper.document.TarefaDocumentMapper;
import br.gov.nuvem.comum.microsservico.service.reindex.Indexador;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@Configuration
@RequiredArgsConstructor
public class IndexadorConfiguration {

    private final ElasticsearchOperations elasticsearchOperations;

    private final TarefaRepository tarefaRepository;
    private final TarefaDocumentMapper tarefaDocumentMapper;
    private final TarefaDocumentRepository tarefaDocumentRepository;

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelDocumentMapper responsavelDocumentMapper;
    private final ResponsavelDocumentRepository responsavelDocumentRepository;

    @Bean
    public Indexador indexadorResponsavel() {
        return new IndexadorComMapper<>("responsavel",
            "Responsavel",
            elasticsearchOperations,
            responsavelRepository,
            responsavelDocumentRepository,
            responsavelDocumentMapper);
    }

    @Bean
    public Indexador indexadorTarefa() {
        return new IndexadorComMapper<>("tarefa",
            "Tarefa",
            elasticsearchOperations,
            tarefaRepository,
            tarefaDocumentRepository,
            tarefaDocumentMapper);
    }

}
