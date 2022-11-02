package com.example.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.model.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 List<User> user =  userRepository.findByUsername(username);
		 User targetUser = user.get(0);
		 Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		 grantedAuthorities.add(new SimpleGrantedAuthority(targetUser.getAuthority()));
		 return new org.springframework.security.core.userdetails.User(targetUser.getUsername(), targetUser.getPassword(), grantedAuthorities);
	}

	public List<User> getAllUsers(String username) throws UsernameNotFoundException{
		List<User> users =  userRepository.findByUsername(username);
		return users;
	}

	public void addUser(@Validated User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String originPassword = user.getPassword();
		user.setPassword(passwordEncoder.encode(originPassword));
		userRepository.saveAndFlush(user);
	}
}
