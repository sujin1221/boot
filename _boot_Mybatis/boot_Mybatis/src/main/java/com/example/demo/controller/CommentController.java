package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment/*")
@RestController
public class CommentController {
	
	private final CommentService csv;

	@PostMapping("/post")
	@ResponseBody
	public String post(@RequestBody CommentVO cvo) {
		log.info(">> cvo >>> {} ", cvo);
		int isOk = csv.post(cvo);
		return isOk > 0 ? "1":"0";
	}
	
	@GetMapping("/{bno}/{page}")
	@ResponseBody
	public PagingHandler list(@PathVariable("bno") long bno,
			@PathVariable("page") int page){
		log.info(">> bno >>> {} "+bno+"/ page >>> {} "+page);
		//List<CommentVO> and PagingHandler
		//비동기는 한 객체만 전송 가능함...
		PagingVO pgvo = new PagingVO(page, 5); //1페이지에 5개씩 댓글 출력		
		PagingHandler ph = csv.getList(bno, pgvo);
		return ph;
	}
	
	@PutMapping("/edit")
	@ResponseBody
	public String edit(@RequestBody CommentVO cvo) {
		log.info(">> cvo >>> {} ", cvo);
		int isOk = csv.edit(cvo);
		return isOk > 0 ? "1":"0";
	}
	
	
	
	
	
	
	
	
	
	
}
