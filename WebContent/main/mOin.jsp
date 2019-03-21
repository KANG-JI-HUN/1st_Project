<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${conPath}/css/style.css" rel="stylesheet">
<title>Insert title here</title>
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
#content {
    width: 1000px;
    height: 1500px;
    margin: 30px auto;
    padding:2px auto;
    text-align: center;
}

/************************* #content의 .section1 **************************************/
    #content .section1 {
       
        width: 100%;
        height: 600px;
        
    }

.slide_banner {
    width: 70%;
    height: 590px;
    border: 1px solid gray;
    float: left;
    line-height: 590px;
}

.side_banner {
    width: 29%;
    height: 590px;
    border: 1px solid gray;
    float: left;
    line-height: 590px;
}

/************************* #content의 .section2 **************************************/
#content .section2 {
    width: 980px;
    height: 622px;
    border: 1px solid gray;
    margin-bottom:30px;
    padding: 10px 10px 0 10px;
}

.hit_product ul {
    width: 956px;
    height: 300px;
    float: left;
    border: 1px solid gray;
    list-style: none;
    margin: 0 0 10px 0;
    text-align: center;
    padding: 0 10px;
}

    .hit_product ul li {
        width: 230px;
        height: 298px;
        float: left;
        border: 2px solid gray;
        margin: 0 2px;
    }

        .hit_product ul li a {
            color: blue;
            text-align: center;
            line-height: 298px;
            font-weight: bold;
        }

.md_product ul {
    width: 956px;
    height: 300px;
    float: left;
    border: 1px solid gray;
    padding: 0 10px;
    list-style: none;
}

    .md_product ul li {
        width: 230px;
        height: 298px;
        float: left;
        border: 2px solid gray;
        text-align: center;
        margin: 0 2px;
    }

        .md_product ul li a {
            color: blue;
            line-height: 298px;
            font-weight: bold;
        }
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	 <div id="content">
        <div class="section1">
            <div class="slide_banner">slide_banner</div>
            <div class="side_banner">side_banner</div>
             <ul><li><a href=#><img src="${conPath }/image/watch.jpg" alt="시계사진"></a></li></ul>
        </div>
        <div class="section2">
            <div class="hit_product">
                <ul>
                    <li><a href="#none">hit_product1</a></li>
                    <li><a href="#none">hit_product2</a></li>
                    <li><a href="#none">hit_product3</a></li>
                    <li><a href="#none">hit_product4</a></li>
                </ul>
            </div>
            <div class="md_product">
                <ul>
                    <li><a href="#none">md_product1</a></li>
                    <li><a href="#none">md_product2</a></li>
                    <li><a href="#none">md_product3</a></li>
                    <li><a href="#none">md_product4</a></li>
                </ul>
            </div>
        </div>
    </div>

	<jsp:include page="../main/footer.jsp"/>
</body>
</html>