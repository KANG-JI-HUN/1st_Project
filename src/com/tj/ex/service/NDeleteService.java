package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.NboardDao;

public class NDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		NboardDao nboardDao = new NboardDao();
		int result = nboardDao.delete(nNo);
		if(result==NboardDao.SUCCESS) {
			request.setAttribute("resultMsg", "공지글 삭제 성공");
		} else {
			request.setAttribute("resultMsg", "공지글 삭제 실패");
		}
	}
}
