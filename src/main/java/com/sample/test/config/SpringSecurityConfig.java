package com.sample.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sample.test.serviceImpl.EmployeeServiceImpl;
import com.sample.test.util.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	@Autowired
	AuthenticationProvider authenticationProvider;
	
	@Autowired
	JWTAuthenticatorEntryPoint authenticatorEntryPoint;
	

	// Constructor injection for required dependencies
	@Autowired
	public SpringSecurityConfig(JwtAuthFilter jwtAuthFilter) {
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// Disable CSRF (not needed for stateless JWT)
				.csrf(csrf -> csrf.disable())
				.cors(cors->cors.disable())
				// Configure endpoint authorization
				.authorizeHttpRequests(auth -> auth
						// Public endpoints
						.requestMatchers("/auth/welcome", "/auth/addNewUser", "/auth/generateTokenV1","/addEmployees","/**").permitAll()

						// Role-based endpoints
						.requestMatchers("/auth/user/**").hasAuthority("ROLE_USER")
						.requestMatchers("/auth/admin/**").hasAuthority("ROLE_ADMIN")

						// All other endpoints require authentication
						.anyRequest().authenticated())
				.exceptionHandling(ex->ex.authenticationEntryPoint(authenticatorEntryPoint))// for any exception handling
				// Stateless session (required for JWT)
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Set custom authentication provider
				.authenticationProvider(authenticationProvider)

				// Add JWT filter before Spring Security's default filter
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	/*
	 * Authentication manager bean Required for programmatic authentication (e.g.,
	 * in /generateToken)
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
