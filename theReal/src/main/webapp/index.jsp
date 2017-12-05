<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0 minimal-ui"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>더리얼마케팅 전자영수증</title>

<link href="styles/style.css"     		 rel="stylesheet" type="text/css">
<link href="styles/framework.css" 		 rel="stylesheet" type="text/css">
<link href="styles/owl.theme.css" 		 rel="stylesheet" type="text/css">
<link href="styles/swipebox.css"		 rel="stylesheet" type="text/css">
<link href="styles/font-awesome.css"	 rel="stylesheet" type="text/css">
<link href="styles/animate.css"			 rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery.placeholder.js"></script>
<script type="text/javascript" src="scripts/jqueryui.js"></script>
<script type="text/javascript" src="scripts/framework.plugins.js"></script>
<script type="text/javascript" src="scripts/custom.js"></script>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
<style type="text/css">
.placeholder { color: #aaa; }
</style>
</head>
<body> 

<div id="preloader">
	<div id="status">
    	<p class="center-text">
			앱을 로딩중입니다...
            <em>전자영수증 어플 실행준비중 입니다.</em>
        </p>
    </div>
</div>
<div class="all-elements">
    <div class="header-clear2"></div>
    
</div>
<!--Fullscreen Pages Go Under the all-elements class -->
    
<div class="coverpage-slider" data-snap-ignore="true">                     
     <div id="cov1">
<!--         <div class="overlay dark-overlay"></div> -->
        <div class="overlay "></div>
        <div class="coverpage-image indexbg1 coverpage-style-1">
            <p style="top:50%; margin-top:-50px;">
                헤매지 말아요!
            </p>
            <a href="#" style="top:75%; margin-top:50px; left:50%; margin-left:-50px;" class="coverpage-button"id = "nextBtn1">다음</a>
        </div>
    </div>

 <div id="cov2">
<!--         <div class="overlay dark-overlay"></div> -->
        <div class="overlay "></div>
        <div class="coverpage-image indexbg2 coverpage-style-3">
            <p style="left:50%; margin-left:-140px; top:50%; margin-top:-20px;">
                헤매지 말아요!
            </p>
            <a href="#" style="top:75%; margin-top:50px; left:50%; margin-left:-50px;" class="coverpage-button" id = "nextBtn2">다음</a>
        </div>
    </div>

 <div id="cov3">
<!--         <div class="overlay dark-overlay"></div> -->
        <div class="overlay"></div>
        <div class="coverpage-image indexbg3 coverpage-style-3">
            <p style="left:50%; margin-left:-140px; top:50%; margin-top:-20px;">
                헤매지 말아요!
            </p>
            <a href="#" style="top:75%; margin-top:50px; left:50%; margin-left:-50px;" class="coverpage-button" id = "startBtn">서비스 시작하기</a>
        </div>
    </div>

</div> 
<div id="addDivBtn">
		<!-- <a class="button center-button2 button-green detected-button" href="#" id="addBtn2" >다음</a> -->
		<a class="button center-button2 button-blue detected-button" href="#" id="loginBtn" >로그인</a>
</div>
<script type="text/javascript">
/* $(function() {
	}); */
 $(document).ready(function(){

	$("#startBtn").click(function(){
		location.href='/receipt/theReal/index.do';
	 })
	 
	 $("#nextBtn1").click(function(){
		 var move =$(".owl-item").width();
		 var moveSize = "translate3d(-"+move+"px, 0px, 0px)";
		 $(".owl-wrapper").css("transform",moveSize);
		 $('.owl-page').eq(0).removeClass("active");
		 $('.owl-page').eq(1).addClass("active");
	 })
	 $("#nextBtn2").click(function(){
		 var move2 =$(".owl-item").width()*2;
		 var moveSize2 = "translate3d(-"+move2+"px, 0px, 0px)";
		 $(".owl-wrapper").css("transform",moveSize2);
		 $('.owl-page').eq(1).removeClass("active");
		 $('.owl-page').eq(2).addClass("active");
	 })
	 
	 $("#loginBtn").click(function(){
		 location.href='/receipt/theReal/index.do';
	 })
})

</script>
</body>
