<%@page import="com.tj.ex.dao.FreeboardDao"%>
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
		for(int i=0 ; i<55 ; i++){
			FreeboardDao dao = new FreeboardDao();
			dao.write("aaa", "제목"+i, i+"번째 본문", null, "aaa", "192.168.10.151");
		}
		response.sendRedirect("../list.do");
	%>
</body>
</html>