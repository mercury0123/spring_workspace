package com.ezen.SelfProject.service;

import java.util.List;

import com.ezen.SelfProject.domain.CommentVO;

public interface CommentService {

	int register(CommentVO cvo);

	List<CommentVO> getList(int bno);

	int modify(CommentVO cvo);

	int remove(int cno);

}
