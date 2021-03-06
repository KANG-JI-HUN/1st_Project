package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.FreeboardDao;
import com.tj.ex.dto.FreeboardDto;

public class FContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fNo = Integer.parseInt(request.getParameter("fNo"));
		FreeboardDao freeboardDao = new FreeboardDao();
		FreeboardDto dto = freeboardDao.contentView(fNo);
		request.setAttribute("content_view", dto);
	}
}
