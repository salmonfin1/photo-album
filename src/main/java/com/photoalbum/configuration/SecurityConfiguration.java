package com.photoalbum.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@Order(2)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private DaoAuthenticationProvider authProvider;

	@Autowired
	public SecurityConfiguration(DaoAuthenticationProvider authProvider) {
		this.authProvider = authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.httpBasic().and()
				.authorizeRequests()
				.antMatchers("/index.html", "/home.html", "/login.html","/login", "photo-album.html","/" ,"/bower_components/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());


	}


}
