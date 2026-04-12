package com.web.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.web.spring.entity.Member;
import com.web.spring.repository.BoardRepository;
import com.web.spring.repository.MemberRepository;
import com.web.spring.service.MemberService;

@SpringBootApplication
public class springbootTest implements CommandLineRunner {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		/*
		Member m = Member.builder().id("hong").pwd("1234").address("종각").name("홍종각").regDate(LocalDateTime.now()).build();
		memberRepository.save(m);
		*/
		
		/*
		Member m = memberRepository.duplicateCheck("hong");
		System.out.println(m);
		*/
		
		/*
		Member m = Member.builder().id("hong").pwd("1234").build();
		Member fm = memberRepository.login(m);
		System.out.println(fm);
		*/
		
		/*
		Member m = Member.builder().id("hong").pwd("1234").address("종각2").name("홍종각2").build();
		memberService.signUp(m);
		*/
		
		/*
		boardRepository.boardList().forEach(b->{
			System.out.println(b);
			System.out.println(b.getMember());
		});

		boardRepository.findAll().forEach(b->{
			System.out.println(b);
			System.out.println(b.getMember());
		});
		*/
		
		/*
		boardRepository.getBoard("hong").forEach(b->{
			System.out.println(b);
			System.out.println(b.getMember());
		});
		*/
	}
	
	public static void main(String[] args) {
		SpringApplication.run(springbootTest.class, args);
	}

}
