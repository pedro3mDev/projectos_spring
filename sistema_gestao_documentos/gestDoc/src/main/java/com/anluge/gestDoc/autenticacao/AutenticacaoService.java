package com.anluge.gestDoc.autenticacao;

import java.util.ArrayList;

import com.anluge.gestDoc.entitys.EmpresaUsuario;
import com.anluge.gestDoc.entitys.Usuario;
import com.anluge.gestDoc.utils.BusinessException;

public interface AutenticacaoService {

    public static class Util {

        public static PerfilUsuario converterUsuarioParaPerfil(Usuario usuario) {
            PerfilUsuario perfil = new PerfilUsuario();
            perfil.setId(usuario.getId());
            perfil.setNome(usuario.getNome());
            perfil.setEmail(usuario.getEmail());
            perfil.setPermissoes(new ArrayList<String>());
            perfil.setPermissoes(usuario.getTodasPermissoes());

            for (EmpresaUsuario empresa : usuario.getEmpresaUsuarios()) {
                // TODO tratar quando houver multiplas empresas
                perfil.setEmpresaSelecionada(empresa.getEmpresa().getId());
            }

            return perfil;
        }
    }

    BusinessException USUARIO_DESCONHECIDO_EXCEPTION = new BusinessException("Usuário não encontrado");

    final String USUARIO_AUTENTICADO = "br.com.anluge.gestDoc.autenticacao.AutenticacaoService:UsuarioAutenticado";

    Boolean possuiUsuarioAutenticado();

    PerfilUsuario getUsuarioAutenticado();

    void logout();

    boolean possuiPermissao(String permission);

    void recarregarPermissoes();

    Integer getEmpresaSelecionada();
}
