<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Page</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
</head>
<style>
	@font-face{
        font-family: 'Pacifico', cursive;
        src:url(font/Pacifico/Pacifico-Regular.ttf)
    }
    
	section {		
		position: fixed;
		top : 72px;
		width: 100%;
		height : 100%;
		background-color: bisque;
		text-align: center;
	}
	
	.header h1{
		font-family: 'Pacifico', cursive;
	}
	table {
		margin-left:auto; 
		margin-right:auto;
		
	}

	table, td, th {	    
	    border : 1px solid black;
	    text-align: center;
	}
</style>
<body onload=getCommentList(${board.bno})>	<!-- 페이지 불러오면 body에 list 출력 -->
<jsp:include page="../layout/header.jsp"></jsp:include>
<section>
<div class="container">
	<div class="header">
		<h1>Board Detail Page</h1>
	</div>
	<div class="body">		
	
		<table border="1">
			<tr>
				<th>게시글 번호</th>
				<td>${board.bno}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${board.title}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${board.writer}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${board.registerDate}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${board.read_count}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${board.content}</td>
			</tr>			
		</table>
		<!-- 파일 업로드 조건을 만족한 아이들만 이곳에 표시 -->
			<div class="col-12" id="fileZone">
				
			</div>
			
		<!-- 파일 표현라인 -->
		<div class="form=group">
			<ul class="list-group list-group-flush">
				<c:forEach items="${fList} " var="fvo">
				
				<!-- 파일이 여러개 일 때 반복적으로 li 추가 -->
				<li>
				<!-- 이미지일때만 -->
				<c:choose>
					<c:when test="${fvo.file_type > 0 }">
						<div>
							<!-- D:\\image\\ -> image/ 로 수정 -->
							<img src="/upload/${fn:replace(fvo.save_dir,'\\','/')}/${fvo.uuid}_th_${fvo.file_name}">	<!-- 주의: 공백없이 처리해야함 -->
							<!-- _th_ 있으면 썸네일 이미지 -->
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<!-- 파일 모양 아이콘 라인 -->
						</div>
					</c:otherwise>
				</c:choose>
					<!-- 파일이름, 등록일, 사이즈 -->
					<div class="ms-2 me-auto">
						<div class="fw-bold">${fvo.file_name}</div>
						${fvo.reg_at}
					</div>
					<span class="badge bg-secondary rounded-pill">${fvo.file_size} Byte</span>
				</li>				
				</c:forEach>
			</ul>		
		</div>
	
		<!--  작성자가 일치해야만 수정삭제 가능  -->
		<!-- c태그 "" 사이에 빈공간 없어야만 작동 -->
		<c:if test="${ses.id eq board.writer}">
			<a href="/board/modify?bno=${board.bno }"><button type="submit" class="btn btn-outline-primary">수정</button></a>
			<a href="/board/remove?bno=${board.bno }"><button type="submit" class="btn btn-outline-danger">삭제</button></a>
		</c:if>
		<a href="/board/list"><button type="submit" class="btn btn-outline-primary" >게시판</button></a>
		<a href="/board/register"><button type="submit" class="btn btn-outline-primary" >글쓰기</button></a>
		<a href="/"><button type="submit" class="btn btn-outline-primary">홈으로</button></a> 
	</div>
	
		
	<div class="container">
	
		<!-- 댓글 입력 영역 -->
		<div class="input-group my-3">
			<span class="input-group-text" id="cmtWriter">${ses.name }</span>
			<input type="text" class="form-control" id="cmtText" placeholder="댓글입력란">
			<button class="btn btn-success" id="cmtPostBtn" type="button">댓글등록</button>
		</div>
		<!-- 댓글 출력 영역 -->
		<ul class="list-group list-group-flush" id="cmtListArea">
		  <li class="list-group-item d-flex justify-content-between align-items-start">
		    <div class="ms-2 me-auto">
		      <div class="fw-bold">작성자</div>
		      Content for Comment
		    </div>
		    <span class="badge bg-dark rounded-pill">modAt</span>
		  </li>
		</ul>
		
	</div>
	<script type="text/javascript">
		const bnoVal = '<c:out value="${board.bno }"/>';
		console.log(bnoVal);
	</script>
</div>
</section>
<script type="text/javascript" src="/resources/js/boardComment.js">

</script>
<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>