package com.anluge.gestDoc.utils;

import java.util.Collection;

import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public interface PermissaoRepository {

    Collection<String> getPermissoes();

    void addPermissoes(RequestMappingHandlerMapping handlerMapping);

}
