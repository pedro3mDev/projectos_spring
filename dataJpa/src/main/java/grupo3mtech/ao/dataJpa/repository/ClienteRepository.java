package grupo3mtech.ao.dataJpa.repository;

import grupo3mtech.ao.dataJpa.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository <Cliente, Long> {
    List <Cliente> buscarPorNome(String nome);
    Cliente buscarId(Long id);
}
