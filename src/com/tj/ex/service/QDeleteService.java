package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.QboardDao;

public class QDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		QboardDao qboardDao = new QboardDao();
		int result = qboardDao.delete(qNo);
		if(result == QboardDao.SUCCESS) {
			request.setAttribute("resultMsg", "질문 성공");
		}else {
			request.setAttribute("resultMsg", "질문 실패");
		}
	}
}
