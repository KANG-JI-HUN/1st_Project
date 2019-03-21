package com.tj.ex.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.tj.ex.dao.LikeitemDao;

public class LWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, "utf-8");
			// cId, iNo, lCnt
			HttpSession httpSession = request.getSession();
			String cId = (String) httpSession.getAttribute("cId");
			int iNo = Integer.parseInt(mRequest.getParameter("iNo"));
			int lCnt = Integer.parseInt(mRequest.getParameter("lCnt"));
			LikeitemDao lDao = new LikeitemDao();
			int result = lDao.input(cId, iNo, lCnt);
			// joinMember결과에 따라 적절히 request.setAttribute
			if (result == LikeitemDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("resultMsg", "선택한 아이템 넣기 성공");
			} else {
				request.setAttribute("resultMsg", "선택한 아이템 넣기 실패");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "선택한 아이템 넣기 실패");
		}
	}
}
