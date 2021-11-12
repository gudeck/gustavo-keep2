package br.com.basis.keep2.service.service.filter;

import org.elasticsearch.index.query.BoolQueryBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNoneBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.elasticsearch.index.query.QueryBuilders.matchPhrasePrefixQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;
import static org.elasticsearch.index.query.QueryBuilders.wildcardQuery;

public interface BaseFilter {

    default void addMatch(BoolQueryBuilder boolQueryBuilder, String field, String value) {
        if (isNotBlank(value)) {
            boolQueryBuilder.must(matchQuery(field, value));
        }
    }

    default void addMatch(BoolQueryBuilder boolQueryBuilder, String field, Enum<?> value) {
        if (nonNull(value)) {
            boolQueryBuilder.must(matchQuery(field, value));
        }
    }

    default void addMatch(BoolQueryBuilder boolQueryBuilder, String field, Double value) {
        if (nonNull(value)) {
            boolQueryBuilder.must(matchQuery(field, value));
        }
    }

    default void addMatch(BoolQueryBuilder boolQueryBuilder, String field, Long value) {
        if (nonNull(value)) {
            boolQueryBuilder.must(matchQuery(field, value));
        }
    }

    default void addMatch(BoolQueryBuilder boolQueryBuilder, String field, Boolean value) {
        if (nonNull(value)) {
            boolQueryBuilder.must(matchQuery(field, value));
        }
    }

    default void addMatchPhrase(BoolQueryBuilder boolQueryBuilder, String field, String value) {
        if (isNotBlank(value)) {
            boolQueryBuilder.must(matchPhrasePrefixQuery(field, wrapStar(value)));
        }
    }

    private String wrapStar(String value) {
        return String.format("*%s*", value.toLowerCase());
    }

    default void addRangeQuery(BoolQueryBuilder boolQueryBuilder, String field, LocalDateTime value1, LocalDateTime value2) {
        if (nonNull(value1) && nonNull(value2)) {
            boolQueryBuilder.must(rangeQuery(field)
                .gte(value1)
                .lte(value2));
        }
    }

    default void addRangeQuery(BoolQueryBuilder boolQueryBuilder, String field, LocalDate value1, LocalDate value2) {
        if (nonNull(value1) && nonNull(value2)) {
            boolQueryBuilder.must(rangeQuery(field)
                .gte(value1)
                .lte(value2));
        }
    }

    default void addTerm(BoolQueryBuilder boolQueryBuilder, String field, LocalDateTime value) {
        if (nonNull(value)) {
            boolQueryBuilder.must(termQuery(field, value.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        }
    }

    default void addTermFilter(BoolQueryBuilder boolQueryBuilder, String field, String value) {
        if (isNotBlank(value)) {
            boolQueryBuilder.filter(termQuery(field, value));
        }
    }

    default void addTermFilter(BoolQueryBuilder boolQueryBuilder, String field, String... values) {
        if (isNoneBlank(values)) {
            boolQueryBuilder.filter(termsQuery(field, values));
        }
    }

    default void addWildcard(BoolQueryBuilder boolQueryBuilder, String field, String value) {
        if (isNotBlank(value)) {
            boolQueryBuilder.must(wildcardQuery(field, wrapStar(value)));
        }
    }

    BoolQueryBuilder getQuery();
}
