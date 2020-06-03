package edu.zut.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		log.info("Konfiguracja Spring Security: dodawnie użytkownika.");
		
		auth.inMemoryAuthentication()
			.withUser("admin").password("{noop}admin").authorities("ROLE_USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		log.info("Konfiguracja Spring Security: określanie dostępu do ścieżek.");
		
		http.authorizeRequests()
			.antMatchers("/user/**").access("hasRole('ROLE_USER')")
			.antMatchers("/", "/**").access("permitAll")
			.and().formLogin().loginPage("/login")
			.and().logout().logoutSuccessUrl("/");
		
		// access to h2-console
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
