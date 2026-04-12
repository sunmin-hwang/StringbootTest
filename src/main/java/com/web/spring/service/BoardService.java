package com.web.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.spring.dto.BoardReq;
import com.web.spring.dto.BoardRes;
import com.web.spring.entity.Board;
import com.web.spring.exception.BoardSearchNotException;
import com.web.spring.exception.DMLException;
import com.web.spring.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	//addBoard()
	@Transactional
	public BoardRes addBoard(BoardReq boardReq) {
		System.out.println("BoardReq==>" + boardReq);
		Board board = boardReq.toBoard(boardReq);
		System.out.println("toBoard==>" + board);
		
		return new BoardRes(boardRepository.save(board));
	}
	
	//findBoard(Long id)
	/*
	 하나의 게시글 못 찾으면 예외처리..
	 찾으면 Board를 바로 반환
	 */
	@Transactional(readOnly = true)
	public BoardRes findBoard(Long id) throws BoardSearchNotException {
		return new BoardRes(boardRepository.findById(id).orElseThrow(()->new BoardSearchNotException("게시글 번호를 확인하세요", "Not Found BoardSearchNot~!!!")));
	}

	@Transactional(readOnly = true)
	public List<BoardRes> findBoard(String title, String content) throws BoardSearchNotException {
		List<Board> boards = boardRepository.findByTitleOrContentContaining(title, content);

		if (boards.isEmpty()) {
			throw new IllegalArgumentException("해당 게시글이 없습니다.");
		}

		return boards.stream().map(BoardRes::new).collect(Collectors.toList());
	}

	//getBoard(String memberId)
	/*
	 특정회원이 작성한 게시글 못 찾으면 예외처리..
	 찾으면 List<Board>를 바로 반환
	 */
	@Transactional(readOnly = true)
	public List<BoardRes> getBoard(String memberId) throws BoardSearchNotException {
		List<Board> list = boardRepository.getBoard(memberId);
		if(list==null || list.isEmpty())
			throw new BoardSearchNotException("특정 회원이 작성한 글이 없습니다.", "Not Found BoardAll~!!!");
		else
			return list.stream().map(BoardRes::new).collect(Collectors.toList());
	}

	//findAll....성능상 안좋다...boardList()
	@Transactional(readOnly = true)
	public List<BoardRes> boardList() throws BoardSearchNotException {
		List<Board> list = boardRepository.boardList();
		if(list==null || list.isEmpty()) throw new BoardSearchNotException("전체 게시글이 없습니다.", "Not Found BoardAll~!!!");
		else return list.stream().map(BoardRes::new).collect(Collectors.toList());
	}
	
	//updateBoard(Long id, BoardReq board)...하나의 게시물 받아와서..필드변경....Entity | Snapshot
	@Transactional
	public BoardRes updateBoard(Long id, BoardReq board) throws DMLException {
		Board boardEntity = boardRepository.findById(id).orElseThrow(()-> new DMLException("글번호 오류로 수정에 실패했습니다.", "Not Update"));
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		return new BoardRes(boardEntity);
	}
	
	//deleteBoard(Long id)
	@Transactional
	public String deleteBoard(Long id) throws DMLException {
		Board boardEntity = boardRepository.findById(id).orElseThrow(()-> new DMLException("글번호 오류로 삭제에 실패했습니다.", "Not Delete", HttpStatus.BAD_REQUEST));
		boardRepository.delete(boardEntity);
		return "OK";
	}
}
