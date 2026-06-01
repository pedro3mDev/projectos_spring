package com.anluge.gestDoc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/teste")
public class TesteHtmlController {
    
    @GetMapping("/")
    public String paginaIndex(Model model) {
        model.addAttribute("index", 123);
        return "index";
    }
    
    /*
     * ADMIN
     * 
     * */
    @GetMapping("/admin/departamento")
    public String adminDepartamento(Model model) {
        model.addAttribute("admin_departamento_listar", 123);
        return "admin/departamento";
    }
    
    @GetMapping("/admin/departamento/")
    public String adminDepartamentoAtualizar(Model model) {
        model.addAttribute("admin_departamento_cadastrar", 123);
        return "admin/departamento";
    }
    
    @GetMapping("/admin/documento")
    public String adminDocumento(Model model) {
        model.addAttribute("admin_documento_listar", 123);
        return "admin/documento";
    }
    
    @GetMapping("/admin/documento/")
    public String adminDocumentoAtualizar(Model model) {
        model.addAttribute("admin_documento_cadastrar", 123);
        return "admin/documento";
    }
    
    @GetMapping("/admin/usuario")
    public String adminUsuario(Model model) {
        model.addAttribute("admin_usuario_listar", 123);
        return "admin/usuario/";
    }
    
    @GetMapping("/admin/usuario/")
    public String adminUsuarioAtualizar(Model model) {
        model.addAttribute("admin_usuario_cadastrar", 123);
        return "admin/usuario";
    }
    
    @GetMapping("/admin/relatorio")
    public String adminRelatorio(Model model) {
        model.addAttribute("admin_relatorio", 123);
        return "admin/relatorio";
    }
    
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("admin_dashboard", 123);
        return "admin/dashboard";
    }
    
    @GetMapping("/admin/grupo")
    public String adminGrupo(Model model) {
        model.addAttribute("admin_grupo_listar", 123);
        return "admin/grupo/listar";
    }
    
    @GetMapping("/admin/grupo/")
    public String adminGrupoAtualizar(Model model) {
        model.addAttribute("admin_grupo_cadastrar", 123);
        return "admin/grupo/cadastrar";
    }
    
    @GetMapping("/empresa")
    public String criarEmpresa(Model model) {
        model.addAttribute("empresa", 123);
        return "empresa";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", 123);
        return "login";
    }
    
    @GetMapping("/recuperarsenha")
    public String recuperarsenha(Model model) {
        model.addAttribute("recuperarsenha", 123);
        return "recuperarsenha";
    }
    
    @GetMapping("/novasenha")
    public String novasenha(Model model) {
        model.addAttribute("novasenha", 123);
        return "novasenha";
    }
}