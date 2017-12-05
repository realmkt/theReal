<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
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
<style type="text/css">

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
        <div class="container no-bottom">
            <h3>공지사항</h3>
					<c:choose>
						<c:when test="${fn:length(list) > 0}">
						</c:when>
					</c:choose>            
        </div>

        <div class="decoration"></div>
        
        <div class="blog-posts">
            <div class="blog-post">
                <h3 class="blog-post-title"></h3>
                <p class="blog-post-text">
                </p>
                <p class="blog-post-date"><i class="fa fa-calendar"></i></p><br/>
                <!-- <p class="blog-post-more"><a href="#">Read More<i class="fa fa-angle-right"></i></a></p> -->
            </div>
            <div class="decoration"></div>
	        <div id="addDivBtn">
				 <a class="button button-green full-bottom detected-button" href="javascript:void(0)" id="btnMod" >수정</a>
				 <a class="button button-green full-bottom detected-button" href="javascript:void(0)" id="btnDel" >삭제</a>
				 <a class="button button-green full-bottom detected-button" href="javascript:void(0)" id="btnList" >목록</a>
	        </div>
        </div>             
        
        <div class="one-two-responsive">
					<c:choose>
						<c:when test="${fn:length(list.resultMap) > 0}">
							<c:forEach var="row" items="${list.resultMap}" varStatus="status">
<%-- 					            <a href="#" class="user-list-item" id="goUseDtl${row.SEQ }"> --%>
					            <a href="#" class="user-list-item" id="${row.SEQ }">
					                <strong class="user-list-strong1">${row.BOARD_TITLE }<br></strong>
					                <strong class="user-list-strong2">${row.SEQ }</strong>
					                <input type="hidden"  id="creadtm${row.SEQ}" value="${row.CREA_DTM }"/>
					                <input type="hidden"  id="title${row.SEQ}" value="${row.BOARD_TITLE }"/>
					                <input type="hidden"  id="contents${row.SEQ}" value= ' ${row.BOARD_CONTENTS }'  />
					                <%-- <i><img src="<c:url value='/images/btn/btn_right.png'/>" alt="rightimg" id="nextMonth" style="height: 30px"></i> --%>
					            </a>
					                
					            <div class="decoration"></div>									
							</c:forEach>	
						</c:when>
						<c:otherwise>
							<br/>조회된 결과가 없습니다.
						</c:otherwise>
					</c:choose>	     
					   	        <div id="addDivBtn">
				<!-- <a class="button center-button button-green full-bottom detected-button" href="#" id="addBtn" >목록</a> -->
				<a class="button button-green full-bottom detected-button" href="javascript:void(0)" id="boardWrite" >글쓰기</a>
	        </div>
        </div>
        
<div class="pageNavi">
<a href="javascript:void(0);"  id="btnPrev">
<%-- <img src="<c:url value='/images/btn/btn_prev.png'/>" alt="처음" id="btn_prev"> --%>
<%-- <img src="<c:url value='/images/btn/ico_pageNavi_left.png'/>" alt="처음" id="btn_prev"> --%>
</a>
<span id="pageNo">
</span>
<!-- <strong>1</strong>/ 8 -->
<a href="javascript:void(0);" id="btnNext">
<%-- <img src="<c:url value='/images/btn/btn_next.png'/>" alt="다음" id="btn_next"> --%>
<%-- <img src="<c:url value='/images/btn/ico_pageNavi_right.png'/>" alt="다음" id="btn_next"> --%>
</a>
</div>        
        
</div>
    </div>   
    <script type="text/javascript">
    var pageCnt = 0;
    var str = "";
    var totalCnt ="${list.resultMap[0].TOTAL_COUNT}";
    totalCnt = (Math.ceil((totalCnt/10)));
