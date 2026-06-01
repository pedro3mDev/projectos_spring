package com.anluge.gestDoc.entitys;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Documento")
public class Documento {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDocumento")
    @SequenceGenerator(name = "seqDocumento", sequenceName = "seq_documento", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String arquivo;

    @Column
    private String descricao;

    @Column(name = "datacriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @ManyToOne
    @JoinColumn(name = "proprietario")
    private Usuario proprietario;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column
    private Integer nivel;

    @Column
    private Integer ordem;

    @Column
    private String tipo;

    @Column
    private Integer versao;

    @ManyToOne
    @JoinColumn(name = "departamentoid")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "documentoPaiId")
    private Documento documentoPai;

    @Column
    private BigDecimal tamanho;

    @Column
    private BigDecimal revisao;

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

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Documento getDocumentoPai() {
        return documentoPai;
    }

    public void setDocumentoPai(Documento documentoPai) {
        this.documentoPai = documentoPai;
    }

    public BigDecimal getTamanho() {
        return tamanho;
    }

    public void setTamanho(BigDecimal tamanho) {
        this.tamanho = tamanho;
    }

    public BigDecimal getRevisao() {
        return revisao;
    }

    public void setRevisao(BigDecimal revisao) {
        this.revisao = revisao;
    }
}