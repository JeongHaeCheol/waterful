package com.example.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.Repository.MemberRepository;
import com.example.model.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	@PostConstruct
	private void init() {
		if(memberRepository.count() == 0) {
			Member member = new Member();
			member.setMemberId("SunFe");
			member.setPassword("test");
			memberRepository.save(member);
		}
		
	}

}
