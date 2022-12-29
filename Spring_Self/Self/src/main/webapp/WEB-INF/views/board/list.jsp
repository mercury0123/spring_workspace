<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List page</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Dongle:wght@700&display=swap" rel="stylesheet">
</head>
<style>
	@font-face{
         font-family: 'Pacifico', cursive;
         src:url(font/Pacifico/Pacifico-Regular.ttf)
    }
    
    @import url('https://fonts.googleapis.com/css2?family=Dongle:wght@700&display=swap');

	section {		
		position: fixed;
		top : 72px;
		width: 100%;
		height : 90%;
		background-color: bisque;
		text-align: center;
	}
	.container {
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%,-50%);
		margin: auto;
		text-align: center;  
	}	
	
	.header h1{
		font-family: 'Pacifico', cursive;
	}
	
	form {
		
		margin: auto;
		text-align: center;
	}
	.list table {
		margin-left:auto; 
		margin-right:auto;
	}

	.list table, tr, td, th {	    
	    border : 1px solid black;
	    text-align: center;
	}
	
	.main a {
		text-decoration: none;
	}
	
	.main a:hover {
		color : green;
	}
</style>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>
<section>
<div class="container">
	<div class="header">
		<h1>Board List Page</h1>
	</div>
	<div class="main">
		<!-- search -->
	 	<div class="col-sm-12 col-md-6">
			<form action="/board/list" method="get">
				<div class="input-group mb-3">
					<!-- 값을 별도 저장 -->
					<c:set value="${pgh.pgvo.type }" var="typed"/>
		  			<select class="form-select" name="type">
		    			<option ${typed == null ? 'selected':'' }>Choose...</option>
		    			<option value="t" ${typed eq 't' ? 'selected':'' }>Title</option>
		    			<option value="c" ${typed eq 'c' ? 'selected':'' }>Content</option>
		    			<option value="w" ${typed eq 'w' ? 'selected':'' }>Writer</option> 
		  			</select>
		
		  			<input class="form-control" type="text" name="keyword" placeholder="Search" value="${pgh.pgvo.keyword }">
		  			<input type="hidden" name="pageNo" value="1">
		  			<input type="hidden" name="qty" value="${pgh.pgvo.qty }">
		  			<button type="submit" class="btn btn-success position-relative">Search
			  			<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
				    		${pgh.totalCount }
				    		<span class="visually-hidden">unread messages</span>
			    		</span>
		  			</button>
				</div>
			</form>
		</div>
		<div class="list">
			<table border="1">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="brd">
						<tr>
							<td>${brd.bno }</td>
							<td>					
								<a href="/board/detail?bno=${brd.bno }">${brd.title }</a>
							</td>
							<td>${brd.writer }</td>
							<td>${brd.registerDate }</td>
							<td>${brd.read_count}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<br>
	<div class="page">
		<p>
		<c:if test="${pgh.prev }">
			<a href="/board/list?pageNo=${pgh.startPage-1 }&qty=${pgh.pgvo.qty }"><button class="btn btn-outline-success btn1">prev</button></a>
		</c:if>
		<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
			<a href="/board/list?pageNo=${i }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type }&keyword=${pgh.pgvo.keyword }"><button class="btn btn-success btn1">${i }</button></a>
		</c:forEach>
		<c:if test="${pgh.next }">
			<a href="/board/list?pageNo=${pgh.endPage+1 }&qty=${pgh.pgvo.qty }"><button class="btn btn-outline-success btn1">next</button></a>
		</c:if>
		</p>
	</div>
	<a href="/board/register"><button type="submit" class="btn btn-outline-primary" >글쓰기</button></a>
	<a href="/"><button type="submit" class="btn btn-primary">홈으로</button></a>
	
	
</div>
</section>
<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>