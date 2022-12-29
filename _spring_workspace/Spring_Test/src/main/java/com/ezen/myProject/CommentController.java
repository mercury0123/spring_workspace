package com.ezen.myProject;

import java.util.List;

import javax.inject.Inject;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.myProject.domain.CommentVO;
import com.ezen.myProject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@Controller
public class CommentController {
	
	@Inject
	private CommentService csv;
	
	//value:mapping 값 설정, consumes: 가져오는 값의 형태 (applicatint.json), 
	//produces: 보낼때의 형식{MediaType.text_plain_value} 
	@PostMapping(value = "/post", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){	//ResponseEntity import!! CommentVO import!!
		log.info("comment post : "+cvo.toString());
		int isUp = csv.register(cvo);	//register => CommentService에 메소드 생성
		log.info("register isUp : "+ (isUp>0? "OK":"Fail"));
		return isUp > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@GetMapping(value="/{bno}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CommentVO>> spread(@PathVariable("bno")int bno) {	//List import!!
		log.info("comment List bno : "+ bno);
		List<CommentVO> list = csv.getList(bno);	// getList => CommentService에 메소드 생성
		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{cno}", consumes = "application/json" , produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> edit(@PathVariable("cno")int cno, @RequestBody CommentVO cvo) {
		log.info("comment modify cno : "+ cno);
		log.info("comment modify cvo : "+ cvo.toString());
		int isUp = csv.modify(cvo);	// modify=> CommentService에 메소드 생성
		log.info("isUp : "+ isUp);
		return isUp > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/{cno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> delete(@PathVariable("cno")int cno) {
		log.info("comment delete cno : "+ cno);
		int isUp = csv.remove(cno);	// delete=> CommentService에 메소드 생성
		log.info("isUp : "+ isUp);
		return isUp > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}