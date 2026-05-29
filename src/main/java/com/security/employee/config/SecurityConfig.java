package com.security.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain security(HttpSecurity http) throws Exception {
	    return http.csrf(c -> c.disable())
	            .authorizeHttpRequests(auth -> auth
	                    .requestMatchers("/public/**", "/login", "/css/**", "/js/**").permitAll()
	                    .requestMatchers("/admin/**").hasRole("ADMIN")
	                    .requestMatchers("/employees/**").hasAnyRole("USER", "ADMIN")
	                    .anyRequest().authenticated())
	            
	            // Configure Form Login for UI
	            .formLogin(form -> form
	                    .loginPage("/login")                     
	                    .defaultSuccessUrl("/employees", true)   
	                    .permitAll())
	            
	            // Configure Logout Behavior 
	            .logout(logout -> logout
	                    .logoutUrl("/logout")
	                    .logoutSuccessUrl("/login?logout=true")
	                    .invalidateHttpSession(true)  
	                    .clearAuthentication(true)     
	                    .deleteCookies("JSESSIONID")   
	                    .permitAll())
	            
	     
	            .headers(headers -> headers
	                    .cacheControl(Customizer.withDefaults()) 
	            )
	            .build();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
