package grupo3mtech.ao.autenticacao.config;

import grupo3mtech.ao.autenticacao.constants.Constantes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca {

    @Bean
    public SecurityFilterChain filtroSeguranca(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public UserDetailsService detalhesUsuario() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username(Constantes.NOME_USUARIO1).password(Constantes.SENHA_USUARIO1).roles("USER_ADMIN").build());
        manager.createUser(User.withDefaultPasswordEncoder().username(Constantes.NOME_USUARIO2).password(Constantes.SENHA_USUARIO2).roles("USER_RH").build());
        manager.createUser(User.withDefaultPasswordEncoder().username(Constantes.NOME_USUARIO3).password(Constantes.SENHA_USUARIO3).roles("USER_PAGAMENTOS").build());
        manager.createUser(User.withDefaultPasswordEncoder().username(Constantes.NOME_USUARIO4).password(Constantes.SENHA_USUARIO4).roles("USER_PAGAMENTOS").build());
        return manager;
    }

    @Bean
    public AuthenticationManager gerenciadorAutenticacao(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
