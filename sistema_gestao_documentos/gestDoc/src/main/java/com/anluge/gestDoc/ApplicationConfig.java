package com.anluge.gestDoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.anluge.gestDoc.utils.Has;

@EnableWebMvc
@Configuration
public class ApplicationConfig {

    static {
        if (!Has.content(System.getProperty("spring.profiles.active"))) {
            System.setProperty("spring.profiles.active", "PROD");
        }
    }

    @Autowired
    Environment environment;

    @Bean(name = "app")
    @ConfigurationProperties(prefix = "app")
    Application aplicacao() {
        return new Application()
            .setAssets(environment.getRequiredProperty("app.assets"))
            .setDomain(environment.getRequiredProperty("app.dominio"));
    }
}