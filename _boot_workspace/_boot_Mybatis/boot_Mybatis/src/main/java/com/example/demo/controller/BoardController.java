package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.BoardVO;
import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Controller
public class BoardController {
	
	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes re) {
		log.info(">>> bvo >> {} ", bvo);
		int isOk = bsv.register(bvo);
		log.info("board register >>> {} "+(isOk > 0 ? "ok":"fail"));
		re.addFlashAttribute("isReg", isOk); //알림창 추가
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String list(Model m) {
		m.addAttribute("list", bsv.getList());
		return "/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes re) {
		log.info(">>>>> modify bvo >>> {} "+bvo);
		int isOk = bsv.modify(bvo);
		re.addFlashAttribute("isMod", isOk); //알림창 추가
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
		
	@GetMapping({"/detail","/modify"})
	public void detail(Model m, @RequestParam("bno") int bno) {
		log.info(">>> detail bno >>> {} "+bno);		
		m.addAttribute("bvo", bsv.getDetail(bno));
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno") int bno, RedirectAttributes re) {
		log.info(">>>>> delete bno >> {} "+bno);
		int isOk = bsv.delete(bno);
		re.addFlashAttribute("isDel", isOk); //알림창 추가
		return "redirect:/board/list";
	}
	
	
}
