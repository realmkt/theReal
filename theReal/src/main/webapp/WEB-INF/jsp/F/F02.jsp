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
    <div class="header">
        <h1 align="center">전자영수증</h1>
       <!--  <em>더리얼마케팅</em> -->
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
		 <div class="tabs" align="center">
		 	<div>
			 <a class="dateselect-but" href="#" ><img src="<c:url value='/images/btn/btn_left.png'/>" alt="leftimg" id="prevMonth" style="height: 30px"></a>
			 <a class="dateselect-but" href="#"><strong id="moveDate" ></strong></a>
			 <a class="dateselect-but" href="#"><img src="<c:url value='/images/btn/btn_right.png'/>" alt="rightimg" id="nextMonth" style="height: 30px"></a>
			 </div>     
		 </div>  
         <div class="tabs">
             <a href="#" class="tab-but tab-but-1 tab-active" id="btnTab1">리스트</a>
             <a href="#" class="tab-but tab-but-2" id="btnTab2">달력</a>
             <a href="#" class="tab-but tab-but-3" id="btnTab3">분류</a>
         </div>		    
        <div class="container no-bottom"  id = "totCntArea">
            <h3>최근 영수증 내역 확인</h3>
					<c:choose>
						<c:when test="${fn:length(list.resultMap) > 0}">
				            <b>총 ${list.resultMap[0].TOTAL_CNT} 건 /  ${list.resultMap[0].TOTAL_AM} 원</b>
						</c:when>
					</c:choose>            
        </div>

        <div class="decoration"></div>
        <div class="one-third-responsive" id="listTab1">
					<c:choose>
						<c:when test="${fn:length(list.resultMap) > 0}">
							<c:forEach var="row" items="${list.resultMap}" varStatus="status">
<%-- 					            <a href="#" class="user-list-item" id="goUseDtl${row.SEQ }"> --%>
					            <a href="#" class="user-list-item" id="${status.count}">
					                <img src="<c:url value='/images/pictures/4s.jpg'/>" alt="img">
					                <strong>${row.USE_DATE }<br></strong>
					                <strong>${row.PAY_DIV }</strong>
					                <input type="hidden"  id="useDate${status.count}" value="${row.USE_DATE }"/>
					                <input type="hidden"  id="payDiv${status.count}" value="${row.PAY_DIV }"/>
					                <input type="hidden"  id="etpNm${status.count}" value="${row.ETP_NM }"/>
					                <input type="hidden"  id="payAm${status.count}" value="${row.PAY_AM }"/>
					                <input type="hidden"  id="eMail${status.count}" value="${row.EMAIL }"/>
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
        <div id="addDivBtn">
			<a class="button center-button button-green full-bottom detected-button" href="#" id="addBtn" >더보기</a>
        </div>
    </div>   
    
    <!-- Footer -->
    
    <div class="footer">
        <p class="center-text">Copyright (c) 2016 The REAL All rights reserved</p>
    </div>    
    <a href="#" class="header-up"><i class="fa fa-caret-up"></i>맨위로</a>
    
    
</div>
<script type="text/javascript">
    var selectDate = new Date();
    var month = selectDate.getMonth()-1;
    var year= selectDate.getFullYear();
    var orgMon = selectDate.getMonth();
    var mon = (selectDate.getMonth()+1)>	9 ? ''+(selectDate.getMonth()+1) : '0'+(selectDate.getMonth()+1);
    var day = selectDate.getDate()>9 ? ''+selectDate.getDate() : '0'+selectDate.getDate();
    var ajaxStartDate = year+mon;
    
