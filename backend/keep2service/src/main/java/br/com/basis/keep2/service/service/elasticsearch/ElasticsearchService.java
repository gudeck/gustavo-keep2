package br.com.basis.keep2.service.service.elasticsearch;

import br.gov.nuvem.comum.microsservico.service.reindex.Indexador;
import br.gov.nuvem.comum.microsservico.util.SonarUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
@Service
public class ElasticsearchService {
    private static final Lock reindexLock = new ReentrantLock();
    private final List<Indexador> indexadores;
    private Map<String, Indexador> indexadoresPorCodigo;

    @PostConstruct
    public void inicializaIndexadoresPorCodigo() {
        indexadoresPorCodigo = new HashMap<>();
        indexadores.forEach(indexador -> indexadoresPorCodigo.put(indexador.getCodigo(), indexador));
    }

    public List<Indexador> listarIndexadores() {
        return SonarUtil.instantiateList(indexadores);
    }

    public void reindexar(Optional<List<String>> codigos) {
        if (reindexLock.tryLock()) {
            try {
                if (codigos.isPresent()) {
                    codigos.get().forEach(codigo -> indexadoresPorCodigo.get(codigo).indexar());
                } else {
                    indexadores.forEach(Indexador::indexar);
                }
            } finally {
                reindexLock.unlock();
            }
        }
    }
}
