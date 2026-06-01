package com.anluge.gestDoc.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.anluge.gestDoc.empresa.EmpresaService;
import com.anluge.gestDoc.usuario.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "")
public class LoginController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return new ModelAndView("login");
    }

    @GetMapping("/criar-empresa")
    public ModelAndView getCriarEmpresa(Model model) {
        return new ModelAndView("empresa").addObject("item", new CriarEmpresaModel());
    }

    @PostMapping(value = { "/criar-empresa" })
    public ModelAndView postCriarEmpresa(@Valid @ModelAttribute CriarEmpresaModel model, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("empresa").addObject("item", model).addObject("erros", result);
        }
        empresaService.validarCriarNovaEmpresa(model, result);

        if (result.hasErrors()) {
            return new ModelAndView("empresa").addObject("item", model).addObject("erros", result);
        }

        empresaService.criarNovaEmpresa(model);
        return new ModelAndView("sucesso-criar-empresa");
    }

    @GetMapping("/ativar-conta/{email}/{token}")
    public ModelAndView getAtivarConta(@PathVariable("email") String email, @PathVariable("token") String token) {
        return new ModelAndView("ativar-conta-senha");
    }

    @PostMapping("/ativar-conta/{email}/{token}")
    public ModelAndView postAtivarConta(@PathVariable("email") String email, @PathVariable("token") String token,
        @ModelAttribute CadastrarSenhaModel model) {
        usuarioService.ativarUsuario(email, token, model);
        return new ModelAndView("ativar-conta-sucesso");
    }

    @GetMapping("/recuperar-senha")
    public ModelAndView recuperarsenha() {
        return new ModelAndView("recuperar-senha");
    }

    @PostMapping("/recuperar-senha")
    public ModelAndView getAtivarConta(@RequestParam("email") String email) {
        usuarioService.dispararEmailRecuperacaoSenha(email);
        return new ModelAndView("recuperar-senha-sucesso-disparo");
    }

    @GetMapping("/recuperar-senha/{email}/{token}")
    public ModelAndView getRedefinirSenha(@PathVariable("email") String email, @PathVariable("token") String token) {
        return new ModelAndView("redefinir-senha");
    }

    @PostMapping("/recuperar-senha/{email}/{token}")
    public ModelAndView postRedefinirSenha(@PathVariable("email") String email, @PathVariable("token") String token,
        @ModelAttribute CadastrarSenhaModel model) {
        usuarioService.redefinirSenhaRecuperada(email, token, model);
        return new ModelAndView("redefinir-senha-sucesso");
    }
}