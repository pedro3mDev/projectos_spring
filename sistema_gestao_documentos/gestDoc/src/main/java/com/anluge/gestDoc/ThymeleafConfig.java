package com.anluge.gestDoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.anluge.gestDoc.utils.UrlMapping;

import jakarta.annotation.PostConstruct;

@Configuration
public class ThymeleafConfig implements WebMvcConfigurer {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostConstruct
    public void extension() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("pages/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(templateEngine.getTemplateResolvers().size());
        resolver.setCacheable(false);
        templateEngine.addTemplateResolver(resolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/content/**").addResourceLocations("classpath:/static/");
    }

    @Bean(name = "urlAtivarConta")
    UrlMapping urlAtivarConta() {
        return new UrlMapping("/ativar-conta/{email}/{token}");
    }

    @Bean(name = "urlResetSenha")
    UrlMapping urlResetSenha() {
        return new UrlMapping("/recuperar-senha/{email}/{token}");
    }
}