$(document).ready(function(){    
    var chan_val = year + '-' + mon + '-' + day + "영수증 확인";
    $("#receiptArea").text(chan_val);
    $("#moveDate").text(year + '-' + mon);
    $("#btnTab1").click(function(){
    	if($("#listTab1").css("display") == "none"){
    		$("#listTab1").show();
    	} else {
    		$("#listTab1").hide();
    	}    	
    	
    })
    $("#btnTab2").click(function(){
    	if($("#listTab1").css("display") == "none"){
    		$("#listTab1").show();
    	} else {
    		$("#listTab1").hide();
    	}    	
    })
    $("#btnTab3").click(function(){
    	if($("#listTab1").css("display") == "none"){
    		$("#listTab1").show();
    	} else {
    		$("#listTab1").hide();
    	}    	
    })
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
	
	$("#prevMonth").click(function(){
		selectDate = new Date(selectDate.getFullYear(), (month-1), selectDate.getDate());
		
		var startDate = new Date();
		var endDate   = new Date();
		
		startDate.setFullYear(selectDate.getFullYear());
		endDate.setFullYear(selectDate.getFullYear());
		startDate.setMonth((selectDate.getMonth()+1));
		endDate.setMonth((selectDate.getMonth()+2));
		month = selectDate.getMonth();
		var startPrevYear = startDate.getFullYear();
		var startPrevMonth = startDate.getMonth();
		$("#moveDate").text(startPrevYear+"-"+((startPrevMonth+1)>9 ? ''+(startPrevMonth+1) : '0'+(startPrevMonth+1) ));
		var endPrevYear = endDate.getFullYear();
		var endPrevMonth = endDate.getMonth();
		ajaxStartDate = (startPrevYear)+((startPrevMonth+1)>9 ? ''+(startPrevMonth+1) : '0'+(startPrevMonth+1) );
		searchMonthList(ajaxStartDate+"01",ajaxStartDate+lastDay());
	})
	
	$("#nextMonth").click(function(){
		selectDate = new Date(selectDate.getFullYear(), (month+1), selectDate.getDate());
		var startDate = new Date();
		var endDate   = new Date();
		
		startDate.setFullYear(selectDate.getFullYear());
		endDate.setFullYear(selectDate.getFullYear());
		startDate.setMonth((selectDate.getMonth()+1));
		endDate.setMonth((selectDate.getMonth()+2));
		var startNextYear = startDate.getFullYear();
		var startNextMonth = startDate.getMonth();
/*  		alert("startNextYear:"+startNextYear);
		alert("startNextMonth:"+startNextMonth);
		alert("year:"+year);
		alert("orgMon:"+orgMon); */
		var endNextYear = endDate.getFullYear();
		var endNextMonth = endDate.getMonth();
/* 		var ajaxStartDate = (startNextYear)+((startNextMonth+1)>9 ? ''+(startNextMonth+1) : '0'+(startNextMonth+1) )+'01';
		var ajaxEndDate = (endNextYear)+((endNextMonth+1)>9 ? ''+(endNextMonth+1) : '0'+(endNextMonth+1) )+'01';
 */
 		ajaxStartDate = (startNextYear)+((startNextMonth+1)>9 ? ''+(startNextMonth+1) : '0'+(startNextMonth+1) );
 alert("ajaxStartDate:"+ajaxStartDate);
 alert("(year)+(orgMon):"+(year)+(orgMon));
 		if(ajaxStartDate > (year)+(orgMon)){
			alert("현재 달보다 이후 달입니다.");
			return;
		} 
 		month = selectDate.getMonth();
		$("#moveDate").text(startNextYear+"-"+((startNextMonth+1)>9 ? ''+(startNextMonth+1) : '0'+(startNextMonth+1) ));
		searchMonthList(ajaxStartDate+"01",ajaxStartDate+lastDay());
	})
 	$("#addBtn").bind("click",function(e){
 		addCnt++;
 		addList();
 	});
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
     },
     success: whenSuccess,
     error: whenError
 	});
}

var addCnt = 0;
function addList(){
    $.ajax({
     type: "post",
     url : "/receipt/theReal/selectAddListF01.do",
     data: {
        	 addStartCnt : addCnt*10,
        	 addEndCnt : addCnt*20,
         	startDate : ajaxStartDate+"01",
        	endDate : ajaxStartDate+lastDay(),
        	eMail : '${list.resultMap[0].EMAIL}',
     },
     success: whenSuccessAddlist,
     error: whenError
 	});
}

function searchMonthList(startDate,endDate){
    $.ajax({
        type: "post",
        url : "/receipt/theReal/MonthListF01.do",
        /* dataType : "html", */
        dataType:"json",
        data: {
        	startDate : startDate,
        	endDate : endDate,
        	eMail : '${list.resultMap[0].EMAIL}',
        },
        success: whenSuccess2,
        error: whenError
    	});	
}

