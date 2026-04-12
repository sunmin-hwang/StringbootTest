package com.web.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.spring.dto.MemberRes;
import com.web.spring.entity.Member;
import com.web.spring.service.MemberService;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@PostMapping("/members")
	public String signUp(@RequestBody Member member) {
		memberService.signUp(member);
		return "OK";
	}
	
	@GetMapping("/members/{id}")
	public String duplicateCheck(@PathVariable String id) {
		System.out.println("ID ==> " + id);
		return memberService.duplicateCheck(id);
	}
	
	@PostMapping("/members/login")
	public MemberRes signIn(@RequestBody Member member) {
		return memberService.signIn(member.getId(), member.getPwd());
	}
	
}
