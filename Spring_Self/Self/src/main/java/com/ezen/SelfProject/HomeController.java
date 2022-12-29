package com.ezen.SelfProject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.SelfProject.domain.UserVO;
import com.ezen.SelfProject.service.UserService;

/*
 * 자동으로 @Controller 어노테이션 붙음
 */
@RequestMapping("/member/*")
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*service 등록*/
	@Inject
	private UserService userService;	//com.ezen.myProject.service 에 인터페이스생성
	
	@GetMapping("/")
	public ModelAndView home(ModelAndView mv) {
		mv.setViewName("/index");
		return mv;		
	}
	
	@GetMapping("/signup")
	public ModelAndView signUpGet(ModelAndView mv) {
		mv.setViewName("/user/signup");
		return mv;
	}
	
	@PostMapping("/signup")
	public ModelAndView signUpPost(ModelAndView mv, UserVO user) {
		logger.info(user.toString());
		//비밀번호 암호화 라이브러리 설정 완료후
		boolean isUp = userService.signUp(user);	//signUp 메소드 생성
		if(isUp) {
			mv.setViewName("/user/login");
		} else {
			mv.setViewName("/user/signup");
			mv.addObject("msg", "0");
		}
		return mv;		
	}
	
	@GetMapping("/login")
	public ModelAndView loginGet(ModelAndView mv) {
		mv.setViewName("/user/login");
		return mv;
	}
	
	@PostMapping("/login")
	public ModelAndView loginPost(ModelAndView mv, String id,
			String pw, HttpServletRequest req) {
		//id , pw를 받아와서 양 값이 일치하는 회원이 있으면 회원정보를 가져오고, 없으면 null
		logger.info(">>> ID : "+ id+">>> PW : "+pw);
		UserVO isUser = userService.getUser(id,pw);	//getUser 메소드 생성
		logger.info(isUser.toString());
		
		//isUser가 null이 아니라면 세션연결
		if(isUser != null) {
			HttpSession session = req.getSession();
			session.setAttribute("ses", isUser);
			mv.addObject("user", isUser);
			mv.addObject("msg", "1");
			mv.setViewName("/index");
		} else {
			mv.setViewName("/user/login");	//로그인 실패시
			mv.addObject("msg", "0");
		}
		return mv;
	}
	
	@GetMapping("/logout")
	public ModelAndView logoutGet(ModelAndView mv, HttpServletRequest req) {
		
		return mv;
	}
	
}