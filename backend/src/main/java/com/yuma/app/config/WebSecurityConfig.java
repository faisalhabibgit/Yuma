package com.yuma.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yuma.app.security.JwtAuthEntryPoint;
import com.yuma.app.security.JwtAuthFilter;
import com.yuma.app.service.CustomUserDetailsService;

@ComponentScan
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	securedEnabled = true,
	jsr250Enabled = true,
	prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

	@Autowired
	private JwtAuthEntryPoint unauthorizedHandler;

	@Bean
	public JwtAuthFilter jwtAuthFilter() {
		return new JwtAuthFilter();
	}

	@Bean
	public UserDetailsService mongoUserDetails() {
		return new CustomUserDetailsService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService userDetailsService = mongoUserDetails();
		auth
			.userDetailsService(userDetailsService).
			passwordEncoder(passwordEncoder());

	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.csrf()
			.disable()
			.exceptionHandling()
			.authenticationEntryPoint(unauthorizedHandler)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers("/",
				"/favicon.ico",
				"/**/*.png",
				"/**/*.gif",
				"/**/*.svg",
				"/**/*.jpg",
				"/**/*.html",
				"/**/*.css",
				"/**/*.js")
			.permitAll()
			.antMatchers("/api/auth/**")
			.permitAll()
			.antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/api/polls/**", "/api/users/**")
			.permitAll()
			.anyRequest()
			.authenticated();

		// Add our custom JWT security filter
		http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
