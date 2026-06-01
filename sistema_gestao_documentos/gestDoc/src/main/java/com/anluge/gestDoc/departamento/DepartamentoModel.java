package com.anluge.gestDoc.departamento;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;

public class DepartamentoModel {

    private Integer id;
    
    @NotEmpty(message = "Campo Obrigatório")
    private String nome;
    private Boolean ativo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Integer> getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(List<Integer> idUsuarios) {
        this.idUsuarios = idUsuarios;
    }
}