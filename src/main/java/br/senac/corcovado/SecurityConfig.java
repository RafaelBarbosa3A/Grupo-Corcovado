package br.senac.corcovado;

import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.service.AuthService;
import br.senac.corcovado.model.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Diego
 */
@Configuration
@EnableWebSecurity
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
    } */

    /* old but gold */
    @Autowired private PessoaService pessoaService;
    @Autowired private PasswordEncoder passwordEncoder;
    
    /*
    private static PasswordEncoder basicPasswordEncoder() {
        return new PasswordEncoder() {
            @Override public String encode(CharSequence cs) {
                return cs.toString();
            }

            @Override public boolean matches(CharSequence cs, String string) {
                return string.equals(cs.toString());
            }
        };
    }
    */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(pessoaService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable()
            .and()
            .authorizeRequests()
                .antMatchers("/comercio/carrinho/**", "/comercio/venda/**", "/comercio/recibo/**").authenticated()
                .antMatchers("/", "/css/**", "/js/**", "/images/**", "/console/**",
                        "/comercio/**", "/cadastro/**").permitAll()
                .anyRequest().hasRole("ADMIN")
            .and()
                .formLogin()
                    .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("senha")
                        .defaultSuccessUrl("/comercio")
                        .failureUrl("/login?erro=true")
                        .permitAll()
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/comercio")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
            
    }
    /**/
}