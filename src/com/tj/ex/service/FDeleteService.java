package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.FreeboardDao;

public class FDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fNo = Integer.parseInt(request.getParameter("fNo"));
		FreeboardDao freeboardDao = new FreeboardDao();
		int result = freeboardDao.delete(fNo);
		if(result==FreeboardDao.SUCCESS) {
			request.setAttribute("resultMsg", "자유게시판 글삭제 성공");
		} else {
			request.setAttribute("resultMsg", "자유게시판 글삭제 실패");
		}
	}
}
