package grupo3mtech.ao.dataJpa;
import grupo3mtech.ao.dataJpa.entity.Cliente;
import grupo3mtech.ao.dataJpa.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class DataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(DataJpaApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(DataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ClienteRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Cliente("Pedro", "Moniz"));
			repository.save(new Cliente("Ismael", "Correia"));
			repository.save(new Cliente("Denizia", "Cazequene"));

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.buscarPorNome("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");

			/*
			// fetch all customers
			repository.findAll().forEach(customer -> {
				System.out.println("Listar Todos");
				System.out.println(customer.toString());
			});

			repository.buscarPorNome("Pedro").forEach(bauer -> {
				System.out.println(bauer.toString());
			});
			*/

		};
	}
}
