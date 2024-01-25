package com.example.demo.handler;

import java.util.List;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PagingHandler {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int totalCount;
	private PagingVO pgvo;
	
	private List<CommentVO> cmtList;
	
	public PagingHandler(int totalCount, PagingVO pgvo) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		this.endPage = 
				(int)Math.ceil((double)pgvo.getPageNo()/pgvo.getQty())*pgvo.getQty();
		this.startPage = endPage -9; //(pgvo.getQty()-1)
		
		//double 처리 안하고 하는 방법:
		int realEndPage = (int)Math.ceil(totalCount*1.0/pgvo.getQty());
		
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;		
	}

	public PagingHandler(int totalCount, PagingVO pgvo, List<CommentVO> list) {
		this(totalCount,pgvo);
		this.cmtList=list;
	}
	
}
