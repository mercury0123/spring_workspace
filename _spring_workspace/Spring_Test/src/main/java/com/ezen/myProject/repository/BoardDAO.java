package com.ezen.myProject.repository;

import java.util.List;

import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.PagingVO;

public interface BoardDAO {

	int insertBoard(BoardVO bvo);

	List<BoardVO> selectBoardList();

	int readCount(int bno);

	BoardVO selectBoardDetail(int bno);

	int updateBoard(BoardVO bvo);

	int deleteBoard(BoardVO bvo);

	List<BoardVO> selectBoardListPaging(PagingVO pgvo);
	
	List<BoardVO> selectList(PagingVO pgvo);

	int selectTotalCount(PagingVO pgvo);

	int selectOneBno();

//	int deleteBoard(int bno);

}
