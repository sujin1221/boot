package com.example.demo.repogitory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.BoardVO;

@Mapper
public interface BoardMapper {

	int insert(BoardVO bvo);

	List<BoardVO> selectList();

	int updateModify(BoardVO bvo);

	void readCount(int bno);

	BoardVO selectDetail(int bno);

	int delete(int bno);
	
}
