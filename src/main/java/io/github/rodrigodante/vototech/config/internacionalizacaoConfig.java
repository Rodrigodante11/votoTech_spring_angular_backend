package io.github.rodrigodante.vototech.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class internacionalizacaoConfig {

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // aonde esta o arquivo de mensagens ( mensagens.properties)
        messageSource.setDefaultEncoding("ISO-8859-1"); //Reconher os acentos
        messageSource.setDefaultLocale(Locale.getDefault() ); // pegando a localizacao do SO para as mensagens
        return messageSource;

    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){  // faz as integracoes entre as mensagens
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
