package com.web.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.spring.dto.MemberRes;
import com.web.spring.entity.Member;
import com.web.spring.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@Tag(name = "Member API", description = "회원 가입과 로그인 API")
public class MemberController {
	private final MemberService memberService;
	
	@PostMapping({"/members", "/signup"})
	@Operation(summary = "회원 가입")
	public String signUp(@RequestBody Member member) {
		memberService.signUp(member);
		return "OK";
	}
	
	@GetMapping("/members/{id}")
	@Operation(summary = "아이디 중복 확인")
	public String duplicateCheck(@PathVariable String id) {
		System.out.println("ID ==> " + id);
		return memberService.duplicateCheck(id);
	}
	
	@PostMapping("/members/login")
	@Operation(summary = "회원 로그인")
	public MemberRes signIn(@RequestBody Member member) {
		return memberService.signIn(member.getId(), member.getPwd());
	}
	
}
