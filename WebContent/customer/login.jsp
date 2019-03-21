<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<style>
	#content {
		width: 800px; height:370px;
		margin: 130px auto 0px;
	}
	#content td{padding:10px 20px;}
</style>
	<link href="${conPath}/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../main/header.jsp"/> 
	<c:if test="${not empty errorMsg }">
		<script>
		alert('${errorMsg}');
		history.back();
		</script>
	</c:if>
	<c:if test="${not empty msg }">
		<script>alert('${msg}')</script>
	</c:if>
	<form action="${conPath }/login.do" method="post">
	<table>
		<caption>CUSTOMER LOGIN<br>
				          고객로그인</caption>
		<tr><th>아이디</th>
			<td><input type="text" name="cId" value="${cId }"  <%-- value="${sessionScope.cId }"에서 sessionScope은 생략가능 --%>
				 required="required"></td>
		</tr>
		<tr><th>비밀번호</th>
			<td><input type="password" name="cPw" required="required"></td>
		</tr>
		<tr><td colspan="2">
				<input type="submit" value="로그인">
				<input type="button" value="회원가입" onclick="location.href='${conPath}/joinView.do'">
			</td>
		</tr>
	</table>
	</form>
	<jsp:include page="../main/footer.jsp"/> 
</body>
</html>