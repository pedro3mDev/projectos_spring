package com.anluge.gestDoc.departamento;

import java.util.List;

import com.anluge.gestDoc.utils.BusinessException;

public interface DepartamentoService {

    void salvar(DepartamentoModel model);

    DepartamentoModel buscarParaEdicao(Integer id);

    List<DepartamentoModel> listarDepartamentos();

    void empresaPossuiPermissao(Integer id) throws BusinessException;
}
