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
        <a href ="/receipt/theReal/logout.do" class="header-icon-one bg3-blur" id="logOutBtn"><i class="fa fa-sign-out"></i></a>
        <a href="#" class="header-icon-two show-top-menu bg6-blur"><i class="fa fa-navicon"></i></a>
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
        <div class="container">
	        <div style="margin-bottom: 10px;">
		        <div class="container no-bottom" style="color:yellow; background-color:#16336C; padding:20px;">
		            <p style="color: white;margin-top: 3px; font-size: medium;"  align="center"  >
		            최근사용내역
					<c:choose>
						<c:when test="${fn:length(list.resultMap) > 0}">
							<c:forEach var="row" items="${list.resultMap}" varStatus="status">
									<br/><a  style="color: white;margin-top: 3px; font-size: medium;"  href="#" id="goUseDtl${row.SEQ }">${row.USE_DATE } ${row.ETP_NM } : ${row.PAY_AM }원</a>
							</c:forEach>	
						</c:when>
						<c:otherwise>
							<br/>조회된 결과가 없습니다.
						</c:otherwise>
					</c:choose>		            
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
		            <br/><a style="color: white;margin-top: 3px; font-size: medium;"  href="#" id="receiptArea"></a>
		            </p>
		            <div style="text-align: center;">
			          	<a href="#" class="go-btn"  style="text-align: center;" id="goReceiptList" ><span>바로가기</span></a>
		            </div>		            
		          <!--   <a href="#" ><span  style="border: 1px solid white; color: white; width: 30%; height: 30%;"  >바로가기</span></a> -->
		        </div>
	        </div>
	        
	        <div style="margin-bottom: 10px;">
		        <div class="container no-bottom" style="color:yellow; background-color:#EBB529; padding:20px;">
		            <p style="color: white;margin-top: 3px; font-size: medium;"  align="center"  >
		            가계부
		            <br/><a style="color: white;margin-top: 3px; font-size: medium;"  href="#" id="diaryArea">누적사용액 테스트</a>
		            </p>
		            <div style="text-align: center;">
			          	<a href="#" class="go-btn"  style="text-align: center;" ><span>바로가기</span></a>
		            </div>		            
		          <!--   <a href="#" ><span  style="border: 1px solid white; color: white; width: 30%; height: 30%;"  >바로가기</span></a> -->
		        </div>
	        </div>
	        <div style="margin-bottom: 10px;">
		        <div class="container no-bottom" style="color:yellow; background-color:#3B3B3B; padding:20px;" >
		            <p style="color: white;margin-top: 3px; font-size: medium;"  align="center"  >
		            할인 혜택을 한번에<br/>
		            <b>EVENT</b>
		            </p>
		            <div style="text-align: center;">
			          	<a href="#" class="go-btn"  style="text-align: center;"  id="goEventPage" ><span>바로가기</span></a>
		            </div>		            
		          <!--   <a href="#" ><span  style="border: 1px solid white; color: white; width: 30%; height: 30%;"  >바로가기</span></a> -->
		        </div>
	        </div>        

<!-- 			<div>
		        <div class="container no-bottom banner-list" style="color:yellow; background-color:#192752; padding:200px;">
		        </div>        
	        </div> -->
 			<div style="margin-bottom: 10px;" >
	 			<a href="#" id="goEventPage2" >
	 				<img src="<c:url value='/images/banner/eventbanner.jpg'/>" style="width: 100%; max-height: 125px"  alt="img"  >
	 			</a>		        
<!-- 		        <div class="container no-bottom" style="padding:200px;">
		        </div> -->        
	        </div>
	        
	        <div style="margin-bottom: 10px;">
		        <div class="container no-bottom" style="color:yellow; background-color:#666; ">
		            <p style="color: white;margin-top: 3px; font-size: medium; vertical-align: middle; margin-bottom: 5px;"  align="center"   >
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
    
</div>
<script type="text/javascript">
$(document).ready(function(){    
    var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
            
    var chan_val = year + '-' + mon + '-' + day + "영수증 확인";
    $("#receiptArea").text(chan_val);
	$("#goUseList").bind("click",function(e){
    	    
    });
	
	$("#goUseList").click(function(){
		location.href='/receipt/theReal/E01.do';
	});
	
	$("#goEventPage").click(function(){
		location.href='/receipt/theReal/notice.do';
	});
	
	$("#goEventPage2").click(function(){
		location.href='/receipt/theReal/goEventPage.do';
	});
	
	$("#goReceiptList").click(function(){
		location.href='/receipt/theReal/F01.do?year='+year+'&mon='+mon+'&lsatDay='+lastDay();
	});
	
/* 	$("#logOutBtn").click(function(){
		alert("1");
		location.href='/receipt/theReal/logout.do';
	}); */
	
});

//현재달의 마지막날(일자) 구하기
function lastDay(){
   var d,d2, s = "";           
   d = new Date();             
   d2 = new Date(d.getYear(),d.getMonth()+1,"");                      
   //s += d2.getYear()+ kind;                       
   //s += (d2.getMonth()+1) + kind;  //여기에 꼭 +1이 있어야됨 생략하고 위에다가 +1하면 1월이 0으로 나옴
   s += d2.getDate();
   return(s);                             
}


</script>    
</body>