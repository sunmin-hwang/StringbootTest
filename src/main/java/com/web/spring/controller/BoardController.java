package com.web.spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.spring.dto.BoardReq;
import com.web.spring.dto.BoardRes;
import com.web.spring.service.BoardService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	/**
	* 전체 게시물 조회
	* */
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(boardService.boardList(), HttpStatus.OK);
	}
	
	//특정한 사람이 작성한 게시물 조회
	@GetMapping("/member/{memberId}")
	public ResponseEntity<?> getBoard(@PathVariable String memberId){
		return new ResponseEntity<>(boardService.getBoard(memberId),HttpStatus.OK);
	}
			
	/**
	 * 글번호에 해당하는 게시물 조회
	 * */
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return new ResponseEntity<>(boardService.findBoard(id),HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<?> findByTitleOrContent(@RequestParam(name = "title", required = false, defaultValue = "") String title,
								   @RequestParam(name = "content", required = false, defaultValue = "") String content){
		return new ResponseEntity<>(boardService.findBoard(title, content),HttpStatus.OK);
	}
	
    /**
	 * 게시물 등록
	 * */
	@PostMapping
	public ResponseEntity<?> save(@RequestBody BoardReq board){
		return ResponseEntity.status(201).body(boardService.addBoard(board));
	}
	/**
	 * 글번호에 해당하는 게시물 수정
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BoardReq board){
		return ResponseEntity.status(202).body(boardService.updateBoard(id, board));
	}
	/**
	 * 글번호에 해당하는 게시물 삭제
	 * */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){		
		return ResponseEntity.status(200).body(boardService.deleteBoard(id));
	}
}
