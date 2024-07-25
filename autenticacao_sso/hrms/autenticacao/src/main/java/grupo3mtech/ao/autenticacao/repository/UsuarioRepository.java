package grupo3mtech.ao.autenticacao.repository;

import grupo3mtech.ao.autenticacao.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String nomeUsuario);
}
