package com.anluge.gestDoc.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anluge.gestDoc.entitys.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    Usuario findByEmailAndToken(String email, String token);
   
    @Query(value = "select * from usuario where lower(email) = lower(?1) and ativo = true order by id limit 1", nativeQuery = true)
    Usuario findByEmailIgnoreCaseAndAtivoTrue(String email);

    @Query(value = "select u.* from usuario u inner join empresa_usuario eu ON u.id = eu.usuarioid where eu.ativo is true and eu.empresaid = ?1 ", nativeQuery = true)
    List<Usuario> findByEmpresa(Integer empresa);
}
