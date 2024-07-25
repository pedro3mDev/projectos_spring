package grupo3mtech.ao.setup.repositories;
import grupo3mtech.ao.setup.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, Long> {

}
