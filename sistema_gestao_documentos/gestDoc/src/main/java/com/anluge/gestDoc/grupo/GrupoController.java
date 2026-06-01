package com.anluge.gestDoc.grupo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.anluge.gestDoc.usuario.UsuarioService;
import com.anluge.gestDoc.utils.BusinessException;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/admin/grupo", name = "admin#grupo")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;
    
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView listar(Model model) {
        return new ModelAndView("admin/grupo/listagem-grupo")
            .addObject("grupos", grupoService.listarGrupos());
    }

    @RequestMapping(value = "/", name = "criar", method = RequestMethod.GET)
    public ModelAndView criarGrupo() {
        return new ModelAndView("admin/grupo/edicao-grupo")
            .addObject("grupo", null)
            .addObject("permissoes", grupoService.buscarPermissoesDisponiveis())
            .addObject("usuarios", usuarioService.listarUsuarios());
    }

    @RequestMapping(value = "/{id}", name = "editar", method = RequestMethod.GET)
    public ModelAndView editar(@PathVariable("id") Integer id) throws BusinessException {
        grupoService.empresaPossuiPermissao(id);
        
        return new ModelAndView("admin/grupo/edicao-grupo")
            .addObject("grupo", grupoService.buscarParaEdicao(id))
            .addObject("permissoes", grupoService.buscarPermissoesDisponiveis())
            .addObject("usuarios", usuarioService.listarUsuarios());
    }

    @RequestMapping(value = { "/", "{id}" }, name = "salvar", method = RequestMethod.POST)
    public ModelAndView salvarGrupoGrupo(@Valid @ModelAttribute GrupoModel model, BindingResult result) throws BusinessException {
        
        if (result.hasErrors()) {
            return new ModelAndView("admin/grupo/edicao-grupo").addObject("item", model).addObject("erros", result);
        }
        
        grupoService.empresaPossuiPermissao(model.getId());
        grupoService.salvar(model);
        return new ModelAndView("redirect:/admin/grupo");
    }
}