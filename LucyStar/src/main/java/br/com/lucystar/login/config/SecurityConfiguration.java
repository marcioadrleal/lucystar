package br.com.lucystar.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.lucystar.login.controller.GeneralController;
import br.com.lucystar.login.filter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private SecurityFilter userAuthenticationFilter;
	
	public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = { 
	   GeneralController.GROUP_USERS_REQUEST_MAPPING + GeneralController.ADD ,
	   GeneralController.GROUP_USERS_REQUEST_MAPPING + GeneralController.UPDATE
    };
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf -> csrf.disable())
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(req -> {
					req.requestMatchers(HttpMethod.POST, ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED[0]).permitAll();
					req.requestMatchers(HttpMethod.PUT, ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED[1]).permitAll();
					req.anyRequest().authenticated();
				}).addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
 
	
}
