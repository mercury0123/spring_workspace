package com.ezen.myProject.service;

import java.util.List;

import com.ezen.myProject.domain.BoardDTO;
import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.PagingVO;
import com.ezen.myProject.domain.UserVO;

public interface BoardService {

// 기본적인 CRUD 구현	
	int register(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(int bno);

	int modify(BoardVO bvo, UserVO user);

	int remove(BoardVO bvo, UserVO user);

	List<BoardVO> getList(PagingVO pgvo);

	List<BoardVO> getListPage(PagingVO pgvo);
	
	int getTotalCount(PagingVO pgvo);
	
// 이하부터 파일업로드 시작

	int register2(BoardDTO bdto);
	
	BoardDTO getDetailFile(int bno);
	
	int modify(BoardDTO boardDTO, UserVO user);
	
//	int remove(int bno, UserVO user);

}
