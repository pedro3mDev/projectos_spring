package grupo3mtech.ao.rh.controller;

import grupo3mtech.ao.rh.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RhController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/verificarToken")
    public ResponseEntity<?> verificarToken(@RequestHeader("Authorization") String token) {
        try {
            // Certifique-se de que o token começa com "Bearer "
            if (!token.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token mal formatado");
            }
            String jwt = token.substring(7); // Remove o prefixo "Bearer "
            String username = jwtUtil.estrairNomeUsuario(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.verificaToken(jwt, username)) {
                String role = jwtUtil.estrair(jwt).get("role", String.class);
                if ("ROLE_USER_RH".equals(role) || "ROLE_USER_ADMIN".equals(role)) {
                    return ResponseEntity.ok("Logou com Sucesso");
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Role não autorizada");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Adicione isso para ver a pilha de erro
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }

}
