package com.ezen.SelfProject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.SelfProject.domain.BoardDTO;
import com.ezen.SelfProject.domain.BoardVO;
import com.ezen.SelfProject.domain.FileVO;
import com.ezen.SelfProject.domain.PagingVO;
import com.ezen.SelfProject.domain.UserVO;
import com.ezen.SelfProject.repository.BoardDAO;
import com.ezen.SelfProject.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO bdao;
	
	@Inject
	private FileDAO fdao;	//FileDAO 인터페이스 repository
	@Override
	public int register(BoardVO bvo) {
		log.info("board register check2");
		return bdao.insertBoard(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		log.info("board list check2");
		return bdao.selectBoardList();
	}


	@Override
	public BoardVO getDetail(int bno) {
		//read_count update 요청 후 detail값 요청
		int isOk = bdao.readCount(bno);
		log.info("board detail check2");
		return isOk>0 ? bdao.selectBoardDetail(bno):null;
	}

	@Override
	public int modify(BoardVO bvo, UserVO user) {
		log.info("board modify check2");
		BoardVO tmpBoard = bdao.selectBoardDetail(bvo.getBno()); //사용자 일치 체크!!
		if (user == null || !user.getId().equals(tmpBoard.getWriter())) {
			log.info("사용자 불일치!!");
			return 0;	// 일치 안하면 0값 반환, 수정 ㄴㄴ
		}
		return bdao.updateBoard(bvo);	//메소드 생성
	}

	@Override
	public int remove(BoardVO bvo, UserVO user) {
		log.info("board remove check2");
		BoardVO tmpBoard = bdao.selectBoardDetail(bvo.getBno()); //사용자 일치 체크!!
		/*
		 * log.info(user.getId()); log.info(tmpBoard.getWriter());
		 */
		if (user == null || !user.getId().equals(tmpBoard.getWriter())) {
			log.info("사용자 불일치!!");
			return 0;	// 일치 안하면 0값 반환, 수정 ㄴㄴ
		}
		return bdao.deleteBoard(bvo);	//메소드 생성
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		
		return bdao.selectBoardListPaging(pgvo);
	}

	@Override
	public List<BoardVO> getListPage(PagingVO pgvo) {
		return bdao.selectList(pgvo);
	}
	
	@Override
	public int getTotalCount(PagingVO pgvo) {		
		return bdao.selectTotalCount(pgvo);
	}

//	@Override
//	public int register(BoardDTO bdto) {
//		log.info("board register check2");
//		int isOk = bdao.insertBoard(bdto.getBvo());	//기존 게시글에 대한 내용을 DB에 저장 bdao
//		if (isOk > 0 && bdto.getFList().size()>0) {
//			//가장 큰 bno 가져오기
//			int bno = bdao.selectOneBno();	//방금 넣은 bvo 객체가 db에 저장된 후 //selectOneBno 메소드 생성
//			for (FileVO fvo : bdto.getFList()) {	//FileVO import
//				fvo.setBno(bno);
//				log.info(">>> insert file : "+fvo.toString());
//				isOk *= fdao.insertFile(fvo);	//1.fdao 위에 넣어주기	//2.insertFile -> FileDAO에 메소드 생성
//			}
//		}
//		return isOk;
//	}

	@Override
	public BoardDTO getDetailFile(int bno) {
		bdao.readCount(bno);	//detail 선택시 조회수 올라감
		BoardDTO bdto = new BoardDTO(bdao.selectBoardDetail(bno), fdao.selectFileList(bno));	//selectFileList 메소드 생성
		return null;
	}

	

//	@Override
//	public int remove(int bno, UserVO user) {
//		log.info("board remove check2");
//		BoardVO tmpBoard = bdao.selectBoardDetail(bno);
//		if (user == null || !user.getId().equals(tmpBoard.getWriter())) {
//			log.info("사용자 불일치!!");
//			return 0;	// 일치 안하면 0값 반환, 수정 ㄴㄴ
//		}
//		return bdao.deleteBoard(bno);	//메소드 생성
//	}

	
}