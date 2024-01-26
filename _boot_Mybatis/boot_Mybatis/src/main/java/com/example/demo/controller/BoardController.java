package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.service.BoardService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller
public class BoardController {

	private final Logger log = LoggerFactory.getLogger(BoardController.class);
	private final BoardService bsv;
	
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		log.info(">>>>> bvo >> {}", bvo);
		bsv.register(bvo);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public void list(Model m, PagingVO pgvo) {
		log.info(">>> pgvo >> {}", pgvo);
		//totalCount
		int totcalCount = bsv.getTotalCount(pgvo); //검색할때 사용
		//PagingHandler 객체생성
		PagingHandler ph = new PagingHandler(pgvo, totcalCount);
		m.addAttribute("list", bsv.getList(pgvo));
		//PagingHandler 객체 보내기
		m.addAttribute("ph", ph);
	}
	
	@GetMapping("/detail")
	public void detail(Model m, @RequestParam("bno")long bno) {
		m.addAttribute("bvo", bsv.getDetail(bno));
	}
	
	@PostMapping("/modify")
	public String modify(RedirectAttributes re, BoardVO bvo) {
		bsv.modify(bvo);
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno")long bno) {
		bsv.remove(bno);
		return "redirect:/board/list";
	}
	
}
