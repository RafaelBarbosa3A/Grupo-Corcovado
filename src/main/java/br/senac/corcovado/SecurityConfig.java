package br.senac.corcovado;

import br.senac.corcovado.controller.filter.JWTAuthenticationFilter;
import br.senac.corcovado.controller.filter.JWTLoginFilter;
import br.senac.corcovado.model.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 *
 * @author Diego
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    @Autowired private PessoaService pessoaService;
    @Autowired private PasswordEncoder passwordEncoder;

    @Bean @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(pessoaService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/","/css/**", "/js/**", "/images/**", "/console/**",
                            "/comercio/**", "/comercio#!/produtos/**", "/comercio#!/carrinho"
      
                    ).permitAll()
                    .antMatchers(HttpMethod.POST,"/auth/login").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .addFilterBefore(new JWTLoginFilter("/auth/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    */

    /* old but gold */
    @Autowired private PessoaService pessoaService;
    @Autowired private PasswordEncoder passwordEncoder;
    
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(pessoaService);
        authProvider.setPasswordEncoder(passwordEncoder);
        
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
            .and().headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable()
            .and().csrf().csrfTokenRepository(csrfTokenRepository())
            .and().authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/console/**",
                        "/comercio",
                        "/comercio/list", "/comercio/show", "/comercio/cart",
                        "/comercio/produto_json", 
                        "/comercio/carrinho_json/**",
                        "/comercio/login",
                        "/comercio/cadastro",
                        "/comercio/pessoa_json/add").permitAll()
                //.antMatchers("").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and().logout().logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID").permitAll()
            .and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
    }
    
    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}