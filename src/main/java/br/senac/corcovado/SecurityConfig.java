/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.senac.corcovado;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Diego
 */


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*
    @Autowired private PessoaService pessServ;
    @Autowired private DataSource dataSource;
    */
    
    private static PasswordEncoder basicPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence cs) {
                return cs.toString();
            }

            @Override
            public boolean matches(CharSequence cs, String string) {
                return string.equals(cs.toString());
            }
        };
    }

    public static PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean 
    public PasswordEncoder passwordEncoder() {
        return bcryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().anyRequest().permitAll();
        
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/console/**",
                        "/comercio/**",
                        "/comercio#!/produtos/**",
                        "/comercio#!/carrinho",
                        //"/comercio#!/finaliza",
                        "/comercio#!/signup").permitAll()
                //.antMatchers("/xpto/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage("/comercio#!/login")
                        .usernameParameter("email")
                        .passwordParameter("senha")
                        .defaultSuccessUrl("/comercio#!/produtos")
                        .failureUrl("/comercio#!/login?error=true")
                        .permitAll()
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/comercio#!/login?logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
        
    }

    /*
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().anyRequest();
    }
    /**/
}