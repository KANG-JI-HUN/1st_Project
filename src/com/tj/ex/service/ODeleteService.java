package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.OafterDao;

public class ODeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int oNo = Integer.parseInt(request.getParameter("oNo"));
		OafterDao oafterDao = new OafterDao();
		int result = oafterDao.delete(oNo);
		if(result == OafterDao.SUCCESS) {
			request.setAttribute("resultMsg", "후기삭제성공");
		}else {
			request.setAttribute("resultMsg", "후기삭제실패");
		}
	}
}
