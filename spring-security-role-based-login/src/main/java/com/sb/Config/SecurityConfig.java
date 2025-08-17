package com.sb.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/register").permitAll()
						.requestMatchers("/welcome.html").permitAll()
						.requestMatchers("/").permitAll()
						.requestMatchers("/admin").hasRole("ADMIN")
						.requestMatchers("/user").hasRole("USER")
						.anyRequest()
						.authenticated())
				  .formLogin(form -> form
			                .loginPage("/login.html")     // use your custom login page
			                .loginProcessingUrl("/login") // form action in login.html
			                .defaultSuccessUrl("/postLogin", true) // after login redirect
			                .permitAll()
			            )
				.logout(logout -> logout
					    .logoutUrl("/logout")              // endpoint Spring handles
					    .logoutSuccessUrl("/welcome.html")  // after logout, redirect here
//					    .permitAll()  all can access logout page
					);
		return httpSecurity.build();
	}
	
//	@Bean
//	public BCryptPasswordEncoder passwordencoder() {
//		return new BCryptPasswordEncoder();
//		
//	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user = User.withUsername("user")
				.password("{noop}user123")
				.roles("USER")
				.build();
		
		UserDetails admin = User.withUsername("admin")
				.password("{noop}admin123")
                .roles("ADMIN")
                .build();
		
		UserDetails useradmin = User.withUsername("useradmin")
				.password("{noop}useradmin123")
                .roles("USER", "ADMIN")
                .build();
		
		return new InMemoryUserDetailsManager(admin,user,useradmin);		
		
	}

}
