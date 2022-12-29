<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>	<!-- index에서 세션 못씀 -->
<style>
	section {		
		position: fixed;
		top : 72px;
		width: 100%;
		height : 100%;
		background-color: bisque;
		text-align: center;
		background: linear-gradient(rgba(0, 0, 0, 0),rgba(0, 0, 0, 0.7)),url("https://lh3.googleusercontent.com/pw/AL9nZEWnfIBUzXG2aURDfoalZgDSvhNOVZ58mZyxcKviGOSDhkxwr6QCnoMDLGleZwLUe7x92fgVP3baRvItnnYDzYr-1P8Jq0X_WVfF2hzs9wwxaTmn464fALO17NVw2413TwLEB1hOD3QUXxsyoBfUlBr_gg=w608-h335-no?authuser=1");
	    background-size: cover;
	    color : white;
	}
</style>
<jsp:include page="./layout/header.jsp"></jsp:include>
<section>
<div class="container">
	<div class="header">
		<h1>
			Hello world!  
		</h1>
	</div>
	<div class="main">
		<P>  My Spring Project </P>
	</div>
</div>
</section>
<jsp:include page="./layout/footer.jsp"></jsp:include>
