package com.anluge.gestDoc.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.anluge.gestDoc.usuario.UsuarioRepository;
import com.anluge.gestDoc.utils.Has;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    @Autowired
    protected HttpSession session;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public PerfilUsuario getUsuarioAutenticado() {

        if (!possuiUsuarioAutenticado()) {
            throw new IllegalArgumentException("Não existe usuário autenticado");
        }

        if (!Has.content(session.getAttribute(USUARIO_AUTENTICADO))) {
            this.recarregarPermissoes();
        }

        return (PerfilUsuario) session.getAttribute(USUARIO_AUTENTICADO);
    }

    @Override
    public Boolean possuiUsuarioAutenticado() {
        return SecurityContextHolder.getContext() != null &&
            Has.content(SecurityContextHolder.getContext().getAuthentication()) &&
            Has.content(SecurityContextHolder.getContext().getAuthentication().getName()) &&
            !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
    }

    @Override
    public boolean possuiPermissao(String permission) {
        return possuiUsuarioAutenticado() && getUsuarioAutenticado().getPermissoes().contains(permission);
    }

    @Override
    public void logout() {
        session.invalidate();
        SecurityContextHolder.clearContext();
    }

    @Override
    public void recarregarPermissoes() {
        if (!possuiUsuarioAutenticado()) {
            throw new IllegalArgumentException("Não existe usuário autenticado");
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        session.setAttribute(USUARIO_AUTENTICADO,
            Util.converterUsuarioParaPerfil(usuarioRepository.findByEmailIgnoreCaseAndAtivoTrue(user.getUsername())));
    }
    
    @Override
    public Integer getEmpresaSelecionada() {
        return this.getUsuarioAutenticado().getEmpresaSelecionada();
    }
}
