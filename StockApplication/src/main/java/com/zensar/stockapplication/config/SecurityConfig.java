package com.zensar.stockapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("tom")
		.password("tom@123")
		.roles("ADMIN")
		.and()
		.withUser("jerry")
		.password("jerry@123")
		.roles("USER");
		
	}
	@Bean
 public PasswordEncoder passwordEncoder() {
	return NoOpPasswordEncoder.getInstance();
	 
 }
	
	
	
}
