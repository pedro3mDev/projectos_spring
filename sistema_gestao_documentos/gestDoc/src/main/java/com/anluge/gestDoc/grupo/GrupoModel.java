package com.anluge.gestDoc.grupo;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public class GrupoModel {

    private Integer id;
    
    @NotEmpty(message = "Campo Obrigatório")
    private String nome;
    private List<String> permissoes;
    private List<Integer> idUsuarios;

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

    public List<String> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<String> permissoes) {
        this.permissoes = permissoes;
    }

    public List<Integer> getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(List<Integer> idUsuarios) {
        this.idUsuarios = idUsuarios;
    }
}
