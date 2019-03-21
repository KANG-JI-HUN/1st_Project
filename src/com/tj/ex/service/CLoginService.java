package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.CustomerDao;
import com.tj.ex.dto.CustomerDto;

public class CLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// cId, cPw 파라미터 값 받아 loginCheck하기
		String cId = request.getParameter("cId");
		String cPw = request.getParameter("cPw");
		CustomerDao cDao = new CustomerDao();
		int result = cDao.loginCheck(cId, cPw);
		// loginCheck 결과가 SUCCEE면 세션에 추가
		// loginCheck 결과가 FAIL이면 FAIL 이유를 reqeust.setAttribute에 추가
		if(result==CustomerDao.LOGIN_SUCCESS) {
			HttpSession httpSession = request.getSession();
			CustomerDto dto = cDao.getCustomer(cId);
			httpSession.setAttribute("cId", cId);
			httpSession.setAttribute("customer", dto);
		} else if(result==CustomerDao.LOGIN_FAIL_PW) {
			request.setAttribute("loginErrorMsg", "비밀번호를 확인하세요");
		} else if(result==CustomerDao.LOGIN_FAIL_ID) {
			request.setAttribute("loginErrorMsg", "ID를 확인하세요");
		}
	}
}