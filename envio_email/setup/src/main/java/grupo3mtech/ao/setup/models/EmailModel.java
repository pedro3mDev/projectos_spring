package grupo3mtech.ao.setup.models;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;
import grupo3mtech.ao.setup.enums.StatusEmail;
import org.hibernate.annotations.GeneratorType;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_EMAIL")
public class EmailModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long email;
    private String ownnerRef;
    private String emailfrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail StatusEmail;

}
