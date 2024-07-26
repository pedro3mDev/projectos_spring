package grupo3mtech.ao.pagamento.util;

import grupo3mtech.ao.pagamento.constants.Constantes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtUtil {

    private final Set<String> listaTokensUsados = new HashSet<>();

    public String gerarToken(String nomeUsuario, String role) {
        return Jwts.builder()
                .setSubject(nomeUsuario)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SignatureAlgorithm.HS256, Constantes.CHAVESECRETA)
                .compact();
    }

    public Claims extrair(String token) {
        return Jwts.parser()
                .setSigningKey(Constantes.CHAVESECRETA)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extrairNomeUsuario(String token) {
        return extrair(token).getSubject();
    }

    public boolean verificaToken(String token, String username) {
        return (username.equals(extrairNomeUsuario(token)) && !expiracaoToken(token) && !usadoToken(token));
    }

    private boolean expiracaoToken(String token) {
        return extrair(token).getExpiration().before(new Date());
    }

    private boolean usadoToken(String token) {
        return listaTokensUsados.contains(token);
    }

    public void carregaListaTokensUsados(String token) {
        listaTokensUsados.add(token);
    }
}
