<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
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
	.header h1 {
		font-family: 'Pacifico', cursive;
	}
	.form-label{	
		font-family: 'Dongle', sans-serif;
		font-size: 2em;
	}
	.form-control {
		background-color: #e3f2fd;
	}
</style>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>
<section>
<div class="container">
	<div class="header">
		<h1>Board Register Page</h1>
	</div>
	<div class="main">
		<form action="/board/register" method="post" enctype="multipart/form-data">
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">제목</label>
			  <input type="text" name="title" class="form-control" id="exampleFormControlInput1" placeholder="제목">
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">작성자</label>
				<input class="form-control" type="text" name="writer" value="${ses.id }" aria-label="readonly input example" readonly>
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label">내용</label>
			  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
			</div>
			
			<!-- 파일 업로드 -->
			<div class="col-12 d-grid">
  				<input class="form-control" type="file" style="display: none;"
  				 id="files" name="files" multiple>	<!-- multiple 파일 여러개 첨부 -->
  				<button type="button" id="trigger" class="btn btn-outline-primary btn-block d-block">파일 업로드</button>
			</div>
			<!-- 첨부한 파일명 표시 -->
			<!-- 파일 업로드 조건을 만족한 아이들만 이곳에 표시 -->
			<div class="col-12" id="fileZone">
				
			</div>
			
			<button type="submit" id="regBtn" class="btn btn-outline-danger">등록</button>
			<a href="/board/list"><button type="button" class="btn btn-outline-primary btn2">리스트</button></a>
			<a href="/"><button type="button" class="btn btn-primary">홈으로</button></a>
		</form>
	</div>
</div>
<%-- <form action="/board/register" method="post">
	<input type="text" name="title" placeholder="제목"> <br>
	<input type="text" name="writer" value="${ses.id }" readonly="readonly"> <br>
	<textarea rows="10" cols="30" name="content" placeholder="내용입력"></textarea> <br>	
	<button type="submit" class="btn btn-outline-danger">등록</button>
	<a href="/board/list"><button type="button" class="btn btn-outline-primary btn2">리스트</button></a>
	<a href="/index.jsp"><button type="button" class="btn btn-primary">홈으로</button></a>
</form> --%>

</section>
<script src="/resources/js/boardRegister.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>