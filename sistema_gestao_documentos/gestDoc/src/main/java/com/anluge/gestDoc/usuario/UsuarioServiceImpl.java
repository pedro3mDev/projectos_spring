package com.anluge.gestDoc.usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.anluge.gestDoc.autenticacao.AutenticacaoService;
import com.anluge.gestDoc.autenticacao.CadastrarSenhaModel;
import com.anluge.gestDoc.autenticacao.CriarEmpresaModel;
import com.anluge.gestDoc.autenticacao.PerfilUsuario;
import com.anluge.gestDoc.email.EmailService;
import com.anluge.gestDoc.empresa.EmpresaRepository;
import com.anluge.gestDoc.empresa.EmpresaService;
import com.anluge.gestDoc.empresa.EmpresaUsuarioRepository;
import com.anluge.gestDoc.entitys.EmpresaUsuario;
import com.anluge.gestDoc.entitys.Usuario;
import com.anluge.gestDoc.utils.BusinessException;
import com.anluge.gestDoc.utils.Has;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaUsuarioRepository empresaUsuarioRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public List<UsuarioModel> listarUsuarios() {
        List<UsuarioModel> usuarios = new ArrayList<UsuarioModel>();
        List<Usuario> entidades = usuarioRepository.findByEmpresa(autenticacaoService.getEmpresaSelecionada());

        for (Usuario usuario : entidades) {
            UsuarioModel model = new UsuarioModel();
            model.setId(usuario.getId());
            model.setNome(usuario.getNome());
            model.setEmail(usuario.getEmail());
            model.setAtivo(usuario.getAtivo());
            model.setDataNascimento(usuario.getDataNascimento());
            usuarios.add(model);
        }

        return usuarios;
    }

    @Override
    public UsuarioModel buscarParaEdicao(Integer id) {
        Usuario entidade = usuarioRepository.findById(id).orElse(new Usuario());
        UsuarioModel model = new UsuarioModel();
        model.setId(id);
        model.setNome(entidade.getNome());
        model.setEmail(entidade.getEmail());
        model.setSenha(entidade.getSenha());
        model.setDataNascimento(entidade.getDataNascimento());
        model.setAtivo(entidade.getAtivo());
        return model;
    }
    
    @Override
    public void validarSalvarUsuario(UsuarioModel model, BindingResult result) {
        Usuario u = usuarioRepository.findByEmailIgnoreCaseAndAtivoTrue(model.getEmail());
        
        if(Has.content(u) && (!Has.content(model.getId()) || u.getId().compareTo(model.getId()) != 0)) {
            result.addError(new FieldError("item", "email", "Este e-mail já esta cadastrado"));
        }
    }

    @Override
    @Transactional
    public void salvar(UsuarioModel model) {
        Usuario entidade = null;
        Boolean novoUsuario = null;

        if (Has.content(model.getId())) {
            novoUsuario = false;
            entidade = usuarioRepository.findById(model.getId()).orElse(new Usuario());
        } else {
            novoUsuario = true;
            entidade = new Usuario();
            entidade.setDataCriacao(new Date());

            if (!Has.content(entidade.getToken())) {
                entidade.setToken(UUID.randomUUID().toString());
            }

            entidade.setEmpresaUsuarios(new ArrayList<>());
        }

        entidade.setNome(model.getNome());
        entidade.setEmail(model.getEmail());
        entidade.setDataNascimento(model.getDataNascimento());
        usuarioRepository.save(entidade);

        if (novoUsuario) {
            EmpresaUsuario vinculo = new EmpresaUsuario();
            vinculo.setEmpresa(empresaRepository.findById(autenticacaoService.getEmpresaSelecionada()).get());
            vinculo.setUsuario(entidade);
            vinculo.setAtivo(true);
            vinculo.setData(new Date());
            empresaUsuarioRepository.save(vinculo);

            emailService.dispararEmailAtivacaoUsuario(entidade);
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCaseAndAtivoTrue(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(AutenticacaoService.USUARIO_DESCONHECIDO_EXCEPTION.getMessage());
        }
        PerfilUsuario perfilUsuario = AutenticacaoService.Util.converterUsuarioParaPerfil(usuario);

        return new User(perfilUsuario.getEmail(), usuario.getSenha(),
            Boolean.TRUE.equals(usuario.getAtivo()), true, true, true, perfilUsuario.getAuthorities());
    }

    @Override
    @Transactional
    public void ativarUsuario(String email, String token, CadastrarSenhaModel model) {
        Usuario u = usuarioRepository.findByEmailAndToken(email, token);
        if (Has.content(u)) {
            u.setSenha(new BCryptPasswordEncoder().encode(model.getSenha()));
            u.setAtivo(true);
            u.setToken(null);
            usuarioRepository.save(u);
        }
    }

    @Override
    @Transactional
    public void dispararEmailRecuperacaoSenha(String email) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCaseAndAtivoTrue(email);

        if (Has.content(usuario)) {
            usuario.setToken(UUID.randomUUID().toString());
            usuarioRepository.save(usuario);
            emailService.dispararEmailRedefinirSenha(usuario);
        }
    }

    @Override
    @Transactional
    public void redefinirSenhaRecuperada(String email, String token, CadastrarSenhaModel model) {
        Usuario u = usuarioRepository.findByEmailAndToken(email, token);
        if (Has.content(u)) {
            u.setSenha(new BCryptPasswordEncoder().encode(model.getSenha()));
            u.setToken(null);
            usuarioRepository.save(u);
        }
    }

    @Override
    public void empresaPossuiPermissao(Integer id) throws BusinessException {
        if (Has.content(id)) {
            Usuario usuario = usuarioRepository.findById(id).orElse(new Usuario());

            if (Has.content(usuario) && Has.content(usuario.getEmpresaUsuarios())) {
                Boolean possuiPermissao = false;
                for (EmpresaUsuario vinculo : usuario.getEmpresaUsuarios()) {
                    if (vinculo.getEmpresa().getId().compareTo(autenticacaoService.getEmpresaSelecionada()) == 0) {
                        possuiPermissao = true;
                    }
                }
                EmpresaService.SEM_PERMISSAO_EXCEPTION.thrownIf(!possuiPermissao);
            }
        }
    }
}