package com.ezen.SelfProject.repository;

import java.util.List;

import com.ezen.SelfProject.domain.CommentVO;

public interface CommentDAO {

	int insertComment(CommentVO cvo);

	List<CommentVO> list(int bno);

	int updateComment(CommentVO cvo);

	int deleteComment(int cno);

	
}
