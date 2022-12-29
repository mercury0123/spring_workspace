<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
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
		height : 100%;
		background-color: bisque;
	}
	.container{
        width: 600px;
        margin: 0;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
        text-align: center;
    }
    .container h1 {
    	font-family: 'Dongle', sans-serif;
    }
</style>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>
<section>
<div class="container">
	<h1>회원가입</h1>
       <form action="/member/signup" method="post">
       		<div class="mb-3">			  
			  <input type="text" name="id" class="form-control" id="exampleFormControlInput1" placeholder="아이디입력">
			</div>
       		<div class="mb-3">			  
			  <input type="password" name="pw" class="form-control" id="exampleFormControlInput1" placeholder="비밀번호입력">
			</div>
       		<div class="mb-3">			  
			  <input type="text" name="age" class="form-control" id="exampleFormControlInput1" placeholder="나이입력">
			</div>
       		<div class="mb-3">			  
			  <input type="text" name="name" class="form-control" id="exampleFormControlInput1" placeholder="이름입력">
			</div>
       		<div class="mb-3">			  
			  <input type="text" name="email" class="form-control" id="exampleFormControlInput1" placeholder="이메일입력">
			</div>
       		<div class="mb-3">			  
			  <input type="text" name="home" class="form-control" id="exampleFormControlInput1" placeholder="주소입력">
			</div>
		       
		    <button type="submit" class="btn btn-outline-danger">회원가입</button>
			<a href="/member/login"><button type="button" class="btn btn-outline-primary btn2">로그인</button></a>
			<a href="/"><button type="button" class="btn btn-primary">홈으로</button></a>
		</form>
     
</div>  
</section> 
<jsp:include page="../layout/footer.jsp"></jsp:include>
<script type="text/javascript">
	const msg = '<c:out value="${msg}"/>';
	if (msg ==='0'){
		console.log(msg);
		alert('이미 가입된 회원입니다');
	}
</script>
</body>
</html>	