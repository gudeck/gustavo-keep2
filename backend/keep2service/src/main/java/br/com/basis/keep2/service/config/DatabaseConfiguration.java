package br.com.basis.keep2.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableJpaRepositories("br.com.basis.keep2.service.repository")
@EnableTransactionManagement
@EnableElasticsearchRepositories("br.com.basis.keep2.service.repository.search")
public class DatabaseConfiguration {
}
