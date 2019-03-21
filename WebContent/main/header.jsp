<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>모아젤데이지</title>
	<style>
	/* ★ ★ ★ ★ ★ 초기화 ★ ★ ★ ★ ★  */
	*{padding:0; margin:0;}
	li{list-style:none;}
	a{text-decoration:none; color:black;}
	
	/* ★ ★ ★ ★ ★ header의 .gnb ★ ★ ★ ★ ★  */
	header{font-size: 15px;}
	header .gnb{width:100%; background-color: #f6f6f6;  border-bottom:1px solid black;}
	header .gnb ul{overflow:hidden; width:90%; height:40px; line-height: 40px; margin:0 auto;}
	header .gnb ul li{float:right; margin-right: 30px;}
	
	/* ★ ★ ★ ★ ★ header의 .logo ★ ★ ★ ★ ★  */
	header .logo{width:500px; height:150px; line-height:100px; text-align: center; margin:100px auto 30px; font-size:4.7em; cursor:pointer; font-weight: bold;}
	header .logo a{color:#ED8163;}
	header .logo a:hover{color:#F6EC2E;}
	
	/* ★ ★ ★ ★ ★ header의 .mark ★ ★ ★ ★ ★  */
	header form .mark{width:100%; margin-bottom:30px;}
	header form .mark ul{width:95%; height:40px; margin:0 auto;}
	header form .mark ul li{float:right; margin: 5px 5px 5px auto;}
	header form .mark ul li p{margin-top: 5px;}
	header form .mark ul li .subMenu {display:none; position:relative; font-size: 1.1em; font-weight: bold; border:1px solid red;}
	header form .mark ul li:hover .subMenu{display:block; margin:0; padding-right:30px; font-size:2em;}
	
	/* ★ ★ ★ ★ ★ header의 .lnb ★ ★ ★ ★ ★  */
	header .lnb{width:1500px; margin:0 auto; text-align: center; font-size:1.1em;}
	header .lnb ul{overflow:hidden; width:100%; cursor:pointer; margin-left:1.5%; }
	header .lnb ul > li {float:left; width: 11%; padding:5px 20px; line-height: 40px; border:1px solid gray;}
	header .lnb ul li .subMenu {display:none; position:absolute; font-size: 1.1em; font-weight: bold; background-color: #F4584A; border:1px solid red; }
	header .lnb ul li:hover .subMenu{display:block; margin:0; z-index:1;}
	
	/* z-index와 position:relative사용해서 이동시켜보기 */

	</style>
</head>
<body>
<header>
	<c:if test="${empty customer and empty admin}"> <!-- 로그인 전 화면 -->
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/mypageView.do">마이페이지</a></li>
				<li><a href="${conPath }/likeView.do">관심상품</a></li>
				<li><a href="${conPath }/orderView.do">주문조회</a></li>
				<li><a href="${conPath }/joinView.do">회원가입</a></li>
				<li><a href="${conPath }/loginView.do">로그인</a></li>
			</ul>
		</div>
		<div class="logo" onclick="location.href='${conPath }/main.do'">
			<ul>
				<li>
					<a href="#" onMouseOver="this.innerHTML='모아젤 데이지'" onMouseOut="this.innerHTML='MoaJel Daisy'">MoaJel Daisy</a>
				</li>
			</ul>
		</div>
		<form>
		<div class="mark">
            <ul>
            	<li>
            	<a href="#"><img src="${conPath }/image/bell.png" alt="종이미지"></a>
            	<ol class="subMenu">
		            	<li><a href="${conPath }/list.do">자유게시판</a></li>
		            	<li><a href="#">상품후기</a></li>
		            	<li><a href="#">QnA</a></li>
            			<li><a href="#">공지사항</a></li>
            	</ol>
            	</li>
                <li><a href="https://www.facebook.com/"><img src="${conPath }/image/facebook.gif" alt="페이스북 마크"></a></li>
                <li><a href="https://story.kakao.com/ch/kakaostory"><img src="${conPath }/image/kakao story.gif" alt="카카오스토리 마크"></a></li>
                <li><a href="https://www.instagram.com/?hl=ko"><img src="${conPath }/image/instagram.gif" alt="인스타그램 마크"></a></li>              
                <li><p><a href="#"><img src="${conPath }/image/glass.png" alt="돋보기"/></a></p></li>
                <li><p><input type="text" name="queryword" class="text" /></p></li>                		
            </ul>        
        </div>
        </form>
		<div class="lnb">
			<ul>
				<li>귀걸이<ol class="subMenu">
						<li><a href="#">실버침·알레르기방지</a></li>
						<li><a href="#">패션귀걸이</a></li>
						<li><a href="#">링귀걸이</a></li>
						<li><a href="#">롱귀걸이</a></li>
						<li><a href="#">원터치</a></li>
					</ol>
				</li>
				<li>목걸이
					<ol class="subMenu">
						<li><a href="#">패션목걸이</a></li>
						<li><a href="#">초커목걸이</a></li>
						<li><a href="#">롱목걸이</a></li>
						<li><a href="#">실버목걸이</a></li>
						<li><a href="#">골드목걸이</a></li>
					</ol>
				</li>	
				<li>팔찌/발찌
					<ol class="subMenu">
						<li><a href="#">실버팔찌</a></li>
						<li><a href="#">게르마늄팔찌</a></li>
						<li><a href="#">레더팔찌</a></li>
						<li><a href="#">발찌</a></li>
						<li><a href="#">레더발찌</a></li>
					</ol>
				</li>	
				<li>헤어악세사리
					<ol class="subMenu">
						<li><a href="#">헤어핀</a></li>
						<li><a href="#">집게핀</a></li>
						<li><a href="#">바나나핀</a></li>
						<li><a href="#">헤어밴드</a></li>
						<li><a href="#">헤어스타일링</a></li>
					</ol>
				</li>
				<li>반지
					<ol class="subMenu">
						<li><a href="#">실버반지</a></li>
						<li><a href="#">골드반지</a></li>
						<li><a href="#">패션반지</a></li>
						<li><a href="#">커플링</a></li>
					</ol>
				</li>
				<li>시계
					<ol class="subMenu">
						<li><a href="#">가죽시계</a></li>
						<li><a href="#">메탈시계</a></li>
					</ol>
				</li>
				<li>선글라스/모자/안경
					<ol class="subMenu">
						<li><a href="#">선글라스</a></li>
						<li><a href="#">모자</a></li>
						<li><a href="#">안경</a></li>
					</ol>
				</li>
			</ul>
		</div>
	</c:if>
	<c:if test="${not empty customer and empty admin }"> <!-- 사용자 모드 로그인 화면 -->
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/mypageView.do">마이페이지</a></li>
				<li><a href="${conPath }/logout.do">로그아웃</a></li>
				<li><a href="${conPath }/likeView.do">관심상품</a></li>
				<li><a href="${conPath }/orderView.do">주문조회</a></li>
				<li><a href="${conPath }/modifyView.do">정보수정</a></li>
				<li><a>${customer.cName }님 &nbsp; ▶</a></li>
			</ul>
		</div>
		<div class="logo" onclick="location.href='${conPath }/main.do'">
			<ul>
				<li>
					<a href="#" onMouseOver="this.innerHTML='모아젤 데이지'" onMouseOut="this.innerHTML='MoaJel Daisy'">MoaJel Daisy</a>
				</li>
			</ul>
		</div>
		<form>
		<div class="mark">
            <ul>
            	<li>
            	<a href="#"><img src="${conPath }/image/bell.png" alt="종이미지"></a>
            	<ol class="subMenu">
		            	<li><a href="${conPath }/list.do">자유게시판</a></li>
		            	<li><a href="#">상품후기</a></li>
		            	<li><a href="#">QnA</a></li>
            			<li><a href="#">공지사항</a></li>
            	</ol>
            	</li>
                <li><a href="https://www.facebook.com/"><img src="${conPath }/image/facebook.gif" alt="페이스북 마크"></a></li>
                <li><a href="https://story.kakao.com/ch/kakaostory"><img src="${conPath }/image/kakao story.gif" alt="카카오스토리 마크"></a></li>
                <li><a href="https://www.instagram.com/?hl=ko"><img src="${conPath }/image/instagram.gif" alt="인스타그램 마크"></a></li>              
                <li><p><a href="#"><img src="${conPath }/image/glass.png" alt="돋보기"/></a></p></li>
                <li><p><input type="text" name="queryword" class="text" /></p></li>                		
            </ul>        
        </div>
        </form>
		<div class="lnb">
			<ul>
				<li>귀걸이<ol class="subMenu">
						<li><a href="#">실버침·알레르기방지</a></li>
						<li><a href="#">패션귀걸이</a></li>
						<li><a href="#">링귀걸이</a></li>
						<li><a href="#">롱귀걸이</a></li>
						<li><a href="#">원터치</a></li>
					</ol>
				</li>
				<li>목걸이
					<ol class="subMenu">
						<li><a href="#">패션목걸이</a></li>
						<li><a href="#">초커목걸이</a></li>
						<li><a href="#">롱목걸이</a></li>
						<li><a href="#">실버목걸이</a></li>
						<li><a href="#">골드목걸이</a></li>
					</ol>
				</li>	
				<li>팔찌/발찌
					<ol class="subMenu">
						<li><a href="#">실버팔찌</a></li>
						<li><a href="#">게르마늄팔찌</a></li>
						<li><a href="#">레더팔찌</a></li>
						<li><a href="#">발찌</a></li>
						<li><a href="#">레더발찌</a></li>
					</ol>
				</li>	
				<li>헤어악세사리
					<ol class="subMenu">
						<li><a href="#">헤어핀</a></li>
						<li><a href="#">집게핀</a></li>
						<li><a href="#">바나나핀</a></li>
						<li><a href="#">헤어밴드</a></li>
						<li><a href="#">헤어스타일링</a></li>
					</ol>
				</li>
				<li>반지
					<ol class="subMenu">
						<li><a href="#">실버반지</a></li>
						<li><a href="#">골드반지</a></li>
						<li><a href="#">패션반지</a></li>
						<li><a href="#">커플링</a></li>
					</ol>
				</li>
				<li>시계
					<ol class="subMenu">
						<li><a href="#">가죽시계</a></li>
						<li><a href="#">메탈시계</a></li>
					</ol>
				</li>
				<li>선글라스/모자/안경
					<ol class="subMenu">
						<li><a href="#">선글라스</a></li>
						<li><a href="#">모자</a></li>
						<li><a href="#">안경</a></li>
					</ol>
				</li>
			</ul>
		</div>
	</c:if>
	<c:if test="${empty customer and not empty admin}"> <%-- 관리자 모드 로그인 화면--%>
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/logout.do">관리자모드나가기</a></li>
				<li><a>${admin.aName }님 &nbsp; ▶</a></li>
			</ul>
		</div>
		<div class="logo" onclick="location.href='${conPath }/main.do'">
			<ul>
				<li>
					<a href="#" onMouseOver="this.innerHTML='모아젤 데이지'" onMouseOut="this.innerHTML='MoaJel Daisy'">MoaJel Daisy</a>
				</li>
			</ul>
		</div>
		<form>
		<div class="mark">
            <ul>
            	<li>
            	<a href="#"><img src="${conPath }/image/bell.png" alt="종이미지"></a>
            	<ol class="subMenu">
		            	<li><a href="${conPath }/list.do">자유게시판</a></li>
		            	<li><a href="#">상품후기</a></li>
		            	<li><a href="#">QnA</a></li>
            			<li><a href="#">공지사항</a></li>
            	</ol>
            	</li>
                <li><a href="https://www.facebook.com/"><img src="${conPath }/image/facebook.gif" alt="페이스북 마크"></a></li>
                <li><a href="https://story.kakao.com/ch/kakaostory"><img src="${conPath }/image/kakao story.gif" alt="카카오스토리 마크"></a></li>
                <li><a href="https://www.instagram.com/?hl=ko"><img src="${conPath }/image/instagram.gif" alt="인스타그램 마크"></a></li>              
                <li><p><a href="#"><img src="${conPath }/image/glass.png" alt="돋보기"/></a></p></li>
                <li><p><input type="text" name="queryword" class="text" /></p></li>                		
            </ul>        
        </div>
        </form>
		<div class="lnb">
			<ul>
				<li>귀걸이<ol class="subMenu">
						<li><a href="#">실버침·알레르기방지</a></li>
						<li><a href="#">패션귀걸이</a></li>
						<li><a href="#">링귀걸이</a></li>
						<li><a href="#">롱귀걸이</a></li>
						<li><a href="#">원터치</a></li>
					</ol>
				</li>
				<li>목걸이
					<ol class="subMenu">
						<li><a href="#">패션목걸이</a></li>
						<li><a href="#">초커목걸이</a></li>
						<li><a href="#">롱목걸이</a></li>
						<li><a href="#">실버목걸이</a></li>
						<li><a href="#">골드목걸이</a></li>
					</ol>
				</li>	
				<li>팔찌/발찌
					<ol class="subMenu">
						<li><a href="#">실버팔찌</a></li>
						<li><a href="#">게르마늄팔찌</a></li>
						<li><a href="#">레더팔찌</a></li>
						<li><a href="#">발찌</a></li>
						<li><a href="#">레더발찌</a></li>
					</ol>
				</li>	
				<li>헤어악세사리
					<ol class="subMenu">
						<li><a href="#">헤어핀</a></li>
						<li><a href="#">집게핀</a></li>
						<li><a href="#">바나나핀</a></li>
						<li><a href="#">헤어밴드</a></li>
						<li><a href="#">헤어스타일링</a></li>
					</ol>
				</li>
				<li>반지
					<ol class="subMenu">
						<li><a href="#">실버반지</a></li>
						<li><a href="#">골드반지</a></li>
						<li><a href="#">패션반지</a></li>
						<li><a href="#">커플링</a></li>
					</ol>
				</li>
				<li>시계
					<ol class="subMenu">
						<li><a href="#">가죽시계</a></li>
						<li><a href="#">메탈시계</a></li>
					</ol>
				</li>
				<li>선글라스/모자/안경
					<ol class="subMenu">
						<li><a href="#">선글라스</a></li>
						<li><a href="#">모자</a></li>
						<li><a href="#">안경</a></li>
					</ol>
				</li>
			</ul>
		</div>
	</c:if>
</header>
</body>
</html>