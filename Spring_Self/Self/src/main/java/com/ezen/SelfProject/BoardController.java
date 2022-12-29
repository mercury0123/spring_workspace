package com.ezen.SelfProject;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.SelfProject.domain.BoardDTO;
import com.ezen.SelfProject.domain.BoardVO;
import com.ezen.SelfProject.domain.FileVO;
import com.ezen.SelfProject.domain.PagingVO;
import com.ezen.SelfProject.domain.UserVO;
import com.ezen.SelfProject.repository.UserDAO;
import com.ezen.SelfProject.service.BoardService;
import com.ezen.SelfProject.service.UserServiceImpl;

import handler.FileHandler;
import handler.PagingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {
	
	@Inject
	private BoardService bsv;	//service에 인터페이스 생성
	@Inject
	private UserDAO userDao;
//	@Inject
//	private UserServiceImpl usv;
	@Inject
	private FileHandler fhd;
	
	
	@GetMapping("/register")
	public String boardRegisterGet() {
		return "/board/register";
	}
	
	//insert, update, delete => redirect 처리
	
	@PostMapping("/register")	//redirect 처리는 model 안써도 됨
	public String register(BoardVO bvo, RedirectAttributes reAttr) {	//BoardVO, RedirectAttributes import!!
		log.info(bvo.toString());
		
		int isOk = bsv.register(bvo);	// 메소드 생성
		reAttr.addFlashAttribute("isOk", isOk>0 ? "1":"0");
		log.info("board register>> "+(isOk>0 ? "OK":"FAIL"));
		return "redirect:/board/list";					
	}
//	@PostMapping("/register")	//redirect 처리는 model 안써도 됨
//	public String register(BoardVO bvo, RedirectAttributes reAttr
//			,@RequestParam(name="files", required = false)MultipartFile[] files) {	//BoardVO, RedirectAttributes import!!
//		log.info("files : " + files.toString());
//		log.info(bvo.toString());
		
//		List<FileVO> fileList = null;
//	      if(files[0].getSize()>0) {
//	         fileList = fhd.uploadFiles(files);
//	      }else {
//	         log.info("파일이 없습니다.");
//	         int isUp = bsv.register(bvo);
//	         
//	         if(isUp>0) {
//	            reAttr.addFlashAttribute("msg_board_register", 1);
//	         }else reAttr.addFlashAttribute("msg_board_register", 0);
//	         return "redirect:/board/list";
//	      }
//	      
//	      BoardDTO bdto = new BoardDTO(bvo, fileList);	      
//	      int isUp = bsv.register(bdto);  
//	      if(isUp >0) {
//	         reAttr.addFlashAttribute("msg_board_register", 1);
//	      }else reAttr.addFlashAttribute("msg_board_register", 0);
//	      return "redirect:/board/list";
//	      
		//int isOk = bsv.register(bvo);	// 메소드 생성
//		reAttr.addFlashAttribute("isOk", isOk>0 ? "1":"0");
//		log.info("board register>> "+(isOk>0 ? "OK":"FAIL"));
//		return "redirect:/board/list";					
//	}
	
	@GetMapping("/list")
	public String list(Model model, PagingVO pgvo) {	//Model import!! PagingVO import!! 
		log.info(">>> pageNo : "+pgvo.getPageNo());
		List<BoardVO> list = bsv.getList(pgvo);	//List import!! getList 메소드 생성
		int totalCount = bsv.getTotalCount(pgvo);	//전체 카운트 호출. 보드서비스에 메소드 생성
		PagingHandler ph = new PagingHandler(pgvo,totalCount);
		model.addAttribute("list", list);
		model.addAttribute("pgh", ph);		
		return "/board/list";
	}
	
	@GetMapping({"/detail","/modify"})
	public void detail(Model model, @RequestParam("bno")int bno) {	//void로 바꿈. 수정 집어넣고
		BoardVO board = bsv.getDetail(bno);	//getDetail 메소드 생성
		model.addAttribute("board", board);
	}
	
	@PostMapping("/modify")
	public String modify(RedirectAttributes reAttr, BoardVO bvo) {	//사용자 체크
		log.info("modify>>> : "+bvo.toString());
		UserVO user = userDao.getUser(bvo.getWriter());	//UserVO import!!
		int isUp = bsv.modify(bvo, user);	// 메소드 생성
		log.info("modify>>> : "+ (isUp>0 ? "ok" : "fail"));
		reAttr.addFlashAttribute(isUp>0 ? "1":"0");
		return "redirect:/board/list";
	}
	
	@GetMapping("/remove") //아예 삭제하는 방식 
	public String remove(RedirectAttributes
	reAttr, @RequestParam("bno")int bno) { BoardVO bvo = bsv.getDetail(bno);
		log.info("remove>>> : "+bvo.toString()); UserVO user =
		userDao.getUser(bvo.getWriter()); //UserVO import!! 
		int isUp = bsv.remove(bvo, user); // 메소드 생성 
		log.info("remove>>> : "+ (isUp>0 ? "ok" :"fail")); 
		reAttr.addFlashAttribute(isUp>0 ? "1":"0"); 
		return"redirect:/board/list"; 
	}
	
//	@GetMapping("/remove")	//isDel 사용하는 방식
//	public String remove(RedirectAttributes reAttr, @RequestParam("bno")int bno,
//			HttpServletRequest req) {
////		HttpSession ses = req.getSession();
////		UserVO user = userDao.getUsers(req); //UserVO import!!
////		log.info(user.toString()); 위아래 같음		
//		UserVO user = userDao.getUsers((UserVO)req.getSession().getAttribute("ses"));
//		int isUp = bsv.remove(bno, user);	// 메소드 생성
//		log.info("remove>>> : "+ (isUp>0 ? "ok" : "fail"));		
//		return "redirect:/board/list";
//	}
	
}