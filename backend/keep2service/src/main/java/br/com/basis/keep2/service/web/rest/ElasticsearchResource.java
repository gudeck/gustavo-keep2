package br.com.basis.keep2.service.web.rest;

import br.com.basis.keep2.service.service.elasticsearch.ElasticsearchService;
import br.gov.nuvem.comum.microsservico.service.reindex.Indexador;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/elasticsearch/reindex")
public class ElasticsearchResource {

    private final ElasticsearchService elasticsearchService;

    @GetMapping("/listar-indexadores")
    public List<Indexador> listarIndexadores() {
        return elasticsearchService.listarIndexadores();
    }

    @GetMapping("/reindexar")
    public void reindexar(@RequestParam(required = false) Optional<List<String>> codigos) {
        this.elasticsearchService.reindexar(codigos);
    }

}
