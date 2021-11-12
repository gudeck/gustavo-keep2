package br.com.basis.keep2.service.service.filter;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

@Getter
@Setter
public class ResponsavelFilter implements BaseFilter {

    private String nome;
    private String email;
    private String dataNascimento;

    @Override
    public BoolQueryBuilder getQuery() {
        var boolQueryBuilder = QueryBuilders.boolQuery();
        addMatchPhrase(boolQueryBuilder, "nome", nome);
        addMatchPhrase(boolQueryBuilder, "email", email);
        addMatch(boolQueryBuilder, "dataNascimento", dataNascimento);
        return boolQueryBuilder;
    }

}
