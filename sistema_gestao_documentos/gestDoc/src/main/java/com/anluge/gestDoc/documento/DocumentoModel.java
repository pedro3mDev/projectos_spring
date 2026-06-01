package com.anluge.gestDoc.documento;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.anluge.gestDoc.entitys.Departamento;

public class DocumentoModel {

    private Integer id;
    private String nome;
    private String arquivo;
    private String descricao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataCriacao;
    private Integer proprietario;
    private Boolean ativo;
    private Integer nivel;
    private Integer ordem;
    private String tipo;
    private Integer versao;

    private Departamento departamento;

    private Integer documentoPai;
    private BigDecimal tamanho;
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

    public Integer getProprietario() {
        return proprietario;
    }

    public void setProprietario(Integer proprietario) {
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

    public Integer getDocumentoPai() {
        return documentoPai;
    }

    public void setDocumentoPai(Integer documentoPai) {
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
