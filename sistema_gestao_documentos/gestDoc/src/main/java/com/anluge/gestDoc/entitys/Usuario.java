package com.anluge.gestDoc.entitys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.anluge.gestDoc.enums.EnumSexo;
import com.anluge.gestDoc.utils.Has;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsuario")
    @SequenceGenerator(name = "seqUsuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String senha;

    @Column(name = "dataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column
    @Enumerated(EnumType.STRING)
    private EnumSexo sexo;

    @Column
    private String token;

    @Column(name = "dataCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column(name = "ativo")
    private Boolean ativo;

    @OrderBy("id")
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<EmpresaUsuario> empresaUsuarios;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grupo_usuario", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "grupo_id") })
    private List<Grupo> grupos;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EnumSexo getSexo() {
        return sexo;
    }

    public void setSexo(EnumSexo sexo) {
        this.sexo = sexo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<EmpresaUsuario> getEmpresaUsuarios() {
        return empresaUsuarios;
    }

    public void setEmpresaUsuarios(List<EmpresaUsuario> empresaUsuarios) {
        this.empresaUsuarios = empresaUsuarios;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public List<String> getTodasPermissoes() {

        List<String> permissoes = new ArrayList<>();

        if (Has.content(getGrupos())) {
            for (Grupo grupo : getGrupos()) {
                permissoes.addAll(grupo.getPermissoes());
            }
        }
        return Collections.unmodifiableList(permissoes);
    }

}