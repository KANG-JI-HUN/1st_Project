<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/style.css" rel="stylesheet">
	<style>
		#content {
			width: 800px; height:400px;
			margin: 100px auto 0px;
		}
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content">
	<form action="${conPath }/boardModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="fNo" value="${modify_view.fNo }">
		<input type="hidden" name="dbFileName" value="${modify_view.fFilename }">
		<table>
			<caption>${modify_view.fNo }번 글 수정(page:${param.pageNum })</caption>
			<tr><th>작성자</th>
					<td><input type="text" required="required" size="30"
								value="${modify_view.cName }(${modify_view.cId })" readonly="readonly"></td>
			</tr>
			<tr><th>제목</th>
					<td><input type="text" name="fTitle" required="required" size="30"
								value="${modify_view.fSubject }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5" cols="32" 
							name="fContent">${modify_view.fContent }</textarea></td>
			</tr>
			<tr><th>첨부파일</th>
					<td><input type="file" name="fFilename"> 원첨부파일:
							<c:if test="${not empty modify_view.fFilename }">
						 		<a href="${conPath }/fileboardUp/${modify_view.fFilename}" target="_blank">${modify_view.fFilename}</a>
						 	</c:if>
						 	<c:if test="${empty modify_view.fFilename }">
						 		첨부파일없음
						 	</c:if>
			<tr><td>비밀번호</td><td><input type="text" name="fPw"></td></tr>
					</td>
			</tr>
			<tr><td colspan="2">
						<input type="submit" value="수정">
						<input type="button" value="목록" 
							onclick="location='${conPath}/list.do?pageNum=${param.pageNum }'">
						<input type="reset" value="취소" onclick="history.back()">
					</td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>