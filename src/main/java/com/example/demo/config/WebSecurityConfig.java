package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin()
				.defaultSuccessUrl("/security/whoami")
				.and()
                .authorizeRequests()
                .antMatchers("/add_plato.html","/admin_acciones.html","/admin_menu.html","/admin.html","/mod_pedidos.html","/mod_plato.html").authenticated()
				.anyRequest().permitAll()
				.and()
				.httpBasic()
                .and()
                .csrf().disable();
    
                
    }

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password(encoder.encode("admin"))
				.roles("ADMIN");
	}
}
