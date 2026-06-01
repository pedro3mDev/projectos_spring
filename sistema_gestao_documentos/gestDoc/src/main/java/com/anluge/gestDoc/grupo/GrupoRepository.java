package com.anluge.gestDoc.grupo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anluge.gestDoc.entitys.Empresa;
import com.anluge.gestDoc.entitys.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

    List<Grupo> findByEmpresa(Empresa emp);
}
