package br.com.basis.keep2.service.service.elasticsearch;

import br.gov.nuvem.comum.microsservico.service.reindex.Indexador;
import br.gov.nuvem.comum.microsservico.service.reindex.mapper.EntityMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @param <A> classe anotada com @Entity
 * @param <B> classe anotada com @Document, representando um documento do Elasticsearch de {@code <A>}
 * @param <C> classe que representa o ID de @Entity e @Document
 */
@Slf4j
@Getter
@Builder
@AllArgsConstructor
public class IndexadorComMapper<A, B, C extends Serializable> implements Indexador {

    private final String codigo;
    private final String descricao;

    private final ElasticsearchOperations elasticsearchOperations;

    private final JpaRepository<A, C> jpaRepository;
    private final ElasticsearchRepository<B, C> elasticsearchRepository;

    private final EntityMapper<B, A> entityMapper;

    @Override
    public void indexar() {
        Class<B> classe = elasticsearchRepository.getEntityClass();
        elasticsearchOperations.deleteIndex(classe);

        try {
            elasticsearchOperations.createIndex(classe);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
        elasticsearchOperations.putMapping(classe);

        if (jpaRepository.count() > 0) {
            List<A> entidades = jpaRepository.findAll();
            List<B> documentos = entityMapper.toDto(entidades);
            elasticsearchRepository.saveAll(documentos);
        }
    }
}
