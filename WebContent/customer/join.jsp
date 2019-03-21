<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link href="${conPath }/css/style.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
	$(document).ready(function(){
		$('#cIdConfirm').click(function(){
			var cId = $('input[name="cId"]').val();
			$.ajax({
				url : '${conPath}/cIdConfirm.do',
				type : 'get',
				dataType : 'html',
				data : "cId="+cId,
				success : function(data){
					$('#cIdConfirmMsg').html("<b>"+data+"</b>");
				}
			}); // ajax
		}); // cIdConfirm의 click이벤트
		$('input[name="cPwChk"]').keyup(function(){
			if($('input[name="cPw"]').val() ==
				$('input[name="cPwChk"]').val()){
				$('#cPwChkMsg').html('<b>비밀번호 일치</b>');
			} else {
				$('#cPwChkMsg').html('<b>비밀번호 불일치</b>');
			} // if
		}); //input[name="cpwChk"]의 keyup이벤트
	});
	</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/> 
	<form action="${conPath }/join.do" method="post"
									enctype="multipart/form-data" name="frm" onsubmit="return chk()">
	<table>
		<caption>회원가입</caption>
		<tr><th>아이디</th>
			<td><input type="text" name="cId" required="required">
				<input type="button" id="cIdConfirm" value="중복체크"><br>
				<div id="cIdConfirmMsg"> &nbsp; &nbsp; &nbsp; </div>
			</td>
		</tr>
		<tr><th>비밀번호</th>
			<td><input type="password" name="cPw" required="required"></td>
		</tr>
		<tr><th>비밀번호확인</th>
			<td><input type="password" name="cPwChk" required="required">
				<div id="cPwChkMsg"> &nbsp; &nbsp; &nbsp; </div>
			</td>
		</tr>
		<tr><th>이름</th>
			<td><input type="text" name="cName" required="required"></td>
		</tr>
		<tr><th>전화번호</th>
			<td><input type="tel" name="cTel"></td>
		</tr>
		<tr><th>성별</th>
			<td><input type="radio" name="cGender" value="M">남
				<input type="radio" name="cGender" value="F">여
			</td>
		</tr>
		<tr><th>주소</th>
			<td><input type="text" name="cAddr"></td>
		</tr>
		<tr><td colspan="2">
			<input type="submit" value="회원가입">
			<input type="button" value="로그인"
				onclick="location.href='${conPath}/loginView.do'"></td>
		</tr>
	</table>
	</form>
	<jsp:include page="../main/footer.jsp"/> 
</body>
</html>