package com.example.controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LoginRequest;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {


	private final CustomUserDetailsService customUserDetailsService;
	private final UserRepository userRepository;
	

	@PostConstruct
	public void init() {
		if(userRepository.count() == 0) {
			User user = new User();
			user.setUsername("admin");
			user.setPassword("1234");
			user.setEnabled(true);
			user.setAuthority("ADMIN");
			
			customUserDetailsService.addUser(user);
		}
	}

	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUserPost(@Valid User user , BindingResult result, Model model){
		
		
		if(result.hasErrors()){
			return ResponseEntity.ok("Error");
		}
		List<User> userList = customUserDetailsService.getAllUsers(user.getUsername());
		
		for(int i=0; i<userList.size(); i++){
			if(user.getUsername().equals(userList.get(i).getUsername()) ){
				model.addAttribute("usernameMsg" , "username already exist" ); 
				return ResponseEntity.ok("username already exist");
			}
		}
		
		user.setEnabled(true);
		if(user.getUsername().equals("admin"))
			user.setAuthority("ROLE_ADMIN");
		else
			user.setAuthority("ROLE_USER");
		
		customUserDetailsService.addUser(user);
		
		return  ResponseEntity.ok("registerUserSuccess");
	 }

}