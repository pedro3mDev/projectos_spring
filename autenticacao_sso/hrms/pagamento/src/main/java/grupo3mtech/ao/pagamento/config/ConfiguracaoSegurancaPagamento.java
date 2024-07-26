package grupo3mtech.ao.pagamento.config;

import grupo3mtech.ao.pagamento.util.FiltrarRequisicaoJwt;
import grupo3mtech.ao.pagamento.util.FiltrarRequisicaoJwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSegurancaPagamento {

    private final FiltrarRequisicaoJwt filtrarRequisicaoJwt;

    public ConfiguracaoSegurancaPagamento(FiltrarRequisicaoJwt filtrarRequisicaoJwt) {
        this.filtrarRequisicaoJwt = filtrarRequisicaoJwt;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("garcia").password("{noop}senha").roles("USER");
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
