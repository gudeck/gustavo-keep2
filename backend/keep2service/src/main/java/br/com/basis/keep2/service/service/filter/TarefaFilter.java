package br.com.basis.keep2.service.service.filter;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.time.LocalDate;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Getter
@Setter
public class TarefaFilter implements BaseFilter {

    private String responsavel;
    private String titulo;
    private String tipo;
    private String dataFimPrevista;
    private String dataInicio;
    private String dataFim;
    private LocalDate dataFimPrevistaPeriodo;
    private LocalDate dataInicioPeriodo;
    private LocalDate dataFimPeriodo;

    @Override
    public BoolQueryBuilder getQuery() {
        var minDate = LocalDate.of(0, 1, 1);
        var maxDate = LocalDate.of(9999, 12, 31);

        var boolQueryBuilder = QueryBuilders.boolQuery();
        addMatchPhrase(boolQueryBuilder, "responsavel", responsavel);
        addMatchPhrase(boolQueryBuilder, "titulo", titulo);
        addMatchPhrase(boolQueryBuilder, "tipo", tipo);
        addMatch(boolQueryBuilder, "dataFimPrevista", dataFimPrevista);
        addMatch(boolQueryBuilder, "dataInicio", dataInicio);
        addMatch(boolQueryBuilder, "dataFim", dataFim);
        addRangeQuery(boolQueryBuilder, "dataInicio", dataInicioPeriodo, defaultIfNull(dataFimPeriodo, maxDate));
        addRangeQuery(boolQueryBuilder, "dataFim", defaultIfNull(dataInicioPeriodo, minDate), dataFimPeriodo);
        return boolQueryBuilder;
    }
}
