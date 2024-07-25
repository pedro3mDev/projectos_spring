package grupo3mtech.ao.autenticacao.controller;
import grupo3mtech.ao.autenticacao.util.RequisicaoAutenticacao;
import grupo3mtech.ao.autenticacao.util.RespostaAutenticacao;
import grupo3mtech.ao.autenticacao.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequisicaoAutenticacao requisicaoAutenticacao) {
        try {
            // Autenticar o usuário
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requisicaoAutenticacao.getNomeUsuario(),
                            requisicaoAutenticacao.getSenhaUsuario()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(requisicaoAutenticacao.getNomeUsuario());
            String jwt = jwtUtil.gerarToken(userDetails.getUsername(), userDetails.getAuthorities().iterator().next().getAuthority());
            return ResponseEntity.ok(new RespostaAutenticacao(jwt));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RespostaAutenticacao("Credenciais inválidas!"));
        }
    }
}
