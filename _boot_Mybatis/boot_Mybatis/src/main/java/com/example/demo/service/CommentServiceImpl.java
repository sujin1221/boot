package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.repogitory.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
	private final CommentMapper mapper;

	@Override
	public int post(CommentVO cvo) {
		
		return mapper.insertPost(cvo);
	}

	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		int totalCount = mapper.selectOneBnoTotalCount(bno);
		List<CommentVO> list = mapper.getList(bno, pgvo);
		PagingHandler ph = new PagingHandler(totalCount, pgvo, list);
		return ph;
	}

	@Override
	public int modify(CommentVO cvo) {
		
		return mapper.updateModify(cvo);
	}

	@Override
	public int deleteComment(long cno) {
		
		return mapper.deleteComment(cno);
	}
}
