<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Page</title>
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
		<h1>Board Modify Page</h1>
	</div>
	<div class="main">
		<form action="/board/modify" method="post" enctype="multipart/form-data">
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">글번호</label>
			  <input type="text" name="bno" value="${board.bno }" class="form-control" id="exampleFormControlInput1" readonly>
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">제목</label>
			  <input type="text" name="title" value="${board.title }" class="form-control" id="exampleFormControlInput1" >
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">작성자</label>
				<input class="form-control" type="text" name="writer" value="${board.writer}" aria-label="readonly input example" readonly>
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label">내용</label>
			  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="content">${board.content }</textarea>
			</div>
			
			<!-- 파일 표현라인 -->
		<div class="form=group">
			<ul>
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
					<button type="button" data-uuid=${fvo.uuid} class="btn btn-sm btn-danger py-0 file-x">X</button>
				</li>				
				</c:forEach>
			</ul>		
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
			
			<button type="submit" id="regBtn" class="btn btn-outline-danger">수정</button>
			<a href="/board/list"><button type="button" class="btn btn-outline-primary btn2">리스트</button></a>
			<a href="/"><button type="button" class="btn btn-primary">홈으로</button></a>
		</form>
	</div>
</div>
</section>
<script src="/resources/js/boardRegister.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>