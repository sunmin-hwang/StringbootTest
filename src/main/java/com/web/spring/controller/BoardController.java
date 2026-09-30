package com.web.spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.web.spring.dto.BoardReq;
import com.web.spring.dto.BoardRes;
import com.web.spring.service.BoardService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Tag(name = "Board API", description = "게시글 조회, 등록, 수정, 삭제 API")
public class BoardController {
	private final BoardService boardService;
	
	/**
	* 전체 게시물 조회
	* */
	@GetMapping
	@Operation(summary = "전체 게시글 조회")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(boardService.boardList(), HttpStatus.OK);
	}
	
	//특정한 사람이 작성한 게시물 조회
	@GetMapping("/member/{memberId}")
	@Operation(summary = "작성자별 게시글 조회")
	public ResponseEntity<?> getBoard(@PathVariable String memberId){
		return new ResponseEntity<>(boardService.getBoard(memberId),HttpStatus.OK);
	}
			
	/**
	 * 글번호에 해당하는 게시물 조회
	 * */
	@GetMapping("/{id}")
	@Operation(summary = "게시글 한 건 조회")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return new ResponseEntity<>(boardService.findBoard(id),HttpStatus.OK);
	}

	@GetMapping("/search")
	@Operation(summary = "제목 또는 내용으로 게시글 검색")
	public ResponseEntity<?> findByTitleOrContent(@RequestParam(name = "title", required = false, defaultValue = "") String title,
								   @RequestParam(name = "content", required = false, defaultValue = "") String content){
		return new ResponseEntity<>(boardService.findBoard(title, content),HttpStatus.OK);
	}
	
    /**
	 * 게시물 등록
	 * */
	@PostMapping
	@Operation(summary = "게시글 등록")
	public ResponseEntity<?> save(@RequestBody BoardReq board){
		return ResponseEntity.status(201).body(boardService.addBoard(board));
	}
	/**
	 * 글번호에 해당하는 게시물 수정
	 */
	@PutMapping("/{id}")
	@Operation(summary = "게시글 수정")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BoardReq board){
		return ResponseEntity.status(202).body(boardService.updateBoard(id, board));
	}
	/**
	 * 글번호에 해당하는 게시물 삭제
	 * */
	@DeleteMapping("/{id}")
	@Operation(summary = "게시글 삭제")
	public ResponseEntity<?> delete(@PathVariable Long id){		
		return ResponseEntity.status(200).body(boardService.deleteBoard(id));
	}
}
