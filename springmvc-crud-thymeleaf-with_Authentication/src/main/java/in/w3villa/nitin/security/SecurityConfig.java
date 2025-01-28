package in.w3villa.nitin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import in.w3villa.nitin.service.IEmployeeService;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {


	private final CustomAuthenticationProvider customAuthenticationProvider;

	public SecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
		this.customAuthenticationProvider = customAuthenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.authenticationProvider(customAuthenticationProvider)
				.build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/employee/login","/employee/signup","/signup").permitAll()
				.anyRequest().authenticated()
				)
		.formLogin(form -> form
				.loginPage("/employee/login")
				.loginProcessingUrl("/perform")
				.defaultSuccessUrl("/employee/showForm", true)
				.failureUrl("/employee/login?error=true")
				.permitAll()
				)
		.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/employee/login?logout=true")
				);
		return http.build();
	}
}
