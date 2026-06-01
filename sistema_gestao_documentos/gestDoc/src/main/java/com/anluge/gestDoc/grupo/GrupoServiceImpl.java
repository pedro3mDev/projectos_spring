package com.anluge.gestDoc.grupo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anluge.gestDoc.empresa.EmpresaService;
import com.anluge.gestDoc.entitys.Grupo;
import com.anluge.gestDoc.entitys.Usuario;
import com.anluge.gestDoc.usuario.UsuarioRepository;
import com.anluge.gestDoc.utils.BusinessException;
import com.anluge.gestDoc.utils.Has;
import com.anluge.gestDoc.utils.PermissaoRepository;

@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private EmpresaService empresaService;

    @Override
    public List<GrupoModel> listarGrupos() {
        List<GrupoModel> grupos = new ArrayList<GrupoModel>();
        List<Grupo> entidades = grupoRepository.findByEmpresa(empresaService.buscarEmpresaSelecionada());

        for (Grupo grupo : entidades) {
            GrupoModel model = new GrupoModel();
            model.setId(grupo.getId());
            model.setNome(grupo.getNome());
            grupos.add(model);
        }

        return grupos;
    }

    @Override
    public GrupoModel buscarParaEdicao(Integer id) {
        Grupo entidade = grupoRepository.findById(id).orElse(new Grupo());

        GrupoModel model = new GrupoModel();
        model.setId(id);
        model.setNome(entidade.getNome());
        model.setPermissoes(entidade.getPermissoes());
        model.setIdUsuarios(new ArrayList<Integer>());

        for (Usuario usuario : entidade.getUsuarios()) {
            model.getIdUsuarios().add(usuario.getId());
        }

        return model;
    }

    @Override
    @Transactional
    public void salvar(GrupoModel model) {
        Grupo entidade = null;
        if (Has.content(model.getId())) {
            entidade = grupoRepository.findById(model.getId()).orElse(new Grupo());
        } else {
            entidade = new Grupo();
        }

        entidade.setNome(model.getNome());
        entidade.setPermissoes(model.getPermissoes());
        entidade.setEmpresa(empresaService.buscarEmpresaSelecionada());
        entidade.setUsuarios(new ArrayList<>());

        if(Has.content(model.getIdUsuarios())) {
            for (Integer idUsuario : model.getIdUsuarios()) {
                Optional<Usuario> u = usuarioRepository.findById(idUsuario);
                if (u.isPresent()) {
                    entidade.getUsuarios().add(u.get());
                }
            }            
        }

        if (!Has.content(entidade.getId())) {
            entidade.setAtivo(true);
            entidade.setData(new Date());
        }

        grupoRepository.save(entidade);
    }

    @Override
    public List<String> buscarPermissoesDisponiveis() {
        return new ArrayList<String>(permissaoRepository.getPermissoes());
    }
    
    @Override
    public void empresaPossuiPermissao(Integer id) throws BusinessException {
        if(Has.content(id)) {
            Grupo grupo = grupoRepository.findById(id).orElse(new Grupo());
            EmpresaService.SEM_PERMISSAO_EXCEPTION.thrownIf(Has.content(grupo.getEmpresa()) && grupo.getEmpresa().getId().compareTo(empresaService.buscarEmpresaSelecionada().getId()) != 0);
        }
    }
}