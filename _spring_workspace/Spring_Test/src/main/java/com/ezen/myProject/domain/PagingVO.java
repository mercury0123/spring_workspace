package com.ezen.myProject.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingVO {
	
	private int pageNo;	//현재 화면에 출력된 페이지네이션 번호
	private int qty;	//한 페이지당 보여줄 게시글 수
	private String type;
	private String keyword;
	
	
	public PagingVO() {	//페이지 네이션을 클릭하기 전, 기본 리스트 출력값
		this(1, 10);	//페이지 네이션 번호1, 1페이지에 10개씩 출력
	}	
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	public int getPageStart() {
		return (this.pageNo-1)*qty;	//시작페이지 번호 구하기
	}
	
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
}