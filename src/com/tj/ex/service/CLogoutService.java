package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CLogoutService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// session attribute 데이터 다 날리기
		HttpSession session = request.getSession();
		session.invalidate();
		request.setAttribute("resultMsg", "로그아웃되었습니다");
	}
}
