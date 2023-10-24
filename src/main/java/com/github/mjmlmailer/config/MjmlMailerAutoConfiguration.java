package com.github.mjmlmailer.config;

import com.github.mjmlconverter.builder.MjmlRequestBuilder;
import com.github.mjmlconverter.converter.MjmlConverter;
import com.github.mjmlmailer.service.MjmlMailer;
import com.github.mjmlmailer.service.MjmlMailerImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Spring Boot auto-configuration class for the MJML mailer components.
 * This class contains bean definitions that are conditionally created only if they don't already exist in the context.
 */
@Configuration
public class MjmlMailerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MjmlMailer mjmlMailer(MjmlRequestBuilder mjmlRequestBuilder, MjmlConverter mjmlConverter, JavaMailSender mailSender) {
        return new MjmlMailerImpl(mjmlRequestBuilder, mjmlConverter, mailSender);
    }
}
