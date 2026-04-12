package com.web.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.spring.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	//@Query(value = "SELECT m FROM Member m WHERE m.id = :id")
	@Query(value = "SELECT m FROM Member m WHERE m.id = ?1")
	//Member duplicateCheck(@Param("id") String id);
	Member duplicateCheck(String id);
	
	@Query(value = "SELECT m FROM Member m WHERE m.id = :#{#m.id} AND m.pwd = :#{#m.pwd}")
	Member login(Member m);
	
}
