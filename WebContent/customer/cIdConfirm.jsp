<%@page import="com.tj.ex.dao.CustomerDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String cId = request.getParameter("cId");
		CustomerDao cDao = new CustomerDao();
		int result = cDao.cIdConfirm(cId);
		if(result==CustomerDao.MEMBER_EXISTENT){
	%>		중복된 ID입니다
	<%}else{%>
			사용가능한 ID입니다
	<% } %>
</body>
</html>