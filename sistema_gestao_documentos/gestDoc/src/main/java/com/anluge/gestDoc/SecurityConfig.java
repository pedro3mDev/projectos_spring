package com.anluge.gestDoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import com.anluge.gestDoc.usuario.UsuarioService;
import com.anluge.gestDoc.utils.Find;
import com.anluge.gestDoc.utils.Has;
import com.anluge.gestDoc.utils.MappingPermissaoRepository;
import com.anluge.gestDoc.utils.PermissaoRepository;
import com.anluge.gestDoc.utils.When;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Autowired
    private UsuarioService usuarioService;

    public static final String[] ENDPOINTS_WHITELIST = {
                    "/content/**",
                    "/",
                    "/login",
                    "/criar-empresa",
                    "/ativar-conta/**",
                    "recuperar-senha",
                    "recuperar-senha/**"
    };
    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    public static final String DEFAULT_SUCCESS_URL = "/admin/dashboard";
    public static final String USERNAME = "email";
    public static final String PASSWORD = "senha";

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PermissaoRepository permissaoRepository() {
        MappingPermissaoRepository repo = new MappingPermissaoRepository();
        repo.addPermissoes(handlerMapping);
        return repo;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        // provider.setSaltSource(service);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(authenticationProvider())
            .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        registrarAuthority(http);
        http.authorizeHttpRequests(request -> request.requestMatchers(ENDPOINTS_WHITELIST).permitAll().anyRequest().authenticated())
            .csrf().disable()
            .formLogin(form -> form
                .loginPage(LOGIN_URL)
                .loginProcessingUrl(LOGIN_URL)
                .failureUrl(LOGIN_FAIL_URL)
                .usernameParameter(USERNAME)
                .passwordParameter(PASSWORD)
                .defaultSuccessUrl(DEFAULT_SUCCESS_URL))
            .logout(logout -> logout
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl(LOGIN_URL + "?logout"))
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/logout")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false));
        return http.build();
    }

    private void registrarAuthority(HttpSecurity http) throws Exception {
        List<RequestMappingInfo> mappings = new ArrayList<>(handlerMapping.getHandlerMethods().keySet());
        
        mappings.removeAll(Find.all(mappings, new When<RequestMappingInfo>() {
            @Override
            public boolean found(RequestMappingInfo e) {
                return e.getName() == null;
            }
        }));
        
        
        Collections.sort(mappings, new Comparator<RequestMappingInfo>() {
            @Override
            public int compare(RequestMappingInfo o1, RequestMappingInfo o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        
        for (RequestMappingInfo rmi : mappings) {
            Set<RequestMethod> metodos = rmi.getMethodsCondition().getMethods();
            Set<PathPattern> patterns = rmi.getPathPatternsCondition().getPatterns();
            
            if (Has.content(metodos)) {
                for (RequestMethod requestMethod : metodos) {
                    for (PathPattern pattern : patterns) {
                        if(Has.content(requestMethod)) {
                            http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.valueOf(requestMethod.name()), pattern.getPatternString()).hasAuthority(rmi.getName()));
                        }else {
                            http.authorizeHttpRequests(request -> request.requestMatchers(pattern.getPatternString()).hasAuthority(rmi.getName()));
                        }
                    }
                }
            }
        }
    }
}