package com.web.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.spring.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	// 모든 게시글 정보 받아오기...작성자 정보도 함께..findAll
	@Query(value = "select b from Board b join fetch b.member")
	List<Board> boardList();
	
	//특정한 작성자가 쓴 모든 게시문 정보를 검색
	@Query(value = "select b from Board b join fetch b.member m where m.id = :id")
	List<Board> getBoard(String id);

	List<Board> findByTitleOrContentContaining(String title, String content);

}
