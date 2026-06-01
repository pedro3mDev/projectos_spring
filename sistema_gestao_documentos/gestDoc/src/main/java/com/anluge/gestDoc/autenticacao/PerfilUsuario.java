package com.anluge.gestDoc.autenticacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.anluge.gestDoc.utils.Has;

public class PerfilUsuario {

    private Integer id;
    private String nome;
    private String email;
    private List<String> permissoes;
    private Integer empresaSelecionada;

    public PerfilUsuario() {}

    public PerfilUsuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<String> permissoes) {
        this.permissoes = permissoes;
    }

    public Integer getEmpresaSelecionada() {
        return empresaSelecionada;
    }

    public void setEmpresaSelecionada(Integer empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
    }

    public List<GrantedAuthority> getAuthorities() {
        if (!Has.content(permissoes)) {
            return new ArrayList<GrantedAuthority>();
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (String permissao : permissoes) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permissao));
        }
        return grantedAuthorities;
    }
}
