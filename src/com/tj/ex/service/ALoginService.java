package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.AdminDao;
import com.tj.ex.dto.AdminDto;

public class ALoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aId = request.getParameter("aId");
		String aPw = request.getParameter("aPw");
		AdminDao aDao = new AdminDao();
		int result = aDao.loginCheck(aId, aPw);
		// loginCheck 결과가 SUCCESS이면 세션에 추가
		// loginCheck 결과가 FAIL이면 fail이유를 request.setAttribute에
		if(result==AdminDao.LOGIN_SUCCESS) {
			HttpSession httpSession = request.getSession();
			AdminDto dto = aDao.getAdmin(aId);
			httpSession.setAttribute("aId", aId);
			httpSession.setAttribute("admin", dto);
			request.setAttribute("adminLoginResult", "관리자계정으로 들어오셨습니다");			
		} else {
			request.setAttribute("adminLoginResult", "관리자계정 로그인이 실패되었습니다");
			request.setAttribute("adminLoginError", "error");
		}
	}
}
