package com.anluge.gestDoc.utils;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import br.com.delogic.jfunk.Has;

public class MappingPermissaoRepository implements PermissaoRepository {

    private Set<String> permissoes = new LinkedHashSet<>();

    public void addPermissao(String... permissions) {
        for (String permission : permissions) {
            this.permissoes.add(permission);
        }
    }

    @Override
    public void addPermissoes(RequestMappingHandlerMapping handlerMapping) {
        for (RequestMappingInfo rmi : handlerMapping.getHandlerMethods().keySet()) {
            if (!Has.content(rmi.getName())) {
                continue;
            }
            permissoes.add(rmi.getName());
        }
    }

    @Override
    public Collection<String> getPermissoes() {
        return permissoes;
    }

}
