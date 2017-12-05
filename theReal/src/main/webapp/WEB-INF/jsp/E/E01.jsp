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
        <div class="container no-bottom" id="totalArea">
<%--             <h3>최근 영수증 내역 확인</h3>
					<c:choose>
						<c:when test="${fn:length(list.resultMap) > 0}">
				            <b>총 ${list.resultMap[0].TOTAL_CNT} 건 /  ${list.resultMap[0].TOTAL_AM} 원</b>
						</c:when>
					</c:choose>       --%>      
        </div>

        <div class="decoration"></div>
        <div class="one-third-responsive">
					<c:choose>
						<c:when test="${fn:length(list.resultMap) > 0}">
							<c:forEach var="row" items="${list.resultMap}" varStatus="status">
<%-- 					            <a href="#" class="user-list-item" id="goUseDtl${row.SEQ }"> --%>
					            <a href="#" class="user-list-item" id="${status.count}">
									<c:set var="divCd1" value="A1"/>
									<c:set var="divCd2" value="A2"/>
									<c:set var="divCd3" value="A3"/>
									<c:set var="divCd4" value="A4"/>
									<c:set var="divCd5" value="A5"/>
									<c:set var="divCd6" value="A6"/>
									<c:set var="divCd7" value="A7"/>
									<c:set var="divCd8" value="A8"/>
									<c:set var="divCd9" value="A9"/>
					                <c:choose>
					                	<c:when test="${row.PAY_DIV_CD == divCd1}">
							                <img src="<c:url value='/images/icon/beer_icon.png'/>" alt="img">
					                	</c:when>
					                	<c:when test="${row.PAY_DIV_CD == divCd2}">
							                <img src="<c:url value='/images/icon/chicken_icon.png'/>" alt="img">
					                	</c:when>
					                	<c:when test="${row.PAY_DIV_CD == divCd3}">
							                <img src="<c:url value='/images/icon/coffe_icon.png'/>" alt="img">
					                	</c:when>
					                	<c:when test="${row.PAY_DIV_CD == divCd4}">
							                <img src="<c:url value='/images/icon/drug_icon.png'/>" alt="img">
					                	</c:when>
					                	<c:when test="${row.PAY_DIV_CD == divCd5}">
							                <img src="<c:url value='/images/icon/game_icon.png'/>" alt="img">
					                	</c:when>
					                	<c:when test="${row.PAY_DIV_CD == divCd6}">
							                <img src="<c:url value='/images/icon/hospital_icon.png'/>" alt="img">
					                	</c:when>
					                	<c:when test="${row.PAY_DIV_CD == divCd7}">
							                <img src="<c:url value='/images/icon/movie_icon.png'/>" alt="img">
					                	</c:when>
					                	<c:when test="${row.PAY_DIV_CD == divCd8}">
							                <img src="<c:url value='/images/icon/present_icon.png'/>" alt="img">
					                	</c:when>
					                	<c:when test="${row.PAY_DIV_CD == divCd9}">
							                <img src="<c:url value='/images/icon/shop_icon.png'/>" alt="img">
					                	</c:when>
										<c:otherwise>
											<img src="<c:url value='/images/icon/shop_icon.png'/>" alt="img">
										</c:otherwise>
					                </c:choose>
					                <strong>${row.USE_DATE }<br></strong>
					                <strong>${row.PAY_DIV }</strong>
					                <input type="hidden"  id="useDate${status.count}" value="${row.USE_DATE }"/>
					                <input type="hidden"  id="payDiv${status.count}" value="${row.PAY_DIV }"/>
					                <input type="hidden"  id="etpNm${status.count}" value="${row.ETP_NM }"/>
					                <input type="hidden"  id="payAm${status.count}" value="${row.PAY_AM }"/>
					                <input type="hidden"  id="eMail${status.count}" value="${row.EMAIL }"/>
					                <input type="hidden"  id="payGoods${status.count}" value="${row.PAY_GOODS }"/>
					                <i><b>${row.ETP_NM } : ${row.PAY_AM }원</b></i>
					            </a>
					            <div class="decoration"></div>									
							</c:forEach>	
						</c:when>
						<c:otherwise>
							<br/>조회된 결과가 없습니다.
						</c:otherwise>
					</c:choose>	        
        </div>

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
	var str = "<h3>최근 영수증 내역 확인</h3>";
	str += "<b>총"+$('.user-list-item').length+" 건 /  "+'${list.resultMap[0].TOTAL_AM}'+" 원</b>";
	
    $('#totalArea').append(str);
    
	$(".user-list-item").click(function(){
		var seq = $(this).attr('id');
   	 	var useDate = $('#useDate'+seq).val();
   	 	var payDiv = $('#payDiv'+seq).val();
   	 	var etpNm = $('#etpNm'+seq).val();
   	 	var payAm = $('#payAm'+seq).val();
   	 	var eMail = $('#eMail'+seq).val();
        var result = confirm("사용일자:"+useDate+"\n사용카드:"+payDiv+"\n업체명:"+etpNm+"\n사용금액:"+payAm+"\n이메일:"+eMail+"\n\n이메일을 전송하시겠습니까?");
        if(result) {
			callAjax(seq);
        } else {
			return;
        }		
	})
/* 	$("#goUseList").bind("click",function(e){
    }); */
});

function callAjax(seq){
    $.ajax({
     type: "post",
     url : "/receipt/theReal/sendMail.do",
     data: {
        	 useDate : $('#useDate'+seq).val(),
        	 payDiv : $('#payDiv'+seq).val(),
        	 etpNm : $('#etpNm'+seq).val(),
        	 payAm : $('#payAm'+seq).val(),
        	 eMail : $('#eMail'+seq).val(),
        	 payGoods : $('#payGoods'+seq).val(),
     },
     success: whenSuccess,
     error: whenError
 	});
}

function whenSuccess(resdata){
	alert("이메일전송이 되었습니다.");
   
}

function whenError(){
   alert("Error");
}


</script>    
</body>