package com.anluge.gestDoc.departamento;

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
@RequestMapping(value = "/admin/departamento", name = "admin#usuario")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView listar(Model model) {
        return new ModelAndView("admin/departamento/listagem-departamento").addObject("departamentos",
            departamentoService.listarDepartamentos());
    }

    @RequestMapping(value = "/", name = "criar", method = RequestMethod.GET)
    public ModelAndView criarDepartamento() {
        return new ModelAndView("admin/departamento/edicao-departamento")
            .addObject("departamento", null)
            .addObject("usuarios", usuarioService.listarUsuarios());
    }

    @RequestMapping(value = "/{id}", name = "editar", method = RequestMethod.GET)
    public ModelAndView editar(@PathVariable("id") Integer id) throws BusinessException {
        departamentoService.empresaPossuiPermissao(id);
        return new ModelAndView("admin/departamento/edicao-departamento")
            .addObject("departamento", departamentoService.buscarParaEdicao(id))
            .addObject("usuarios", usuarioService.listarUsuarios());
    }

    @RequestMapping(value = { "/", "{id}" }, name = "salvar", method = RequestMethod.POST)
    public ModelAndView salvarDepartamento(@Valid @ModelAttribute DepartamentoModel model, BindingResult result) throws BusinessException {

        if (result.hasErrors()) {
            return new ModelAndView("admin/departamento/edicao-departamento").addObject("item", model).addObject("erros", result);
        }
        departamentoService.empresaPossuiPermissao(model.getId());
        departamentoService.salvar(model);
        return new ModelAndView("redirect:/admin/departamento");
    }
}