package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.FreeboardDao;
import com.tj.ex.dao.NboardDao;
import com.tj.ex.dto.FreeboardDto;
import com.tj.ex.dto.NboardDto;

public class NContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		NboardDao nboardDao = new NboardDao();
		NboardDto dto = nboardDao.contentView(nNo);
		request.setAttribute("content_view", dto);
	}
}
