package com.ezen.SelfProject.service;

import java.util.List;

import com.ezen.SelfProject.domain.BoardDTO;
import com.ezen.SelfProject.domain.BoardVO;
import com.ezen.SelfProject.domain.PagingVO;
import com.ezen.SelfProject.domain.UserVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(int bno);

	int modify(BoardVO bvo, UserVO user);

	int remove(BoardVO bvo, UserVO user);

	List<BoardVO> getList(PagingVO pgvo);

	List<BoardVO> getListPage(PagingVO pgvo);
	
	int getTotalCount(PagingVO pgvo);

	//int register(BoardDTO bdto);

	BoardDTO getDetailFile(int bno);

//	int register(BoardDTO bdto);


//	int remove(int bno, UserVO user);

}