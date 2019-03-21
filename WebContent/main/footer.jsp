<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	body{background-color: white;}
	footer {width:100%; background-color: #F6EC2E;}
	footer #footer_conts, footer #footer_conts a {
		color:black;
		font-weight:bold;
		font-size:1.1em;
		text-align: center;
	}
	footer #footer_conts p:first-child {
		line-height: 30px;
	}
</style>
</head>
<body>
	<footer>
		<div id="footer_conts">
			<p>상호명:(주)모아젤데이지|대표:강지훈|개인정보보호책임자:김태욱</p> 
			<p>사업장소재지:서울특별시 강남구 논현동 강남대로21 스타골드빌딩 | <b><a href="${conPath }/adminloginView.do">관리자 모드</a></b></p>
			<p>사업자등록번호:751-9815-8901</p>
			<p>통신판매업신고번호:제2019-강남논현-0821호
			<p>Copyright© 2019 Star. All rights reserved.</p>
		</div>
	</footer>
</body>
</html>