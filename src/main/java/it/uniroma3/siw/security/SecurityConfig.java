package it.uniroma3.siw.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
          	  .antMatchers("/static/**").permitAll()
	          .antMatchers("/index*").permitAll()
	          .antMatchers("/admin*").hasRole("ADMIN")
	          .antMatchers("/add*").hasRole("ADMIN")
	          .antMatchers("/show*").hasRole("ADMIN")
	          //.anyRequest().authenticated()		//tutti gli altri URL necessitano autenticazione
	          .and()
          .formLogin()
	          .loginPage("/login")
	          .permitAll()
	          .loginProcessingUrl("/login")
	          .defaultSuccessUrl("/dash.html")
	          .and()
          .logout()
	          .permitAll()
        	  .and();
    }
    

}