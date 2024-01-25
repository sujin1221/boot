package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.repogitory.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
	private final BoardMapper mapper;

	@Override
	public int register(BoardVO bvo) {
		log.info("register service impl");
		int isOk = mapper.insert(bvo);
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {		
		return mapper.selectList(pgvo);
	}

	@Override
	public int modify(BoardVO bvo) {		
		return mapper.updateModify(bvo);		
	}

	@Override
	public BoardVO getDetail(int bno) {		
		return mapper.selectDetail(bno);
	}

	@Override
	public int delete(int bno) {		
		return mapper.delete(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		
		return mapper.getTotalCount(pgvo);
	}
}
