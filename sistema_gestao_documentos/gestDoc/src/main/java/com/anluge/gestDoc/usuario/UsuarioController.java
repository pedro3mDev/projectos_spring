package com.anluge.gestDoc.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.anluge.gestDoc.utils.BusinessException;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/admin/usuario", name = "admin#usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView listar(Model model) {
        return new ModelAndView("admin/usuario/listagem-usuario").addObject("usuarios", usuarioService.listarUsuarios());
    }

    @RequestMapping(value = "/", name = "criar", method = RequestMethod.GET)
    public ModelAndView criarUsuario(Model model) {
        return new ModelAndView("admin/usuario/edicao-usuario").addObject("usuario", null);
    }

    @RequestMapping(value = "/{id}", name = "editar", method = RequestMethod.GET)
    public ModelAndView editar(@PathVariable("id") Integer id) throws BusinessException {
        usuarioService.empresaPossuiPermissao(id);
        return new ModelAndView("admin/usuario/edicao-usuario").addObject("usuario", usuarioService.buscarParaEdicao(id));
    }

    @RequestMapping(value = { "/", "{id}" }, name = "salvar", method = RequestMethod.POST)
    public ModelAndView salvarUsuario(@Valid @ModelAttribute UsuarioModel model, BindingResult result) throws BusinessException {
        
        if (result.hasErrors()) {
            return new ModelAndView("admin/usuario/edicao-usuario").addObject("item", model).addObject("erros", result);
        }
        usuarioService.empresaPossuiPermissao(model.getId());
        usuarioService.validarSalvarUsuario(model, result);
        
        if (result.hasErrors()) {
            return new ModelAndView("admin/usuario/edicao-usuario").addObject("item", model).addObject("erros", result);
        }
        
        usuarioService.salvar(model);
        return new ModelAndView("redirect:/admin/usuario");
    }
}