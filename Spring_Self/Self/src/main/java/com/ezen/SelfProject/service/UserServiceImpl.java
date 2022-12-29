package com.ezen.SelfProject.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.SelfProject.domain.UserVO;
import com.ezen.SelfProject.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	private UserDAO userDao;	//repository에 인터페이스 생성
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public boolean signUp(UserVO user) {
		log.info(">>>signup check2");
		//아이디가 중복되면 회원가입 실패
		//아이디와 일치하는 값이 있는지 DB에서 체크
		//1. 우선 아이디와 일치하는 정보를 DB에서 가져옴
		UserVO tmpUser =userDao.getUser(user.getId());	//getUser=> userDAO에 메소드생성
		//tmpUser가 null이 아니라면, 이미 가입한 회원 => 아이디 중복 => 회원가입 실패
		if(tmpUser != null) {
			return false;
		}
		//아이디가 중복되지 않았으면 회원가입 ㄱㄱ but 아이디가 없다 or 아이디 형식에 맞지않은지 등등
		//아이디 유효성 검사는 아이디가 입력되었는지만 체크
		if(user.getId()==null || user.getId().length()==0) {
			return false;
		}
		//비번 유효성 검사는 비밀번호가 입력되었는지만 체크
		if(user.getPw()==null || user.getPw().length()==0) {
			return false;
		}
		
		// 모두 통과시, 비번 암호화 과정 ㄱㄱ
		String pw = user.getPw();	//비번을 가져와서 변수 pw에 삽입
		//encode(암호화) / matches(원래 비번 vs 암호화된 비번 일치 검토) 
		String encodePw = passwordEncoder.encode(pw);	//pw암호화된 패스워드를 getPw에 set
		user.setPw(encodePw);	//비번을 암호화된 비번으로 수정
		
		//회원가입 => insert
		int isOk = userDao.insertUser(user);	//insertUser => 메소드 생성		
		return isOk>0 ? true : false;
	}

	@Override
	public UserVO getUser(String id, String pw) {
		UserVO user = userDao.getUser(id);	//위에서 한 메소드 호출
		//가져온 User 객체의 비번과 입력받은 비번이 같은지 확인
		//user가 없을 때
		if(user == null) return null;
		
		//matches(원비번,암호화비번) 일치검토
		if(passwordEncoder.matches(pw, user.getPw())) {
			return user;	//true 라면 user 값 리턴
		} else {
			return null;	//false 라면 null 값 리턴
		}
	}
	
}