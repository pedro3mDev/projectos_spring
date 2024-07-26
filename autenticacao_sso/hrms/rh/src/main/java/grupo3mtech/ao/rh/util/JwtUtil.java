package grupo3mtech.ao.rh.util;

import grupo3mtech.ao.rh.constants.Constantes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtUtil {

    private final Set<String> listaTokensUsados = new HashSet<>();

    @Autowired
    private UserDetailsService userDetailsService;

    public String gerarToken(String nomeUsuario, String role) {
        return Jwts.builder()
                .setSubject(nomeUsuario)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, Constantes.CHAVESECRETA)
                .compact();
    }

    public Claims estrair(String token) {
        return Jwts.parser()
                .setSigningKey(Constantes.CHAVESECRETA)
                .parseClaimsJws(token)
                .getBody();
    }

    public String estrairNomeUsuario(String token) {
        return estrair(token).getSubject();
    }

    public boolean verificaToken(String token, String username) {
        try {
            return (username.equals(estrairNomeUsuario(token)) && !espiracaoToken(token) && !usadoToken(token));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private boolean espiracaoToken(String token) {
        return estrair(token).getExpiration().before(new Date());
    }

    private boolean usadoToken(String token) {
        return listaTokensUsados.contains(token);
    }

    public void carregaListaTokensUsados(String token) {
        listaTokensUsados.add(token);
    }


    public UserDetailsService getUserDetails(String username) {
        return (UserDetailsService) userDetailsService.loadUserByUsername(username);
    }


}

