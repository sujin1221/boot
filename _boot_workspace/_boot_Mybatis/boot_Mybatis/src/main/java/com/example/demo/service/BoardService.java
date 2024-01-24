package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList();

	int modify(BoardVO bvo);

	BoardVO getDetail(int bno);

	int delete(int bno);
	
}
