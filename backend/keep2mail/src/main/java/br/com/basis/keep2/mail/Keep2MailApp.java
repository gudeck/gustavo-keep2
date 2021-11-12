package br.com.basis.keep2.mail;


import br.gov.nuvem.comum.microsservico.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties({})
@RequiredArgsConstructor
@Slf4j
public class Keep2MailApp implements InitializingBean {

    private final Environment env;

    public static void main(String[] args) {
        AppUtil.startup(args, Keep2MailApp.class, log);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AppUtil.checkProfiles(env, log);
    }
}

