<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath}/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../main/header.jsp"/> 
<form action="${conPath}/modify.do" method="post"
	enctype="multipart/form-data">
	<table>
		<tr><th>아이디</th>
			<td><input type="text" name="cId" value="${customer.cId }"
					readonly="readonly">
			</td>
		</tr>
		<tr><th>비밀번호</th>
			<td><input type="password" name="cPw" value="${customer.cPw }"
				required="required">
			</td>
		</tr>
		<tr><th>이름</th>
			<td><input type="text" name="mName" value="${customer.cName }"
				required="required">
			</td>
		</tr>
		<tr><th>전화번호</th>
			<td><input type="tel" name="cTel" value="${customer.cTel }">
			</td>
		</tr>
		<tr><th>이메일</th>
			<td><input type="email" name="cEmail" value="${customer.cEmail }">
			</td>
		</tr>
		<tr><th>성별</th>
			<td><input type="radio" name="cGender" value="${customer.cGender }">남
				<input type="radio" name="cGender" value="${customer.cGender }">여
			</td>
		</tr>
		<tr><th>주소</th>
			<td colspan="2">
				<input type="text" name="cAddr" value="${customer.cAddr }">
			</td>
		</tr>
		<tr><td colspan="3">
				<input type="submit" value="정보수정">	
				<input type="reset" value="취소">
				<input type="reset" value="이전" onclick="history.go(-1)">
			</td>
		</tr>
	</table>
</form>
<jsp:include page="../main/footer.jsp"/> 
</body>
</html>