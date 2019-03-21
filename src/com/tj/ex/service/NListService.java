package com.tj.ex.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.FreeboardDao;
import com.tj.ex.dto.FreeboardDto;

public class NListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		String requestPageNum = (String)request.getAttribute("pageNum");
		if(pageNum==null) {
			if(requestPageNum==null) {
				pageNum = "1";
			} else {
				pageNum = requestPageNum;
			}
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 10, BLOCKSIZE = 10;
		int startRow = (currentPage - 1) * PAGESIZE + 1;
		int endRow = startRow + PAGESIZE - 1;
		FreeboardDao freeboardDao = new FreeboardDao();
		ArrayList<FreeboardDto> list = freeboardDao.list(startRow, endRow);
		request.setAttribute("list", list);
		int totCnt = freeboardDao.getFreeboardTotCnt(); // 자유게시판 글갯수
		int pageCnt = (int) Math.ceil((double) totCnt / PAGESIZE); // 자유게시판 페이지갯수
		int startPage = ((currentPage - 1) / BLOCKSIZE) * BLOCKSIZE + 1;
		int endPage = startPage + BLOCKSIZE - 1;
		if (endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt); // totCnt는 없으면 list.size()대용
		request.setAttribute("pageNum", currentPage);// pageNum 없으면 param.pageNum
	}

}