$(document).ready(function(){
	
	$(".blog-posts").css("display","none");
	
	str += "<strong>"+(pageCnt+1)+"</strong>/"+totalCnt+"</span>";
	$("#pageNo").append(str);
	
	$("#btnPrev").click(function(){
		if(pageCnt < 1){
			alert("첫번째 페이지 입니다.");
			return;
		}
		pageCnt --;
		movePaging(pageCnt);
	})
	
	$("#btnNext").click(function(){
		if((pageCnt+1) == totalCnt){
			alert("마지막 페이지 입니다.");
			return;			
		}
		pageCnt ++;
		movePaging(pageCnt);
	})
	
	$("#boardWrite").click(function(){
		location.href='/theReal/receipt/Admin/boardMngWrite.do';
	})
	
	$(document).on('click','.user-list-item',function(){
		
		$(".blog-posts").css("display","block");
		$(".one-two-responsive").css("display","none");
		$(".pageNavi").css("display","none");
 		moveToId = $(this).attr("id");
 		var hid1 = $("#creadtm"+moveToId).val();
 		var hid2 = $("#title"+moveToId).val();
 		var hid4 = $("#contents"+moveToId).val();
 		var hid3 = '기존 DB에 저장된 내용을 에디터에 적용할 문구<p>1111<img src="/receipt/resources/editor/multiupload/20160309144715bc3057ed-fd3f-4a36-a8d8-30903e6e3862.png" title="git11.png">&nbsp;</p>'
 		console.log(hid3);
 		console.log(hid4);
		$(".blog-post-date").text(hid1);
		$(".blog-post-title").text(hid2);
		$(".blog-post-text").html(hid4);
		 		
		}
	);
	
	$(document).on('click','#addBtn',function(){
		$(".blog-posts").css("display","none");
		$(".one-two-responsive").css("display","block");
		$(".pageNavi").css("display","block");
		}
	);
	
	$(document).on('click','#btnMod',function(){
		location.href='/theReal/receipt/Admin/boardMngWrite.do?IDX='+moveToId;
	});
	
	$(document).on('click','#btnDel',function(){
		
		location.href='/theReal/receipt/Admin/deleteAdminBoard.do?IDX='+moveToId;
		//location.href='/receipt/theReal/Admin/boardMng.do';
/*  	    $.ajax({
	        type: "post",
	        url : "/receipt/sample/deleteAdminBoard.do",
	        dataType:"json",
	        data: {
	        	IDX : moveToId,
	        },
	        success: successDel,
	        error: whenError
	    	});	  */
	});
	
	$(document).on('click','#btnList',function(){
		location.href='/receipt/theReal/Admin/boardMng.do';
	});
	
/* 	$(".user-list-item").click(function(){
		alert("test");
		var test2 = $(this).attr("id");
		var test3 = $("#creadtm"+test2).val();
		alert("test3:"+test3);
	}) */
});
var moveToId = '';
function movePaging(cnt){
	
	var startCnt = ""; 
	var endCnt = "";
	if(cnt == 0){
		startCnt = 1; 
		endCnt = 10;
	}else{
		startCnt = cnt*10; 
		endCnt = (startCnt+10);
	}
    $.ajax({
        type: "post",
        url : "/receipt/theReal/noticeMove.do",
        dataType:"json",
        data: {
        	startNo : startCnt,
        	endNo : endCnt,
        },
        success: successPaging,
        error: whenError
    	});	
};

function successPaging(resdata){
	
	var resultData = resdata.resultMap;
	
	str = "";
	var str2 ="";
	
	console.log("pageCnt:"+pageCnt);
	console.log("totalCnt:"+totalCnt);
		
	if(resultData.length>0){
		
		$("#pageNo").empty();
		$(".one-two-responsive").empty();
		
		str += "<strong>"+(pageCnt+1)+"</strong>/"+totalCnt+"</span>";
		
 		for(var i=0; i<resultData.length; i++){
 			
			str2 += "<a href='javascript:void(0)' class='user-list-item' id="+resultData[i].SEQ+">";
			str2 += "<strong class='user-list-strong1'>"+resultData[i].BOARD_TITLE+"<br></strong>";
			str2+= "<strong class='user-list-strong2'>"+resultData[i].SEQ+"</strong>";
			str2 += "<input type='hidden'  id='creadtm"+resultData[i].SEQ+ "' value='"+resultData[i].CREA_DTM+"'/>";
			str2 += "<input type='hidden'  id='title"+resultData[i].SEQ+ "' value='"+resultData[i].BOARD_TITLE+"'/>";
			str2 += "<input type='hidden'  id='contents"+resultData[i].SEQ+ "' value='"+resultData[i].BOARD_CONTENTS+"'/>";
			str2 += "</a>";
			str2+= "<div class='decoration'></div>"; 			
			
		}
 		
		$(".one-two-responsive").append(str2);
		$("#pageNo").append(str);
		
	}
	
}

function whenError(request,status,error){
	alert("ERROR");
}

function successDel(resdata){
}
    </script>
</body>
