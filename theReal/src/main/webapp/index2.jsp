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
<script type="text/javascript" src="scripts/jqueryui.js"></script>
<script type="text/javascript" src="scripts/framework.plugins.js"></script>
<script type="text/javascript" src="scripts/custom.js"></script>

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
    <div class="header">
        <h1 align="center">전자영수증</h1>
       <!--  <em>더리얼마케팅</em> -->
        <a href="#" class="header-icon-one show-top-menu bg6-blur"><i class="fa fa-navicon"></i></a>
    </div>    
    <div class="header-clear"></div>

    <ul class="top-menu">
        <li class="active-menu"><a href="#"><i class="fa fa-home bg-blue2"></i>최근사용내역조회<i class="fa fa-circle"></i></a></li> 
        <li class="active-menu"><a href="#"><i class="fa fa-home bg-blue2"></i>영수증조회<i class="fa fa-circle"></i></a></li> 
        <li class="has-submenu">
            <a class="deploy-submenu" href="#"><i class="fa fa-camera bg-green1"></i>가계부<i class="fa fa-plus"></i></a>
            <ul class="submenu">
                <li><a href="gallery-adaptive.html"><i class="fa fa-angle-right"></i>이번달<i class="fa fa-circle"></i></a></li>
                <li><a href="gallery-square.html"><i class="fa fa-angle-right"></i>저번달<i class="fa fa-circle"></i></a></li>
            </ul>
        </li>                      
        <li class="has-submenu">
            <a class="deploy-submenu" href="#"><i class="fa fa-file bg-teal2"></i>My 설정<i class="fa fa-plus"></i></a>
            <ul class="submenu">
                <li><a href="page-error.html"><i class="fa fa-angle-right"></i>My쿠폰<i class="fa fa-circle"></i></a></li>
                <li><a href="page-soon.html"><i class="fa fa-angle-right"></i>My정보수정<i class="fa fa-circle"></i></a></li>
            </ul>
        </li>                
        <li><a href="#" class="close-menu"><i class="fa fa-times bg-red2"></i>닫기<i class="fa fa-circle"></i></a></li>  
    </ul>
    <div class="menu-background"></div>
    
    <div class="content">
        <div class="container">
	        <div style="margin-bottom: 10px;">
		        <div class="container no-bottom" style="color:yellow; background-color:#16336C; padding:20px;">
		            <p style="color: white;margin-top: 3px; font-size: medium;"  align="center"  >
		            최근사용내역
		            </p>
		            <div style="text-align: center;">
			          	<a href="#" class="go-btn"  style="text-align: center;" id="goUseList" ><span>바로가기</span></a>
		            </div>
		        </div>
	        </div>
	        
	        <div style="margin-bottom: 10px;">
		        <div class="container no-bottom" style="color:yellow; background-color:#0C63AA; padding:20px;">
		            <p style="color: white;margin-top: 3px; font-size: medium;"  align="center"  >
		            영수증확인
		            </p>
		            <div style="text-align: center;">
			          	<a href="#" class="go-btn"  style="text-align: center;" ><span>바로가기</span></a>
		            </div>		            
		          <!--   <a href="#" ><span  style="border: 1px solid white; color: white; width: 30%; height: 30%;"  >바로가기</span></a> -->
		        </div>
	        </div>
	        
	        <div style="margin-bottom: 10px;">
		        <div class="container no-bottom" style="color:yellow; background-color:#EBB529; padding:20px;">
		            <p style="color: white;margin-top: 3px; font-size: medium;"  align="center"  >
		            가계부<br/>
		            </p>
		            <div style="text-align: center;">
			          	<a href="#" class="go-btn"  style="text-align: center;" ><span>바로가기</span></a>
		            </div>		            
		          <!--   <a href="#" ><span  style="border: 1px solid white; color: white; width: 30%; height: 30%;"  >바로가기</span></a> -->
		        </div>
	        </div>
	        <div style="margin-bottom: 10px;">
		        <div class="container no-bottom" style="color:yellow; background-color:#3B3B3B; padding:20px;">
		            <p style="color: white;margin-top: 3px; font-size: medium;"  align="center"  >
		            할인 혜택을 한번에<br/>
		            <b>EVENT</b>
		            </p>
		            <div style="text-align: center;">
			          	<a href="#" class="go-btn"  style="text-align: center;" ><span>바로가기</span></a>
		            </div>		            
		          <!--   <a href="#" ><span  style="border: 1px solid white; color: white; width: 30%; height: 30%;"  >바로가기</span></a> -->
		        </div>
	        </div>        
	        
	        <div style="margin-bottom: 10px;">
		        <div class="container no-bottom banner-list" style="color:yellow; background-color:#192752; padding:200px;">
		        </div>        
	        </div>     
	        
	        <div style="margin-bottom: 10px;">
		        <div class="container no-bottom" style="color:yellow; background-color:#666; ">
		            <p style="color: white;margin-top: 3px; font-size: medium; vertical-align: middle; "  align="center"   >
						<a href="#" style="color: white;margin-top: 3px; font-size: medium;">이용약관 | </a><a href="#" style="color: white;margin-top: 3px; font-size: medium;">개인정보 취급방침 | </a><a href="#" style="color: white;margin-top: 3px; font-size: medium;">공지&뉴스 </a>
		            </p>
		        </div>
	        </div>                    
        </div>
        <div class="decoration"></div>
    </div>
    
    <!-- Footer -->
    
    <div class="footer">
        <p class="center-text">Copyright (c) 2016 The REAL All rights reserved</p>
<!--         <div class="footer-socials half-bottom">
            <a href="#" class="footer-facebook" ><i class="fa fa-facebook"></i></a>
            <a href="#" class="footer-twitter"><i class="fa fa-twitter"></i></a>
            <a href="#" class="footer-google"><i class="fa fa-google-plus"></i></a>
            <a href="#" class="footer-share show-share-bottom"><i class="fa fa-share-alt"></i></a>
            <a href="#" class="footer-up"><i class="fa fa-angle-up"></i></a>
        </div> -->
    </div>    
    <a href="#" class="header-up"><i class="fa fa-caret-up"></i>맨위로</a>
    
    <!-- Share Menu -->
        
    <div class="share-bottom">
        <h3>Share Page</h3>
        <div class="share-socials-bottom">
            <a href="https://www.facebook.com/sharer/sharer.php?u=http://www.themeforest.net/">
                <i class="fa fa-facebook facebook-color"></i>
                Facebook
            </a>
            <a href="https://twitter.com/home?status=Check%20out%20ThemeForest%20http://www.themeforest.net">
                <i class="fa fa-twitter twitter-color"></i>
                Twitter
            </a>
            <a href="https://plus.google.com/share?url=http://www.themeforest.net">
                <i class="fa fa-google-plus google-color"></i>
                Google
            </a>

            <a href="https://pinterest.com/pin/create/button/?url=http://www.themeforest.net/&media=https://0.s3.envato.com/files/63790821/profile-image.jpg&description=Themes%20and%20Templates">
                <i class="fa fa-pinterest-p pinterest-color"></i>
                Pinterest
            </a>
            <a href="sms:">
                <i class="fa fa-comment-o sms-color"></i>
                Text
            </a>
            <a href="mailto:?&subject=Check this page out!&body=http://www.themeforest.net">
                <i class="fa fa-envelope-o mail-color"></i>
                Email
            </a>
        </div>
        <a href="#" class="close-share-bottom">Close</a>
    </div>
    
</div>
<script type="text/javascript">
$(document).ready(function(){    

	$("#goUseList").bind("click",function(e){
    	    
    });
});




</script>    
</body>