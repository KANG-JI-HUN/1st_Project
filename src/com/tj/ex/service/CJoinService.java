package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.CustomerDao;
import com.tj.ex.dto.CustomerDto;

public class CJoinService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// cId, cPw, cName, cTel, cEmail, cGender, cAddr 파라미터 값 받아
		String cId = request.getParameter("cId");
		String cPw = request.getParameter("cPw");
		String cName = request.getParameter("cName");
		String cTel = request.getParameter("cTel");
		String cEmail = request.getParameter("cEmail");
		String cGender = request.getParameter("cGender");
		String cAddr = request.getParameter("cAddr");
		// dto객체로 만들고, dao의 joinCustomer(dto)호출
		CustomerDto dto = new CustomerDto(cId, cPw, cName, cTel, cEmail, cGender, cAddr);
		CustomerDao cDao = new CustomerDao();
		// cId 중복체크
		int result = cDao.cIdConfirm(cId);
		if (result == CustomerDao.MEMBER_NONEXISTENT) { // 회원가입 진행
			result = cDao.joinCustomer(dto);
			if (result == CustomerDao.SUCCESS) {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("cId", cId);
				request.setAttribute("resultMsg", "회원가입이 성공되었습니다");
			} else {
				request.setAttribute("errorMsg", "모든 회원정보를 입력해주세요");
			}
		} else {
			request.setAttribute("errorMsg", "중복된 ID라서 회원가입이 불가합니다");
		}
		// joinCustomer결과에 따라 적절히 request.setAttribute
	}
}
