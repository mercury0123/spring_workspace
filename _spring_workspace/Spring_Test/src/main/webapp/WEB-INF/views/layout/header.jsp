<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<style>
	header{
		position :fixed;
		top: 0px;
		width: 100%;
	}
</style>

<body>
<header>
<nav class="navbar" style="background-color: #e3f2fd;">
	<nav class="navbar navbar-expand-lg bg-light">
	  <div class="container-fluid">
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <a class="navbar-brand" href="/">▦HOME</a>
	    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="/member/signup">회원가입</a>
	        </li>
	        <c:if test="${ses.id == null}">
	        	<li class="nav-item">
	         		<a class="nav-link" href="/member/login">로그인</a>
	        	</li>
	        </c:if>
	        <c:if test="${ses.id != null}">
	        	<li class="nav-item">
	         		<a class="nav-link" href="/member/logout">로그아웃</a>
	        	</li>
	        	<li class="nav-item">
	         		<a class="nav-link" href="/board/register">글쓰기</a>
	        	</li>
	        </c:if>
	        
	        <li class="nav-item">
	          <a class="nav-link" href="/board/list">게시판</a>
	        </li>
	        <c:if test="${ses.id == null}">
	        	<li class="nav-item">
	         		<a class="nav-link" href="/member/login">로그아웃상태입니다!!</a>
	        	</li>
	        </c:if>
	        <c:if test="${ses.id != null}">
	        	<li class="nav-item">
	          		<a class="nav-link" href="/">${ses.id } welcome!!</a>
	        	</li>
	        </c:if>
	      </ul>
	      <!-- <form class="d-flex" role="search">
	        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
	        <button class="btn btn-outline-success" type="submit">Search</button>
	      </form> -->
	    </div>
	  </div>
	</nav>
</nav>
</header>
</body>
</html>