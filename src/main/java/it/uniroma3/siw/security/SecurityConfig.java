package it.uniroma3.siw.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("admin").password("admin").roles("ADMIN")
          .and()
          .withUser("user").password("user").roles("USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
          .antMatchers("/login*").permitAll()
          .antMatchers("/admin*").hasRole("ADMIN")
          .antMatchers("/add*").hasRole("ADMIN")
          .antMatchers("/show*").hasRole("ADMIN")
          .anyRequest().authenticated()		//tutti gli altri URL necessitano autenticazione
          .and()
          .formLogin()
          .loginPage("/login.html")
          .loginProcessingUrl("/login")
          .defaultSuccessUrl("/index.html")
          .failureUrl("/login.html?error=true")
          .and()
          .logout()
          .invalidateHttpSession(true)
          .logoutUrl("/logout")
          .logoutSuccessUrl("/login.html");
    }
    

}