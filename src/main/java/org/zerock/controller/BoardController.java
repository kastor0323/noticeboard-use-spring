package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
	
	private final  BoardService service;
	
//	@GetMapping("/list")
//	public void list(Model model) { //전달당시에는 없는데... 화면에서 추가적인 데이터가 필요한 경우
//		log.info("list...............");
//		model.addAttribute("list", service.getList());
//	}
	@GetMapping("/register")
	public void registerGet() {
		
	}
	
//	@PostMapping("/register")
//	public void register(BoardVO board) {
//		log.info("board: " + board);
//		
//		Long bno = service.register(board);
//		
//		log.info("bno: " + bno);
//	}

// redirect 사용할 때
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("board: " + board);
		
		Long bno = service.register(board);
		
		log.info("bno: " + bno);
	
//		//데이터를 전달 addattribute addflashattribute 둘 중 하나 사용
//		//addFlashAttribute 데이터를 전송하면 한번만 전송이후 브라우저에 남지 않음
		rttr.addFlashAttribute("result", bno);
		
		//addattribute 브라우저의 링크랑 같이 연결
//		rttr.addAttribute("result", bno);
		
		return "redirect:/board/list";
	}
	
	//조회
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam ("bno")long bno, Model model) {
		
		model.addAttribute("board", service.get(bno));
	}
	//수정
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		int count = service.modify(board);
		if(count == 1) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
	
	//삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") long bno, RedirectAttributes rttr) {
		int count = service.remove(bno);
		if(count == 1) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("--------------------------------------------------------------");
		log.info(cri);
		log.info("list...............");
		
		model.addAttribute("list", service.getList(cri));
	}
}
