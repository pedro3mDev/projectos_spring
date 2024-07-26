package grupo3mtech.ao.rh.config;
import grupo3mtech.ao.rh.util.FiltrarRequisicaoJwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSegurancaRH {

    private final FiltrarRequisicaoJwt filtrarRequisicaoJwt;

    public ConfiguracaoSegurancaRH(FiltrarRequisicaoJwt filtrarRequisicaoJwt) {
        this.filtrarRequisicaoJwt = filtrarRequisicaoJwt;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("garcia")
                .password("{noop}password")
                .roles("USER_PAGAMENTOS")
                .build();

        return new InMemoryUserDetailsManager(user);
    }


    @Bean
    public SecurityFilterChain filtroSeguranca(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(filtrarRequisicaoJwt, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
