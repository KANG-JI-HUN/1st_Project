package com.tj.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.service.ALoginService;
import com.tj.ex.service.CAllViewService;
import com.tj.ex.service.CJoinService;
import com.tj.ex.service.CLoginService;
import com.tj.ex.service.CLogoutService;
import com.tj.ex.service.CModifyService;
import com.tj.ex.service.FContentService;
import com.tj.ex.service.FDeleteService;
import com.tj.ex.service.FListService;
import com.tj.ex.service.FModifyService;
import com.tj.ex.service.FModifyViewService;
import com.tj.ex.service.FReplyService;
import com.tj.ex.service.FReplyViewService;
import com.tj.ex.service.FWriteService;
import com.tj.ex.service.IContentService;
import com.tj.ex.service.IDeleteService;
import com.tj.ex.service.IListService;
import com.tj.ex.service.IModifyService;
import com.tj.ex.service.IModifyViewService;
import com.tj.ex.service.IWriteService;
import com.tj.ex.service.LListService;
import com.tj.ex.service.LWriteService;
import com.tj.ex.service.NContentService;
import com.tj.ex.service.NDeleteService;
import com.tj.ex.service.NListService;
import com.tj.ex.service.NModifyService;
import com.tj.ex.service.NModifyViewService;
import com.tj.ex.service.NWriteService;
import com.tj.ex.service.OContentService;
import com.tj.ex.service.ODeleteService;
import com.tj.ex.service.OListService;
import com.tj.ex.service.OModifyService;
import com.tj.ex.service.OModifyViewService;
import com.tj.ex.service.OWriteService;
import com.tj.ex.service.QContentService;
import com.tj.ex.service.QDeleteService;
import com.tj.ex.service.QListService;
import com.tj.ex.service.QModifyService;
import com.tj.ex.service.QModifyViewService;
import com.tj.ex.service.QReplyService;
import com.tj.ex.service.QReplyViewService;
import com.tj.ex.service.QWriteService;
import com.tj.ex.service.Service;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int write_view = 0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri     = request.getRequestURI();
		String conPath = request.getContextPath();
		String com     = uri.substring(conPath.length()); //들어온 요청
		String viewPage = null;
		Service service = null;
		
		/* ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ Customer ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ */
		if(com.equals("/joinView.do")) { 
			viewPage = "customer/join.jsp";
		}else if(com.equals("/join.do")) {
			service = new CJoinService();
			service.execute(request, response);
			viewPage = "customer/login.jsp";
		}else if(com.equals("/loginView.do")) {
			viewPage = "customer/login.jsp";
		}else if(com.equals("/login.do")) {
			service = new CLoginService();
			service.execute(request, response);
			viewPage = "customer/main.jsp";
		}else if(com.equals("/logout.do")) {
			service = new CLogoutService();
			service.execute(request, response);
			viewPage = "customer/main.jsp";
		}else if(com.equals("/modifyView.do")) {
			viewPage = "customer/modify.jsp";
		}else if(com.equals("/modify.do")) {
			service = new CModifyService();
			service.execute(request, response);
			viewPage = "customer/modify.jsp";
		}else if(com.equals("/cAllView.do")) {
			/* ◆◆◆ admin login successs ◆◆◆ */
			service = new CAllViewService();
			service.execute(request, response);
			viewPage = "customer/cAllView.jsp";
		}else if(com.equals("/main.do")) {
			viewPage = "main/main.jsp";
		}else if(com.equals("/cIdConfirm.do")) {
			viewPage = "customer/cIdConfirm.jsp";
			
			
			
		/* ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ Freeboard ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ */
		}else if(com.equals("/list.do")) {
			service = new FListService();
			service.execute(request, response);
			viewPage = "freeboard/list.jsp";
		}else if(com.equals("/write_view.do")) {
			write_view = 1;
			viewPage = "freeboard/write_view.jsp";
		}else if(com.equals("/write.do")) {
			if(write_view == 1) {
				service = new FWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "list.do";
		}else if(com.equals("/content_view.do")) {
			service = new FContentService();
			service.execute(request, response);
			viewPage = "freeboard/content_view.jsp";
		}else if(com.equals("/boardModify_view.do")) {
			service = new FModifyViewService();
			service.execute(request, response);
			viewPage = "freeboard/modify_view.jsp";
		}else if(com.equals("/boardModify.do")) {
			service = new FModifyService();
			service.execute(request, response);
			viewPage = "freeboard/list.jsp";
		}else if(com.equals("/delete.do")) {
			service = new FDeleteService();
			service.execute(request, response);
			viewPage = "list.do";
		}else if(com.equals("/reply_view.do")) {
			service = new FReplyViewService();
			service.execute(request, response);
			viewPage = "freeboard/reply_view.jsp";
		}else if(com.equals("/reply.do")) {
			service = new FReplyService();
			service.execute(request, response);
			viewPage = "list.do";
					
			
			
		/* ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ Qboard ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ */
		}else if(com.equals("/list.do")) {
			service = new QListService();
			service.execute(request, response);
			viewPage = "fileboard/list.jsp";
		}else if(com.equals("/write_view.do")) {
			write_view = 1;
			viewPage = "fileboard/write_view.jsp";
		}else if(com.equals("/write.do")) {
			if(write_view == 1) {
				service = new QWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "list.do";
		}else if(com.equals("/content_view.do")) {
			service = new QContentService();
			service.execute(request, response);
			viewPage = "fileboard/content_view.jsp";
		}else if(com.equals("/boardModify_view.do")) {
			service = new QModifyViewService();
			service.execute(request, response);
			viewPage = "fileboard/modify_view.jsp";
		}else if(com.equals("/boardModify.do")) {
			service = new QModifyService();
			service.execute(request, response);
			viewPage = "list.do";
		}else if(com.equals("/delete.do")) {
			service = new QDeleteService();
			service.execute(request, response);
			viewPage = "list.do";
		}else if(com.equals("/reply_view.do")) {
			service = new QReplyViewService();
			service.execute(request, response);
			viewPage = "fileboard/reply_view.jsp";
		}else if(com.equals("/reply.do")) {
			service = new QReplyService();
			service.execute(request, response);
			viewPage = "list.do";
			
			
			
		/* ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ Nboard ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ */
		}else if(com.equals("/list.do")) {
			service = new NListService();
			service.execute(request, response);
			viewPage = "fileboard/list.jsp";
		}else if(com.equals("/write_view.do")) {
			write_view = 1;
			viewPage = "fileboard/write_view.jsp";
		}else if(com.equals("/write.do")) {
			if(write_view == 1) {
				service = new NWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "list.do";
		}else if(com.equals("/content_view.do")) {
			service = new NContentService();
			service.execute(request, response);
			viewPage = "fileboard/content_view.jsp";
		}else if(com.equals("/boardModify_view.do")) {
			service = new NModifyViewService();
			service.execute(request, response);
			viewPage = "fileboard/modify_view.jsp";
		}else if(com.equals("/boardModify.do")) {
			service = new NModifyService();
			service.execute(request, response);
			viewPage = "list.do";
		}else if(com.equals("/delete.do")) {
			service = new NDeleteService();
			service.execute(request, response);
			viewPage = "list.do";
		
			
			
		/* ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ Oafter ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ */
		}else if(com.equals("/list.do")) {
			service = new OListService();
			service.execute(request, response);
			viewPage = "fileboard/list.jsp";
		}else if(com.equals("/write_view.do")) {
			write_view = 1;
			viewPage = "fileboard/write_view.jsp";
		}else if(com.equals("/write.do")) {
			if(write_view == 1) {
				service = new OWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "list.do";
		}else if(com.equals("/content_view.do")) {
			service = new OContentService();
			service.execute(request, response);
			viewPage = "fileboard/content_view.jsp";
		}else if(com.equals("/boardModify_view.do")) {
			service = new OModifyViewService();
			service.execute(request, response);
			viewPage = "fileboard/modify_view.jsp";
		}else if(com.equals("/boardModify.do")) {
			service = new OModifyService();
			service.execute(request, response);
			viewPage = "list.do";
		}else if(com.equals("/delete.do")) {
			service = new ODeleteService();
			service.execute(request, response);
			viewPage = "list.do";
			
			
			
		/* ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ Itemlist ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ */
		}else if(com.equals("/list.do")) {
			service = new IListService();
			service.execute(request, response);
			viewPage = "fileboard/list.jsp";
		}else if(com.equals("/write_view.do")) {
			write_view = 1;
			viewPage = "fileboard/write_view.jsp";
		}else if(com.equals("/write.do")) {
			if(write_view == 1) {
				service = new IWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "list.do";
		}else if(com.equals("/content_view.do")) {
			service = new IContentService();
			service.execute(request, response);
			viewPage = "fileboard/content_view.jsp";
		}else if(com.equals("/boardModify_view.do")) {
			service = new IModifyViewService();
			service.execute(request, response);
			viewPage = "fileboard/modify_view.jsp";
		}else if(com.equals("/boardModify.do")) {
			service = new IModifyService();
			service.execute(request, response);
			viewPage = "list.do";
		}else if(com.equals("/delete.do")) {
			service = new IDeleteService();
			service.execute(request, response);
			viewPage = "list.do";
			
			
			
		/* ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ Listitem ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ */
		}else if(com.equals("/list.do")) {
			service = new LListService();
			service.execute(request, response);
			viewPage = "fileboard/list.jsp";
		}else if(com.equals("/write_view.do")) {
			write_view = 1;
			viewPage = "fileboard/write_view.jsp";
		}else if(com.equals("/write.do")) {
			if(write_view == 1) {
				service = new LWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "list.do";
		
			
			
		/* ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ Admin ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ */
		}else if(com.equals("/adminloginView.do")) {
			viewPage = "admin/adminLogin.jsp";
		}else if(com.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "cAllView.do";
		}
	RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	dispatcher.forward(request, response);
	}
}