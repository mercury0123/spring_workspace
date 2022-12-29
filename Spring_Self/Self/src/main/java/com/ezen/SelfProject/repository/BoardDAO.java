package com.ezen.SelfProject.repository;

import java.util.List;

import com.ezen.SelfProject.domain.BoardVO;
import com.ezen.SelfProject.domain.PagingVO;

public interface BoardDAO {

	int insertBoard(BoardVO bvo);

	List<BoardVO> selectBoardList();

	int readCount(int bno);

	BoardVO selectBoardDetail(int bno);

	int updateBoard(BoardVO bvo);

	int deleteBoard(BoardVO bvo);

	List<BoardVO> selectBoardListPaging(PagingVO pvo);	

	List<BoardVO> selectList(PagingVO pgvo);

	int selectTotalCount(PagingVO pgvo);

	int selectOneBno();

}
