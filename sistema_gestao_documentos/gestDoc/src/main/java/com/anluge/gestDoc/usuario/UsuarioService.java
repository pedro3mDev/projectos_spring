package com.anluge.gestDoc.usuario;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;

import com.anluge.gestDoc.autenticacao.CadastrarSenhaModel;
import com.anluge.gestDoc.utils.BusinessException;

public interface UsuarioService extends UserDetailsService {

    void salvar(UsuarioModel model);

    UsuarioModel buscarParaEdicao(Integer id);

    List<UsuarioModel> listarUsuarios();

    void ativarUsuario(String email, String token, CadastrarSenhaModel model);

    void dispararEmailRecuperacaoSenha(String email);

    void redefinirSenhaRecuperada(String email, String token, CadastrarSenhaModel model);

    void empresaPossuiPermissao(Integer id) throws BusinessException;

    void validarSalvarUsuario(UsuarioModel model, BindingResult result);
}
