package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ItemlistDao;
import com.tj.ex.dto.ItemlistDto;

public class IContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			int iNo = Integer.parseInt(request.getParameter("iNo"));
			ItemlistDao itemlistDao = new ItemlistDao();
			ItemlistDto dto = itemlistDao.contentView(iNo);
			request.setAttribute("content_view", dto);
	}
}
