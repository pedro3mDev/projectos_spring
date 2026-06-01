package com.anluge.gestDoc.departamento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anluge.gestDoc.empresa.EmpresaService;
import com.anluge.gestDoc.entitys.Departamento;
import com.anluge.gestDoc.entitys.Grupo;
import com.anluge.gestDoc.entitys.Usuario;
import com.anluge.gestDoc.usuario.UsuarioRepository;
import com.anluge.gestDoc.utils.BusinessException;
import com.anluge.gestDoc.utils.Has;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public void salvar(DepartamentoModel model) {
        Departamento entidade = null;
        if (Has.content(model.getId())) {
            entidade = departamentoRepository.findById(model.getId()).orElse(new Departamento());
        } else {
            entidade = new Departamento();
            entidade.setData(new Date());
            entidade.setAtivo(true);
        }

        entidade.setNome(model.getNome());
        entidade.setData(model.getData());
        entidade.setEmpresa(empresaService.buscarEmpresaSelecionada());

        entidade.setUsuarios(new ArrayList<Usuario>());
        if(Has.content(model.getIdUsuarios())) {
            for (Integer idUsuario : model.getIdUsuarios()) {
                Optional<Usuario> u = usuarioRepository.findById(idUsuario);
                if (u.isPresent()) {
                    entidade.getUsuarios().add(u.get());
                }
            }            
        }

        departamentoRepository.save(entidade);
    }

    @Override
    public DepartamentoModel buscarParaEdicao(Integer id) {
        Departamento entidade = departamentoRepository.findById(id).orElse(new Departamento());
        DepartamentoModel model = new DepartamentoModel();
        model.setId(id);
        model.setNome(entidade.getNome());
        model.setAtivo(entidade.getAtivo());

        model.setIdUsuarios(new ArrayList<Integer>());
        for (Usuario usuario : entidade.getUsuarios()) {
            model.getIdUsuarios().add(usuario.getId());
        }

        return model;
    }

    @Override
    public List<DepartamentoModel> listarDepartamentos() {
        List<DepartamentoModel> departamentos = new ArrayList<DepartamentoModel>();
        List<Departamento> entidades = departamentoRepository.findByEmpresa(empresaService.buscarEmpresaSelecionada());

        for (Departamento departamento : entidades) {
            DepartamentoModel model = new DepartamentoModel();
            model.setId(departamento.getId());
            model.setNome(departamento.getNome());
            model.setAtivo(departamento.getAtivo());
            departamentos.add(model);
        }
        return departamentos;
    }
    
    @Override
    public void empresaPossuiPermissao(Integer id) throws BusinessException {
        if(Has.content(id)) {
            Departamento departamento = departamentoRepository.findById(id).orElse(new Departamento());
            EmpresaService.SEM_PERMISSAO_EXCEPTION.thrownIf(Has.content(departamento.getEmpresa()) && departamento.getEmpresa().getId().compareTo(empresaService.buscarEmpresaSelecionada().getId()) != 0);
        }
    }
}