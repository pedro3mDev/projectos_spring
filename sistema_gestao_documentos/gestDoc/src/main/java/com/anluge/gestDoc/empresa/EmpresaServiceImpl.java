package com.anluge.gestDoc.empresa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.anluge.gestDoc.autenticacao.AutenticacaoService;
import com.anluge.gestDoc.autenticacao.CriarEmpresaModel;
import com.anluge.gestDoc.email.EmailService;
import com.anluge.gestDoc.entitys.Empresa;
import com.anluge.gestDoc.entitys.EmpresaUsuario;
import com.anluge.gestDoc.entitys.Grupo;
import com.anluge.gestDoc.entitys.Usuario;
import com.anluge.gestDoc.grupo.GrupoRepository;
import com.anluge.gestDoc.usuario.UsuarioRepository;
import com.anluge.gestDoc.utils.Has;
import com.anluge.gestDoc.utils.PermissaoRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaUsuarioRepository empresaUsuarioRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PermissaoRepository permissaoResporitory;

    @Autowired
    private AutenticacaoService autenticacaoService;
    
    
    @Override
    public void validarCriarNovaEmpresa(CriarEmpresaModel model, BindingResult result) {
        Usuario u = usuarioRepository.findByEmailIgnoreCaseAndAtivoTrue(model.getEmail());
        
        if(Has.content(u)) {
            result.addError(new FieldError("item", "email", "Este e-mail já esta cadastrado"));
        }
    }

    @Override
    @Transactional
    public void criarNovaEmpresa(CriarEmpresaModel model) {
        Usuario usuario = new Usuario();;
        usuario.setDataCriacao(new Date());
        usuario.setToken(UUID.randomUUID().toString());
        usuario.setEmail(model.getEmail());
        usuario.setAtivo(false);
        usuario = usuarioRepository.save(usuario);

        Empresa empresa = new Empresa();
        empresa.setAtivo(true);
        empresa.setDataCriacao(new Date());
        empresa.setEmail(model.getEmail());
        empresa.setNome(model.getNomeEmpresa());
        empresa.setProprietario(usuario);
        empresa = empresaRepository.save(empresa);

        EmpresaUsuario vinculo = new EmpresaUsuario();
        vinculo.setEmpresa(empresa);
        vinculo.setUsuario(usuario);
        vinculo.setAtivo(true);
        vinculo.setData(new Date());

        Grupo grupo = new Grupo();
        grupo.setNome("Administradores");
        grupo.setAtivo(true);
        grupo.setData(new Date());
        grupo.setEmpresa(empresa);
        grupo.setPermissoes(new ArrayList<String>(permissaoResporitory.getPermissoes()));
        grupo.setUsuarios(Arrays.asList(usuario));
        grupo = grupoRepository.save(grupo);

        empresaUsuarioRepository.save(vinculo);
        emailService.dispararEmailAtivacaoUsuario(usuario);
    }

    @Override
    public Empresa buscarEmpresaSelecionada() {
        return empresaRepository.findById(autenticacaoService.getEmpresaSelecionada()).get();
    }
}