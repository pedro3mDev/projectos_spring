package com.anluge.gestDoc.utils;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.ServletContext;

public class UrlMapping implements ServletContextAware {

    private String url;
    private String path;
    private String contexto;

    public UrlMapping(String path) {
        this.path = path;
    }

    public UrlMapping() {}

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return path;
    }

    public String getPath() {
        return path;
    }

    public String getRedirectPath() {
        return "redirect:" + path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public UrlBuilder getBuilder() {
        return new UrlBuilder(path);
    }

    public UrlBuilder getUrlBuilder() {
        return new UrlBuilder(url);
    }

    public UrlBuilder getRedirectBuilder() {
        return new UrlBuilder(getRedirectPath());
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.contexto = servletContext.getContextPath();
        this.url = contexto + path;
    }

    public class UrlBuilder {

        private UriComponentsBuilder builder;

        private UrlBuilder(String path) {
            builder = UriComponentsBuilder.fromUriString(path);
        }

        public UrlBuilder query(String queryString) {
            builder = builder.query(queryString);
            return this;
        }

        public String build(Object... params) {
            return builder.buildAndExpand(params).encode().toUriString();
        }

    }
}
