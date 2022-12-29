<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.container-footer {
	position: fixed;		
	bottom : 0px;
	width:100%; 
	height: 80px;	
	text-align: center;
	line-height: 40px;
	font-size: 20px;
	background-color: #e3f2fd;
	
}

.container-footer a{
	text-decoration: none;
}
</style>

<div class="container-footer">
	<a href="/">HOME</a>&nbsp;|&nbsp;
    <a href="/member/signup">회원가입</a>&nbsp;|&nbsp;
    <c:if test="${ses.id == null}">
    	<a href="/member/login">로그인</a>&nbsp;|&nbsp;
    </c:if>
    <c:if test="${ses.id != null}">
    	<a href="/member/logout">로그아웃</a>&nbsp;|&nbsp;
    	<a href="/board/register">글쓰기</a>&nbsp;|&nbsp;
    </c:if>
    <a href="/board/list">게시판</a><br>
  2022 Winter NiziU Project
</div>
</body>
</html>