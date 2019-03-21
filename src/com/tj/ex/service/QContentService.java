package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.QboardDao;
import com.tj.ex.dto.QboardDto;

public class QContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		QboardDao qboardDao = new QboardDao();
		QboardDto dto = qboardDao.contentView(qNo);
		request.setAttribute("qcontent_view", dto);
	}
}
