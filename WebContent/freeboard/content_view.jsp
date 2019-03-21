<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<form action="${conPath}/boardModify_view.do" method="post">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="fNo" value="${content_view.fNo }">
		<table>
				 <caption>${content_view.fNo }번글 상세보기</caption>
				 <tr><td>작성자</td>
				 		 <td>${content_view.cId}님</td>
				 </tr>
				 <tr><td>제목</td>
				 		 <td>${content_view.fSubject }</td>
				 </tr>
				 <tr><td>본문</td>
				 		 <td><pre>${content_view.fContent}</pre></td>
				 </tr>
				 <tr><th>첨부파일</th>
						 <td>
						 	<c:if test="${not empty content_view.fFilename }">
						 		<a href="${conPath }/fileboardUp/${content_view.fFilename}" target="_blank">${content_view.fFilename}</a>
						 	</c:if>
						 	<c:if test="${empty content_view.fFilename }">
						 		첨부파일없음
						 	</c:if>
						</td>
				 </tr>
				 <tr><td colspan="2">
				 			<c:if test="${customer.cId eq content_view.cId }">
				 				<input type="submit" value="수정">
				 			</c:if>
				 			<c:if test="${customer.cId eq content_view.cId or not empty admin}">
				 				<input type="button" value="삭제"
				 					onclick="location='${conPath}/delete.do?fNo=${content_view.fNo }&pageNum=${param.pageNum }'">
				 			</c:if>
				 			<c:if test="${not empty customer }">
				 				<input type="button" value="답변"
				 				onclick="location='${conPath}/reply_view.do?fNo=${content_view.fNo}&pageNum=${param.pageNum}'">
				 			</c:if>
				 			<input type="button" value="목록"
				 	onclick="location='${conPath}/list.do?pageNum=${param.pageNum }'">	
				 	</td>
				 </tr>		 
		</table>
	</form>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>