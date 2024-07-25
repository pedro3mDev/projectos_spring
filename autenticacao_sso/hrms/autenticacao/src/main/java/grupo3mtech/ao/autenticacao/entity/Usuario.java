package grupo3mtech.ao.autenticacao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nomeUsuario")
    private String username;
    @Column(name = "senhasuario")
    private String password;
}
