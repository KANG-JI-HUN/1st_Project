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
	<table>
		<caption>전체회원보기</caption>
		<tr>
			<c:forEach var="dto" items="${cAllView }">
				<td>
					${dto.cName }<br>
					(${dto.cId })
				</td>
			</c:forEach>
		</tr>
	</table>
	<div class="paging">
		<a href="${conPath }/cAllView.do?pageNum=1">&lt;&lt;</a>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${startPage>BLOCKSIZE }">
			<a href="${conPath }/cAllView.do?pageNum=${startPage-1}">&lt;</a> <!-- 마지막에 꺾쇠괄호 표시(&lt; -> '>')-->
		</c:if>
		<c:if test="${startPage<=BLOCKSIZE }">
			&lt;
		</c:if>
		&nbsp; &nbsp; &nbsp;
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				[ <b>${i }</b> ]
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath}/cAllView.do?pageNum=${i }">${i }</a>]
			</c:if>
		</c:forEach>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${endPage < pageCnt }">
			<a href="${conPath }/cAllView.do?pageNum=${endPage+1}">&gt;</a>
		</c:if>
		<c:if test="${endPage == pageCnt }">
			&gt;
		</c:if>
		&nbsp; &nbsp; &nbsp;
		<a href="${conPath }/cAllView.do?pageNum=${pageCnt}">&gt;&gt;</a>
	</div>
	<jsp:include page="../main/footer.jsp"/> 
</body>
</html>