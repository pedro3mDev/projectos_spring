package com.anluge.gestDoc;

import org.springframework.web.context.ServletContextAware;

import com.anluge.gestDoc.utils.Has;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;

public class Application implements ServletContextAware {
    private ServletContext servletContext;

    private String context;
    private String assets;
    private String domain;

    @PostConstruct
    public void init() {
        context = servletContext.getContextPath();
        if (!Has.content(assets)) {
            assets = context;
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String getContext() {
        return context;
    }

    public Application setContext(String contexto) {
        this.context = contexto;
        return this;
    }

    public String getAssets() {
        return assets;
    }

    public Application setAssets(String assets) {
        this.assets = assets;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public Application setDomain(String dominio) {
        this.domain = dominio;
        return this;
    }
}
