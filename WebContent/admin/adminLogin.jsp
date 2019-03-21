<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 로그인</title>
	<link href="${conPath}/css/style.css" rel="stylesheet">
<style>
	#content {
		width: 800px; height:370px;
		margin: 130px auto 0px;
	}
	#content td{padding:10px 20px;}
</style>
</head>
<body>
<c:if test="${not empty admin }">
	<script>location.href='${conPath }/cAllView.do';</script>
</c:if>
<c:if test="${not empty customer }">
	<script>alert('관리자 권한으로 접근하세요.'); history.back();</script>
</c:if>
<jsp:include page="../main/header.jsp"/>					<!-- include는 conPath가 안먹는다!!!! -->
<div id="content">
	<form action="${conPath}/adminLogin.do" method="post">
		<table style="width: 50%;">
			<caption>CUSTOMER LOGIN<br>
						관리자 로그인</caption>
			<tr><th>admin ID</th>
				<td><input type="text" name="aId"
				 required="required" autofocus="autofocus"></td>
			</tr>
			<tr><th>PW</th>
				<td><input type="password" name="aPw" required="required"></td>
			</tr>
			<tr><td colspan="2"><input type="submit" value="로그인"></td></tr>
		</table>
	</form>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>