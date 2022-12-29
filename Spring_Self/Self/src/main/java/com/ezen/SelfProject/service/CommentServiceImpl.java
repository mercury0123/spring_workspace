package com.ezen.SelfProject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.SelfProject.domain.CommentVO;
import com.ezen.SelfProject.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
public class CommentServiceImpl implements CommentService {
	
	@Inject
	private CommentDAO cdao;	
	
	@Override
	public int register(CommentVO cvo) {
		log.info("comment check 2");
		return cdao.insertComment(cvo);	//CommentDAO 메소드 생성
	}

	@Override
	public List<CommentVO> getList(int bno) {
		log.info("commentList check 2");
		return cdao.list(bno);	////CommentDAO 메소드 생성
	}

	@Override
	public int modify(CommentVO cvo) {
		log.info("comment modify check 2");
		return cdao.updateComment(cvo);
	}

	@Override
	public int remove(int cno) {
		log.info("comment delete check 2");
		return cdao.deleteComment(cno);
	}
}