function whenSuccess(resdata){
	alert("이메일전송이 되었습니다.");
}

function whenSuccessAddlist(resdata){
	var resultData = resdata.resultMap;
	var addMinCnt = addCnt*10+1;
	var addMaxCnt = resultData.length+addCnt*10+1;
	var str = "";
	if(resultData.length>0){
		if(resultData.length<9) $("#addDivBtn").hide();
		for(var i=0; i<resultData.length; i++){
			str += "<a href='#' class='user-list-item' id="+parseInt(addMinCnt+i)+">";
			str += "<img src='<c:url value='/images/pictures/4s.jpg'/>' alt='img'>";
			str += "<strong>"+resultData[i].USE_DATE+"<br></strong>";
			str += "<strong>"+resultData[i].PAY_DIV+"</strong>";
			str += "<input type='hidden'  id='useDate"+parseInt(addMaxCnt+1)+ "' value='"+resultData[i].USE_DATE+"'/>";
			str += "<input type='hidden'  id='payDiv"+parseInt(addMaxCnt+1)+ "' value='"+resultData[i].PAY_DIV+"'/>";
			str += "<input type='hidden'  id='etpNm"+parseInt(addMaxCnt+1)+ "' value='"+resultData[i].ETP_NM+"'/>";
			str += "<input type='hidden'  id='payAm"+parseInt(addMaxCnt+1)+ "' value='"+resultData[i].PAY_AM+"'/>";
			str += "<input type='hidden'  id='eMail"+parseInt(addMaxCnt+1)+ "' value='"+resultData[i].EMAIL+"'/>";
			str += "<i><b>"+resultData[i].ETP_NM+" : "+resultData[i].PAY_AM+"원</b></i>";
			str += "</a>";
			str += "<div class='decoration'></div>";
		}
	}
	$(".one-third-responsive").append(str);
}

function whenSuccess2(resdata){
	console.log(resdata.resultMap);
	//alert(resdata.resultMap.length);
	$(".one-third-responsive").empty();
	$("#totCntArea").empty();
	var str = "";
	var totStr = "<h3>최근 영수증 내역 확인</h3>";
	var resultData = resdata.resultMap;
	if(resultData.length>0){
		
		totStr += "<b>총"+ resultData[0].TOTAL_CNT+" 건 /  "+resultData[0].TOTAL_AM+" 원</b>";
	            
		for(var i=0; i<resultData.length; i++){
			str += "<a href='#' class='user-list-item' id="+parseInt(i+1)+">";
			str += "<img src='<c:url value='/images/pictures/4s.jpg'/>' alt='img'>";
			str += "<strong>"+resultData[i].USE_DATE+"<br></strong>";
			str += "<strong>"+resultData[i].PAY_DIV+"</strong>";
			str += "<input type='hidden'  id='useDate"+parseInt(i+1)+ "' value='"+resultData[i].USE_DATE+"'/>";
			str += "<input type='hidden'  id='payDiv"+parseInt(i+1)+ "' value='"+resultData[i].PAY_DIV+"'/>";
			str += "<input type='hidden'  id='etpNm"+parseInt(i+1)+ "' value='"+resultData[i].ETP_NM+"'/>";
			str += "<input type='hidden'  id='payAm"+parseInt(i+1)+ "' value='"+resultData[i].PAY_AM+"'/>";
			str += "<input type='hidden'  id='eMail"+parseInt(i+1)+ "' value='"+resultData[i].EMAIL+"'/>";
			str += "<i><b>"+resultData[i].ETP_NM+" : "+resultData[i].PAY_AM+"원</b></i>";
			//str += "<div class='decoration'></div>";
			str += "</a>";
		}
	}else{
			str += "<br/><h3>조회된 결과가 없습니다.</h3>";
			$("#addDivBtn").hide();
	}
	$(".one-third-responsive").append(str);
	$("#totCntArea").append(totStr);
}

function whenError(request,status,error){
	alert("ERROR");
}

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