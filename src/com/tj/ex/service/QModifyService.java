package com.tj.ex.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.tj.ex.dao.QboardDao;

public class QModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, "utf-8");
			// QNO, QSUBJECT, QCONTENT, QPW
			int qNo = Integer.parseInt(mRequest.getParameter("qNo"));
			String qSubject = mRequest.getParameter("qSubject");
			String qContent = mRequest.getParameter("qContent");
			String qPw = mRequest.getParameter("qPw");
			QboardDao qDao = new QboardDao();
			int result = qDao.modify(qNo, qSubject, qContent, qPw);
			// joinCustomer 결과에 따라 적절히 request.setAttribute
			if(result==QboardDao.SUCCESS) { // 회원가입진행
				request.setAttribute("resultMsg", "질문수정성공");
			} else {
				request.setAttribute("resultMsg", "질문수정실패");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "질문수정실패");
		}
	}
}
