package com.example.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Repository.MemberRepository;
import com.example.model.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not found user" + username));

        log.info("Success find member {}", member);

        return User.builder()
                .username(member.getMemberId())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles("USER")
                .build();
    }

}
