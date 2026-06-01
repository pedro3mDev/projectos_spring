package com.anluge.gestDoc.documento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.anluge.gestDoc.entitys.Documento;
import com.anluge.gestDoc.utils.Has;

@Service
public class DocumentoServiceImpl implements DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Override
    @Transactional
    public void salvar(DocumentoModel model, MultipartFile file) {
        Documento entidade = null;

        if (Has.content(model.getId())) {
            entidade = documentoRepository.findById(model.getId()).orElse(new Documento());
        } else {
            entidade = new Documento();
            entidade.setDataCriacao(new Date());
        }

        entidade.setNome(model.getNome());
        entidade.setArquivo(model.getArquivo());
        entidade.setDescricao(model.getDescricao());
        // entidade.setProprietario(model.getProprietario());
        entidade.setAtivo(model.getAtivo());
        entidade.setNivel(model.getNivel());
        entidade.setOrdem(model.getOrdem());
        entidade.setTipo(model.getTipo());
        entidade.setVersao(model.getVersao());
        // entidade.setDepartamento(model.getDepartamento());
//        entidade.setDocumentopaiid(model.getDocumentopaiid());
        entidade.setTamanho(model.getTamanho());
        entidade.setRevisao(model.getRevisao());
        documentoRepository.save(entidade);
    }

    @Override
    public DocumentoModel buscarParaEdicao(Integer id) {
        Documento entidade = documentoRepository.findById(id).orElse(new Documento());
        DocumentoModel model = new DocumentoModel();
        model.setId(entidade.getId());
        model.setNome(entidade.getNome());
        model.setArquivo(entidade.getArquivo());
        model.setDescricao(entidade.getDescricao());
        model.setDataCriacao(entidade.getDataCriacao());
        // model.setProprietario(entidade.getProprietario());
        model.setAtivo(entidade.getAtivo());
        model.setNivel(entidade.getNivel());
        model.setOrdem(entidade.getOrdem());
        model.setTipo(entidade.getTipo());
        model.setVersao(entidade.getVersao());
        // model.setDepartamento(entidade.getDepartamento());
//        model.setDocumentopaiid(entidade.getDocumentopaiid());
        model.setTamanho(entidade.getTamanho());
        model.setRevisao(entidade.getRevisao());
        return model;
    }

    @Override
    public List<DocumentoModel> listarDocumentos() {
        List<DocumentoModel> documentos = new ArrayList<DocumentoModel>();
        List<Documento> entidades = documentoRepository.findAll();

        for (Documento documento : entidades) {
            DocumentoModel model = new DocumentoModel();
            model.setId(documento.getId());
            model.setNome(documento.getNome());
            model.setArquivo(documento.getArquivo());
            model.setDescricao(documento.getDescricao());
            model.setDataCriacao(documento.getDataCriacao());
            // model.setProprietario(documento.getProprietario());
            model.setAtivo(documento.getAtivo());
            model.setNivel(documento.getNivel());
            model.setOrdem(documento.getOrdem());
            model.setTipo(documento.getTipo());
            model.setVersao(documento.getVersao());
            // model.setDepartamento(documento.getDepartamento());
//            model.setDocumentopaiid(documento.getDocumentopaiid());
            model.setTamanho(documento.getTamanho());
            model.setRevisao(documento.getRevisao());
            documentos.add(model);
        }

        return documentos;
    }
}
