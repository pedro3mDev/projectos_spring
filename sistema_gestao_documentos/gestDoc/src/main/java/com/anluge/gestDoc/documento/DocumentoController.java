package com.anluge.gestDoc.documento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @GetMapping("")
    public ModelAndView listar(Model model) {
        return new ModelAndView("admin/documento/listagem-documento")
            .addObject("documentos", documentoService.listarDocumentos());
    }

    @GetMapping("/")
    public ModelAndView criarDocumento(Model model) {
        return new ModelAndView("admin/documento/edicao-documento").addObject("documento", null);
    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        return new ModelAndView("admin/documento/edicao-documento")
            .addObject("documento", documentoService.buscarParaEdicao(id));
    }

    @PostMapping(value = { "/", "{id}" })
    public ModelAndView salvarDocumento(@ModelAttribute DocumentoModel model, MultipartFile file) {
        documentoService.salvar(model, file);
        return new ModelAndView("redirect:/admin/documento");
    }
}