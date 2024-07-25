package grupo3mtech.ao.rh.util;

import lombok.Data;

@Data
public class RespostaAutenticacao {
    private String mensagem;
    private String jwt;

    public RespostaAutenticacao(String mensagem, String jwt) {
        this.mensagem = mensagem;
        this.jwt = jwt;
    }
}
