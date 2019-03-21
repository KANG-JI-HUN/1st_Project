package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.OafterDao;
import com.tj.ex.dto.OafterDto;

public class OContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int oNo = Integer.parseInt(request.getParameter("oNo"));
		OafterDao oafterDao = new OafterDao();
		OafterDto dto = oafterDao.contentView(oNo);
		request.setAttribute("content_view", dto);
	}
}
