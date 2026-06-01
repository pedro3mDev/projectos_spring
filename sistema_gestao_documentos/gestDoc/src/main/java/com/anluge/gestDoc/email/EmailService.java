package com.anluge.gestDoc.email;

import com.anluge.gestDoc.entitys.Usuario;

public interface EmailService {

    void dispararEmailAtivacaoUsuario(Usuario u);

    void dispararEmailRedefinirSenha(Usuario u);
}