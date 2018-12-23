package com.yuma.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.yuma.app.service.CustomUserDetailsService;

@ComponentScan
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

	@Bean
	public UserDetailsService mongoUserDetails() {
		return new CustomUserDetailsService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService userDetailsService = mongoUserDetails();
		auth
			.userDetailsService(userDetailsService).
			passwordEncoder(bCryptPasswordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
			.antMatchers("/user/register", "api/meals/all", "api/meals/{description}").permitAll()
			.antMatchers("api/meals/update", "api/meals/delete/{mealId}", "weeklycombo").hasAuthority("ADMIN").anyRequest()
			.authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
			.loginPage("/login").failureUrl("/login?error=true")
			.usernameParameter("email")
			.passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").and().exceptionHandling();
	}


}
