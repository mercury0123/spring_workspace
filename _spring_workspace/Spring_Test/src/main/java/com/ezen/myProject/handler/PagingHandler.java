package com.ezen.myProject.handler;

import com.ezen.myProject.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingHandler {
	
	private int startPage;	//현재 화면에서 보여줄 시작 페이지네이션의 시작번호
	private int endPage; 	//현재 화면에서 보여줄 마지막 페이지네이션의 번호
	private boolean prev, next; 	//이전, 다음이 존재하는지 여부를 true false로 판별

	private int totalCount;	//전체 게시글 수 (이걸 알아야 페이지 몇 개까지 나오는지 파악 가능)
	private PagingVO pgvo; //import (도메인에서 가져옴)

	//생성자
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		//페이지 번호 / 한 화면의 게시글 수 (10개) * 한 화면의 게시글 수
		//(1/10)*10
		//this.endPage = pageNo / qty * qty; 이렇게 쓰면 오류므로 pgvo.get 사용
		//ceil 값이 double 이므로 int 형변환
		this.endPage
		= (int)Math.ceil(pgvo.getPageNo() / (pgvo.getQty()*1.0)) * pgvo.getQty();	//한 화면에 보여지는 페이지 네이션 마지막 번호
		this.startPage = this.endPage -9;	//한 화면에 보여지는 페이지 네이션 첫 번호
		int realEndPage = (int)(Math.ceil((totalCount*1.0)/pgvo.getQty()));	//페이지 네이션의 전체 끝페이지
		//realEndPage 진짜 끝번호

		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;	//끝페이지
		}
		this.prev = this.startPage > 1; //다음페이지가 있다는 의미므로 true반환
		this.next = this.endPage < realEndPage;
		// 마지막 페이지가 10이 안되면 당연히 next 값은 false가 됨
	}
}
