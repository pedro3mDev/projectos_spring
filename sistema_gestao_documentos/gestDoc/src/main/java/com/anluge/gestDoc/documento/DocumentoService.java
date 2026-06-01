package com.anluge.gestDoc.documento;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentoService {

    void salvar(DocumentoModel model, MultipartFile file);

    DocumentoModel buscarParaEdicao(Integer id);

    List<DocumentoModel> listarDocumentos();
}
