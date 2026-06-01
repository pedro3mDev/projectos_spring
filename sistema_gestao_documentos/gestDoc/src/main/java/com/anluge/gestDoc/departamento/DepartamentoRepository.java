package com.anluge.gestDoc.departamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anluge.gestDoc.entitys.Departamento;
import com.anluge.gestDoc.entitys.Empresa;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer>{

    List<Departamento> findByEmpresa(Empresa emp);
}
