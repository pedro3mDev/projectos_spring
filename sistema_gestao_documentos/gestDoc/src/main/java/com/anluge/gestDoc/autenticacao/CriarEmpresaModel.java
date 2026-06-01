package com.anluge.gestDoc.autenticacao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class CriarEmpresaModel {

    @NotEmpty(message = "Campo Obrigatório")
    private String nomeEmpresa;
    
    @Email(message = "Esse e-mail é inválido")
    @NotEmpty(message = "Campo Obrigatório")
    private String email;

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}