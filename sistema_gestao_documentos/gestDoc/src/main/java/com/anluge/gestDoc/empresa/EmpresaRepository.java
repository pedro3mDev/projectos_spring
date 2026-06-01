package com.anluge.gestDoc.empresa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anluge.gestDoc.entitys.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer>  {

}
