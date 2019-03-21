package com.tj.ex.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.tj.ex.dao.QboardDao;

public class QWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, "utf-8");
			// cId, qSubject, qContent, qPw
			HttpSession httpSession = request.getSession();
			String cId = (String)httpSession.getAttribute("cId");
			String qSubject = mRequest.getParameter("qSubject");
			String qContent = mRequest.getParameter("qContent");
			String qPw = mRequest.getParameter("qPw");
			int qGroup = Integer.parseInt(mRequest.getParameter("qGroup"));
			int qStep = Integer.parseInt(mRequest.getParameter("qStep"));
			int qIndent = Integer.parseInt(mRequest.getParameter("qIndent"));
			QboardDao qDao = new QboardDao();
			int result = qDao.write(cId, qSubject, qContent, qPw, qGroup, qStep, qIndent);
			// joinCustomer 결과에 따라 적절히 request.setAttribute
			if(result==QboardDao.SUCCESS) { // 회원가입진행
				request.setAttribute("resultMsg", "질문 성공");
			} else {
				request.setAttribute("resultMsg", "질문 실패");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "질문 실패");
		}
	}
}
