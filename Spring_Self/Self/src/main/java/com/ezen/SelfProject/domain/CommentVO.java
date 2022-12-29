package com.ezen.SelfProject.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentVO {
	private int cno;
	private int bno;
	private String writer;
	private String content;
	private String teg_at;
	private String mod_at;
	
}