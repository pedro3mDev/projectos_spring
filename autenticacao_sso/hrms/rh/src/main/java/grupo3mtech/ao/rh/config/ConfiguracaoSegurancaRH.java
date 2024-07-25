package grupo3mtech.ao.rh.config;

import grupo3mtech.ao.rh.constants.Constantes;
import grupo3mtech.ao.rh.util.FiltrarRequisicaoJwtRH;
import grupo3mtech.ao.rh.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSegurancaRH {

    @Bean
    public SecurityFilterChain filtroSeguranca(HttpSecurity http, JwtUtil jwtUtil, UserDetailsService userDetailsService) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().hasAnyRole("USER_RH", "ADMIN")
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new FiltrarRequisicaoJwtRH(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder userBuilder = User.withUsername(Constantes.NOME_USUARIO2);
        userBuilder.password("{noop}password");
        userBuilder.roles("USER_RH");
        return new InMemoryUserDetailsManager(userBuilder.build());
    }
}
