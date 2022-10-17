package com.example.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.slf4j.Slf4j;





@EnableWebSecurity
@Configuration
@Slf4j
public class WebSecurityConfigure{
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.cors()
		.and()
		.csrf()
		.disable()
		
		.authorizeRequests()
        .antMatchers("/member/**").authenticated()
        .antMatchers("/admin/**").authenticated()
        .antMatchers("/**").authenticated()
        .and()
		
		.formLogin()
//        .loginPage("/login")
        .defaultSuccessUrl("/")
        .permitAll()
        .and()

		.logout()
		        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .logoutSuccessUrl("/login")
		        .invalidateHttpSession(true)
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied");
		
		
		return http.build();
	}
	
	
	//passwordEncoder why need not public
	@Bean 
	PasswordEncoder  passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	
}
