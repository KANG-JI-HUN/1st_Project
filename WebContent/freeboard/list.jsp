<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/style.css" rel="stylesheet">
</head>
<style>
	#content {
		width: 1200px; height:50%;
		margin: 70px auto 70px;
	}
</style>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('tr').click(function(){
			var fNo = Number($(this).children(0).eq(0).text()); // 0번째 td안의 있는 text;
			if(!isNaN(fNo)){
				location.href = '${conPath}/content_view.do?fNo='+fNo+'&pageNum=${pageNum}';
			}
		});
	});
</script>
<body>
<jsp:include page="../main/header.jsp"/>
<div id="content">
	<c:if test="${not empty resultMsg }">
		<script>alert('${resultMsg}');</script>
	</c:if>
	<c:if test="${not empty loginErrorMsg}">
		<script>
			alert('${loginErrorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<table>
		<tr><td>
					<c:if test="${not empty customer }"><a href="${conPath }/write_view.do">글쓰기</a></c:if>
					<c:if test="${empty customer }"><a href="${conPath }/loginView.do">글쓰기는 사용자 로그인 이후에만 가능합니다</a></c:if>
		</td></tr>
	</table>
	<table>
		<tr><th>글번호</th><th>회원아이디</th><th>글제목</th><th>조회수</th>
				<th>날짜</th><th>ip</th></tr>
		<c:if test="${totCnt==0 }">
			<tr><td colspan="6">글이 없습니다</td></tr>
		</c:if>
		<c:if test="${totCnt!=0 }">
			<c:forEach items="${list }" var="dto">
				<tr><td>${dto.fNo }</td>
						<td>${dto.cId }</td>
						<td class="left">
							<c:forEach var="i" begin="1" end="${dto.fIndent }">
								<c:if test="${i==dto.fIndent }">└─</c:if>
								<c:if test="${i!=dto.fIndent }"> &nbsp; &nbsp; </c:if>
							</c:forEach>
							${dto.fSubject }
						</td>
						<td>${dto.fReadcount }</td>
						<td><fmt:formatDate value="${dto.fDate }" 
											type="date" dateStyle="short"/></td>
						<td>${dto.fIp }</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/list.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				<b> [ ${i } ] </b>
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/list.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/list.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
	</div>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>