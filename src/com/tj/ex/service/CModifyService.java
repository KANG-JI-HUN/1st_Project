package com.tj.ex.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.tj.ex.dao.CustomerDao;
import com.tj.ex.dto.CustomerDto;

public class CModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// cId, cPw, cName, cTel, cEmail, cGender, cAddr 파라미터 값 받아
		String cId = request.getParameter("cId");                       // 사진이나 첨부파일이 아니면 MultipartRequest로 받으면 안된다!!!
		String cPw = request.getParameter("cPw");
		String cName = request.getParameter("cName");
		String cTel = request.getParameter("cTel");
		String cEmail = request.getParameter("cEmail");
		String cGender = request.getParameter("cGender");
		String cAddr = request.getParameter("cAddr");
		// dto객체로 만들고, dao의 joinCustomer(dto)호출
		CustomerDto dto = new CustomerDto(cId, cPw, cName, cTel,
											cEmail, cGender, cAddr);
		CustomerDao cDao = new CustomerDao();
		int result = cDao.ModifyCustomer(dto);
		if(result==CustomerDao.SUCCESS) {
			// 회원수정 성공시 세션 갈아엎기, modifyResultMsg 추가하기
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("customer", dto);
			request.setAttribute("resultMsg", "회원정보 수정 성공");
		} else {
			// 회원수정 실패시 modifyResultMsg 추가하기
			request.setAttribute("resultMsg", "회원정보 수정 실패");
		}
	}
}
