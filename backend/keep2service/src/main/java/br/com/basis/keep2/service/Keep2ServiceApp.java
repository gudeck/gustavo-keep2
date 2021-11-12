package br.com.basis.keep2.service;


import br.gov.nuvem.comum.microsservico.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties({LiquibaseProperties.class})
@RequiredArgsConstructor
@Slf4j
public class Keep2ServiceApp implements InitializingBean {

    private final Environment env;

    public static void main(String[] args) {
        AppUtil.startup(args, Keep2ServiceApp.class, log);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AppUtil.checkProfiles(env, log);
    }
}

