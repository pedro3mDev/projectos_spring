package com.anluge.gestDoc.grupo;

import java.util.List;

import com.anluge.gestDoc.utils.BusinessException;

public interface GrupoService {

    void salvar(GrupoModel model);

    GrupoModel buscarParaEdicao(Integer id);

    List<String> buscarPermissoesDisponiveis();

    List<GrupoModel> listarGrupos();

    void empresaPossuiPermissao(Integer id) throws BusinessException;
}
