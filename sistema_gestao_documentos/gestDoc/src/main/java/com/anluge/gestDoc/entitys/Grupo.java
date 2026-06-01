package com.anluge.gestDoc.entitys;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Grupo")
public class Grupo {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGrupo")
    @SequenceGenerator(name = "seqGrupo", sequenceName = "seq_grupo", allocationSize = 1, initialValue = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "empresaId")
    private Empresa empresa;

    @Column
    private String nome;

    @Column(name = "ativo")
    private Boolean ativo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date data;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "grupo_permissao", joinColumns = { @JoinColumn(referencedColumnName = "id", name = "grupo_id") })
    @Column(name = "permissao", length = 255)
    private List<String> permissoes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grupo_usuario", joinColumns = {
                    @JoinColumn(name = "grupo_id") }, inverseJoinColumns = { @JoinColumn(name = "usuario_id") })
    private List<Usuario> usuarios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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

    public List<String> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<String> permissoes) {
        this.permissoes = permissoes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}