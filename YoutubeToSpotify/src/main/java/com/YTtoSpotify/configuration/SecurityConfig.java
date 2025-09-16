package com.YTtoSpotify.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	    http
	    .csrf(csrf -> csrf.disable())
	    .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/","/index.html","/login**").permitAll()
	            .requestMatchers("/youtube/**").permitAll()
	            .anyRequest().authenticated())
	    .oauth2Login(oauth2 -> oauth2
	            .loginPage("/oauth2/authorization/spotify")
	            .defaultSuccessUrl("/home",true))
	    ;	    
	    return http.build();
	}
}