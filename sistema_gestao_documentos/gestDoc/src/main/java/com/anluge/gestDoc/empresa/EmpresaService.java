package com.anluge.gestDoc.empresa;

import org.springframework.validation.BindingResult;

import com.anluge.gestDoc.autenticacao.CriarEmpresaModel;
import com.anluge.gestDoc.entitys.Empresa;
import com.anluge.gestDoc.utils.BusinessException;

public interface EmpresaService {

    BusinessException SEM_PERMISSAO_EXCEPTION = new BusinessException("Você não possui permissão no recurso.");

    Empresa buscarEmpresaSelecionada();

    void criarNovaEmpresa(CriarEmpresaModel model);

    void validarCriarNovaEmpresa(CriarEmpresaModel model, BindingResult result);

}
