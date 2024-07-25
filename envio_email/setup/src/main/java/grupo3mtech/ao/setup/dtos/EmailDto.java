package grupo3mtech.ao.setup.dtos;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmailDto {
    @NotBlank
    @NotEmpty
    @Email
    private String ownnerRef;
    @NotBlank
    @Email
    private String emailfrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;
}
