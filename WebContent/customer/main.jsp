<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${conPath}/css/style.css" rel="stylesheet">
<title>Insert title here</title>
<link rel="stylesheet" href="${conPath }/css/test2.css" type="text/css">
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.18.5/TweenMax.min.js"></script>
	<script>
	var current = 0;
	var max = 0;
	var container;
	var interval;
	var interval;
	function animate() {
		var moveX = current * 600;
		TweenMax.to( container, 0.8, { marginLeft: -moveX, ease:Expo.easeOut } );
	}
	function keydown(e){
		if(e.which == 39){
			alert('next');
			$("button.next").trigger('click');
		}else if(e.which == 37){
			alert('prev');
			$("button.prev").trigger('click');
		}
	}
	$( document ).ready(function(){
		container = $(".slide ul");
		max = container.children().length;
		$("button.prev").click(function(){
			current --;
			if( current < 0) current = max-1;
			animate();
		});
		$("button.next").click(function(){
			current ++;
			if( current > max-1 ) current = 0;
			animate();
		});
		jQuery(window).on("keydown", keydown);
		
		interval = setInterval(function(){
			$("button.next").trigger('click');
		}, 3000);
	});
	</script>
<style>
/**************************** 초기화 **************************************/
* {
    margin: 0;
    padding: 0;
}

a {
    text-decoration: none;
    color: black;
}

/*********************************** #content **************************************/
#content .slide .prev{z-index:1; }
#content .slide{width:600px; height: 600px; margin:30px auto;}

</style>
</head>
<body>
<jsp:include page="../main/header.jsp"/> 
	<c:if test="${not empty modifyResultMsg }">
		<script>
		alert('${modifyResultMsg }');
		</script>
	</c:if>
	<c:if test="${not empty loginErrorMsg }">
		<script>
			alert('${loginErrorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty customer }">
		<div id="content">
		<div class="slide">
			<button class="prev" type="button">
				<img src="${conPath }/img/prev.png" alt="" />
			</button>
			<ul>
				<li><img src="${conPath }/img/pic1.jpg" /></li>
				<li><img src="${conPath }/img/pic2.jpg" /></li>
				<li><img src="${conPath }/img/pic3.jpg" /></li>
				<li><img src="${conPath }/img/pic4.jpg" /></li>
				<li><img src="${conPath }/img/pic5.jpg" /></li>
			</ul>
			<button class="next" type="button">
				<img src="${conPath }/img/next.png" alt="">
			</button>
		</div>
	</div>
	</c:if>
	<c:if test="${empty customer }">
		<div id="content">
		<div class="slide">
			<button class="prev" type="button">
				<img src="${conPath }/img/prev.png" alt="" />
			</button>
			<ul>
				<li><img src="${conPath }/img/pic1.jpg" /></li>
				<li><img src="${conPath }/img/pic2.jpg" /></li>
				<li><img src="${conPath }/img/pic3.jpg" /></li>
				<li><img src="${conPath }/img/pic4.jpg" /></li>
				<li><img src="${conPath }/img/pic5.jpg" /></li>
			</ul>
			<button class="next" type="button">
				<img src="${conPath }/img/next.png" alt="">
			</button>
		</div>
	</div>
	</c:if>
	<jsp:include page="../main/footer.jsp"/> 
</body>
</html>