package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repogitory.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
	private final BoardMapper mapper;
}
