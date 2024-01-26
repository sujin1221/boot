package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.repository.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor 
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardMapper mapper;

	@Override
	public void register(BoardVO bvo) {
		mapper.insert(bvo);
		
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		
		return mapper.getList(pgvo);
	}

	@Override
	public BoardVO getDetail(long bno) {
		// TODO Auto-generated method stub
		return mapper.getDetail(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		// TODO Auto-generated method stub
		mapper.update(bvo);
		
	}

	@Override
	public void remove(long bno) {
		// TODO Auto-generated method stub
		mapper.delete(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return mapper.getTotalCount(pgvo);
	}
}
