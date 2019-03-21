package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ItemlistDao;

public class IDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int iNo = Integer.parseInt(request.getParameter("iNo"));
		ItemlistDao itemlistDao = new ItemlistDao();
		int result = itemlistDao.delete(iNo);
		if(result == ItemlistDao.SUCCESS) {
			request.setAttribute("resultMsg", "물건등록 성공");
		}else {
			request.setAttribute("resultMsg", "물건등록 실패");
		}
	}
}
