package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ItemlistDao;
import com.tj.ex.dao.NboardDao;
import com.tj.ex.dto.ItemlistDto;
import com.tj.ex.dto.NboardDto;

public class NModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		NboardDao nboardDao = new NboardDao();
		NboardDto dto = nboardDao.modifyView(nNo);
		request.setAttribute("modify_view", dto);
	}
}
