package com.example.demo.repogitory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;

@Mapper
public interface CommentMapper {

	int insertPost(CommentVO cvo);

	int selectOneBnoTotalCount(long bno);

	List<CommentVO> getList(long bno, PagingVO pgvo);

	int updateModify(CommentVO cvo);

	int deleteComment(long cno);

}
