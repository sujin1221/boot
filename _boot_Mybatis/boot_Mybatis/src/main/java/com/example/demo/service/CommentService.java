package com.example.demo.service;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;

public interface CommentService {

	int post(CommentVO cvo);

	PagingHandler getList(long bno, PagingVO pgvo);

	int modify(CommentVO cvo);

	int deleteComment(long cno);
	
}
