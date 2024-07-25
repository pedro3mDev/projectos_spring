package grupo3mtech.ao.autenticacao.util;
import lombok.Data;
@Data
public class RespostaAutenticacao {
    private String jwt;
    public RespostaAutenticacao(String jwt) {
        this.jwt = jwt;
    }
}
