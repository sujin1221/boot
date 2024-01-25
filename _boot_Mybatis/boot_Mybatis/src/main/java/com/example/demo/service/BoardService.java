package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	int modify(BoardVO bvo);

	BoardVO getDetail(int bno);

	int delete(int bno);

	int getTotalCount(PagingVO pgvo);
	
}
