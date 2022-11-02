package com.example.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;




@Configuration
@EnableWebSecurity // springSecurityFilterChain가 자동으로 포함
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	private final CustomUserDetailsService customUserDetailsService;
	



	@Override
	public void configure(WebSecurity web) throws Exception {
		/*web.ignoring().antMatchers("/resources/**");*/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").hasRole("USER")
				.antMatchers("/login").permitAll()
				.antMatchers("/projects/add").hasRole("ADMIN")
				.antMatchers("/projects/update").hasRole("ADMIN")
				.and()

				.formLogin() /* 로그인 폼 나오도록 */
//				.loginPage("/login") /* 커스텀 로그인 URI 지정 */
				.defaultSuccessUrl("/")
				.usernameParameter("username") /* username 을 대체할 아이디 param default username */
				.passwordParameter("password") /* password 를 대체할 패스워드 param default password */
				.permitAll() /* 모두 오픈 ( 반대는 denyAll() ) */
				.and().logout().permitAll().logoutSuccessUrl("/") /* 로그아웃 성공시 리다이렉트 url */
				.and().csrf().disable();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptpasswordEncoder());

	}
	
	@Bean 
	public PasswordEncoder bCryptpasswordEncoder() { 
		return new BCryptPasswordEncoder(); 
	} 


}
