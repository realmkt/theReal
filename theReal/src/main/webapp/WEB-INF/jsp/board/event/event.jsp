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
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/style.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/framework.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/owl.theme.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/swipebox.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/font-awesome.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/animate.css'/>" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
<script src="<c:url value='/scripts/jquery.js'/>" charset="utf-8"></script>
<script src="<c:url value='/scripts/jqueryui.js'/>" charset="utf-8"></script>
<script src="<c:url value='/scripts/framework.plugins.js'/>" charset="utf-8"></script>
<script src="<c:url value='/scripts/custom.js'/>" charset="utf-8"></script>
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
        
<div class="all-elements main-bg-img">
    <div class="header header-bg-img">
        <!-- <h1 align="center">전자영수증</h1> -->
       <!--  <em>더리얼마케팅</em> -->
       <a href ="javascript:void(0)" onclick="javascript:history.go(-1)" class="header-icon-three bg3-blur"><i class="fa fa-chevron-left"></i></a>
        <a href="/receipt/theReal/loginSucess.do" class="header-icon-two bg3-blur"><i class="fa fa-th-large"></i></a>
        <a href="#" class="header-icon-one show-top-menu bg6-blur"><i class="fa fa-navicon"></i></a>
    </div>    
    <div class="header-clear"></div>

    <ul class="top-menu">
        <li class="active-menu"><a href="/receipt/theReal/E01.do"><i class="fa fa-home bg-blue2"></i>최근사용내역조회<i class="fa fa-circle"></i></a></li> 
        <li class="active-menu"><a href="/receipt/theReal/F01.do"><i class="fa fa-home bg-blue2"></i>영수증조회<i class="fa fa-circle"></i></a></li> 
        <li class="active-menu"><a href="/receipt/theReal/goEventPage.do"><i class="fa fa-home bg-blue2"></i>이벤트<i class="fa fa-circle"></i></a></li> 
        <li class="active-menu"><a href="/receipt/theReal/notice.do"><i class="fa fa-home bg-blue2"></i>게시판<i class="fa fa-circle"></i></a></li> 
        <li class="has-submenu">
            <a class="deploy-submenu" href="#"><i class="fa fa-camera bg-green1"></i>가계부<i class="fa fa-plus"></i></a>
            <ul class="submenu">
                <li><a href="gallery-adaptive.html"><i class="fa fa-angle-right"></i>이번달<i class="fa fa-circle"></i></a></li>
                <li><a href="gallery-square.html"><i class="fa fa-angle-right"></i>저번달<i class="fa fa-circle"></i></a></li>
            </ul>
        </li>                      
<!--         <li class="has-submenu">
            <a class="deploy-submenu" href="#"><i class="fa fa-file bg-teal2"></i>My 설정<i class="fa fa-plus"></i></a>
            <ul class="submenu">
                <li><a href="page-error.html"><i class="fa fa-angle-right"></i>My쿠폰<i class="fa fa-circle"></i></a></li>
                <li><a href="page-soon.html"><i class="fa fa-angle-right"></i>My정보수정<i class="fa fa-circle"></i></a></li>
            </ul>
        </li> -->                
        <li><a href="#" class="close-menu"><i class="fa fa-times bg-red2"></i>닫기<i class="fa fa-circle"></i></a></li>  
    </ul>
    <div class="menu-background"></div>
    
    <div class="content">       

        <div class="adaptive-style">
            <h4>이벤트</h4>
        </div>
        <div class="clear"></div>
        <div class="portfolio-adaptive adaptive-three">
            <div class="adaptive-item">
                <a href="#" title="이벤트페이지" >
                    <img class="responsive-image" src="<c:url value='/images/board/event/event01.png'/>" alt="이벤트페이지">
                </a>
            </div>
 
        </div>
        <div class="decoration"></div>
    </div>
    
    <!-- Footer -->
    
   <!-- Footer -->
    
    <div class="footer">
        <p class="center-text">Copyright (c) 2016 The REAL All rights reserved</p>
    </div>    
    <a href="#" class="header-up"><i class="fa fa-caret-up"></i>맨위로</a>
    
    <!-- Share Menu -->
        
    
</div>
    
</body>
