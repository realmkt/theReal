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

        <!-- <div class="decoration"></div> -->
        <div class="one-third-responsive" id="listTab1">
					<c:choose>
						<c:when test="${fn:length(list.resultMap) > 0}">
							<c:forEach var="row" items="${list.resultMap}" varStatus="status">
<%-- 					            <a href="#" class="user-list-item" id="goUseDtl${row.SEQ }"> --%>
					            	<a href="#" class="user-list-item" id="${status.count}"> 
					                <%-- <img src="<c:url value='/images/pictures/4s.jpg'/>" alt="img"> --%>
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
					                <i><b>${row.ETP_NM } : ${row.PAY_AM }원</b></i>
					            </a>
					            <div class="decoration"></div>									
							</c:forEach>	
						</c:when>
						<c:otherwise>
							<br/>조회된 결과가 없습니다.
						</c:otherwise>
					</c:choose>	        
	        <div id="addDivBtn">
				<a class="button center-button button-green full-bottom detected-button" href="#" id="addBtn" >더보기</a>
	        </div>
        </div>
        
<div class="one-half-responsive" id="listTab2" >
<!--             <h3>Calendar Design</h3>
            <p>
                A calendar template, you can use to show schedules, events and whatever else you want! 
                This is a calendar template. You can create months by simply adding days, years and more.
            </p> -->
            <!-- <div class="decoration"></div> -->
            <!-- <h3 class="right-text half-bottom">January, 2025</h3> -->
            <div class="calendar-titles">
                <a class="light-titles" href="#">일</a>
                <a href="#">월</a>
                <a href="#">화</a>
                <a href="#">수</a>
                <a href="#">목</a>
                <a href="#">금</a>
                <a class="light-titles" href="#">토</a>
            </div>
            <div class="decoration half-bottom"></div>
            <div class="calendar-days">
            </div>
        </div>
        <div class="one-third-responsive" id="listTab2L">
        </div>
        <!-- <div class="decoration"></div> -->         
        
        
      <div class="one-third-responsive" id="listTab3">
      
<!-- 		<div class="select_box">
			<label for="period_fix">선택</label>
			<select name="period_fix" id="period_fix1" >
					<option value="A1">A1</option>
					<option value="A2">A2</option>
					<option value="A3">A3</option>
					<option value="A4">A4</option>
					<option value="A5">A5</option>
					<option value="A6">A6</option>
					<option value="A7">A7</option>
			</select>
		</div>      --> 
		    <div id="addDivBtn">
					<a class="button center-button button-green full-bottom detected-button" href="#" id="addBtn" >더보기</a>
			</div>
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
var select_name ="선택"; 
var totalCnt = '${list.resultMap[0].TOTAL_CNT}';
var totalAm = '${list.resultMap[0].TOTAL_AM}';

$(document).ready(function(){    
	$("#listTab2").hide();
	$("#listTab3").hide();
    var chan_val = year + '-' + mon + '-' + day + "영수증 확인";
    $("#receiptArea").text(chan_val);
    $("#moveDate").text(year + '-' + mon);
    if($(".listItem").length == 0){
		$("#addDivBtn").hide();   	
    };
    
/* 	$(document).on('change','select',function(){
        select_name = $(this).children("option:selected").text();
        divSearch(ajaxStartDate+"01",ajaxStartDate+lastDay(selectDate),$(this).children("option:selected").val(),select_name);
	}
	); */
	
   
    $("#btnTab1").click(function(){
    	if($("#listTab1").css("display") == "none"){
    		$("#listTab1").css("display","block");
    		$("#listTab2").css("display","none");
    		$("#listTab2L").css("display","none");
    		$("#listTab3").css("display","none");
    		$("#totCntArea").empty();
    		var totStr = "<h3>최근 영수증 내역 확인</h3>";
			totStr += "<b>총"+ totalCnt+" 건 /  "+totalAm+" 원</b>";
			$("#totCntArea").append(totStr);
    	} else {
    		$("#listTab1").css("display","block");
    		$("#listTab2").css("display","none");
    		$("#listTab2L").css("display","none");
    		$("#listTab3").css("display","none");
    	}    	
    	
    })
    $("#btnTab2").click(function(){
    	if($("#listTab2").css("display") == "none"){
    		$("#listTab1").css("display","none");
    		$("#listTab2").css("display","block");
    		$("#listTab2L").css("display","none");
    		$("#listTab3").css("display","none");
    		selectDateCntListF01();
    		$("#totCntArea").empty();
    		var totStr = "<h3>최근 영수증 내역 확인</h3>";
			totStr += "<b>총"+ totalCnt+" 건 /  "+totalAm+" 원</b>";
			$("#totCntArea").append(totStr);
    	} else {
    		$("#listTab1").css("display","none");
    		$("#listTab2").css("display","block");
    		$("#listTab2L").css("display","none");
    		$("#listTab3").css("display","none");
    	}    	
    })
    
    $("#btnTab3").bind("click",function(e){
    //$("#btnTab3").click(function(){
    //$(document).on('click','#btnTab3',function(){
    	if($("#listTab3").css("display") == "none"){
    		$("#listTab1").css("display","none");
    		$("#listTab2").css("display","none");
    		$("#listTab2L").css("display","none");
    		$("#listTab3").css("display","block");
    		divSearch(ajaxStartDate+"01",ajaxStartDate+lastDay(selectDate),"A1");
    	} else {
    		$("#listTab1").css("display","none");
    		$("#listTab2").css("display","none");
    		$("#listTab2L").css("display","none");
    		$("#listTab3").css("display","block");
    	}    	
    })

	$(".user-list-item").click(function(){
		console.log(this);
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
/* 		if($('#btnTab1').hasClass('tab-active')){
		}else if($('#btnTab2').hasClass('tab-active')){
		}else{
		} */
		//calendar();
		searchMonthList(ajaxStartDate+"01",ajaxStartDate+lastDay(startDate));
		selectDateCntListF01();	
		divSearch(ajaxStartDate+"01",ajaxStartDate+lastDay(startDate),"A1");
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
		var endNextYear = endDate.getFullYear();
		var endNextMonth = endDate.getMonth();
 		if((startNextYear)+((startNextMonth+1)>9 ? ''+(startNextMonth+1) : '0'+(startNextMonth+1) ) > (year)+(mon)){
			alert("현재 달보다 이후 달입니다.");
	 		month = (selectDate.getMonth()-1);
			return;
		} 
 		ajaxStartDate = (startNextYear)+((startNextMonth+1)>9 ? ''+(startNextMonth+1) : '0'+(startNextMonth+1) );
 		month = selectDate.getMonth();
		$("#moveDate").text(startNextYear+"-"+((startNextMonth+1)>9 ? ''+(startNextMonth+1) : '0'+(startNextMonth+1) ));
/* 		if($('#btnTab1').hasClass('tab-active')){
			alert("btn1");
		}else if($('#btnTab2').hasClass('tab-active')){
			alert("btn2");
		}else{
			alert("btn3");
		}		 */
		//calendar();
		searchMonthList(ajaxStartDate+"01",ajaxStartDate+lastDay(startDate));
		selectDateCntListF01();
		divSearch(ajaxStartDate+"01",ajaxStartDate+lastDay(startDate),"A1");
	})
	
 	$("#addBtn").bind("click",function(e){
 		addCnt++;
 		addList(selectDate);
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
function addList(selectDate){
    $.ajax({
     type: "post",
     url : "/receipt/theReal/selectAddListF01.do",
     data: {
        	 addStartCnt : addCnt*10,
        	 addEndCnt : addCnt*20,
         	startDate : ajaxStartDate+"01",
        	endDate : ajaxStartDate+lastDay(selectDate),
        	eMail : '${eMail}',
     },
     success: whenSuccessAddlist,
     error: whenError
 	});
}

function divSearch(startDate,endDate,divCd){
	console.log("startDate:"+startDate);
	console.log("endDate:"+endDate);
	console.log("divCd:"+divCd);
    $.ajax({
        type: "post",
        url : "/receipt/theReal/divSearch.do",
        /* dataType : "html", */
        dataType:"json",
        data: {
        	startDate : startDate,
        	endDate : endDate,
        	eMail : '${eMail}',
        	divCd : divCd,
        },
        success: whenSuccess3,
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
        	eMail : '${eMail}',
        },
        success: whenSuccess2,
        error: whenError
    	});	
}

function selectDateCntListF01(){
    $.ajax({
        type: "post",
        url : "/receipt/theReal/selectDateCntListF01.do",
        /* dataType : "html", */
        dataType:"json",
        data: {
        	startDate : ajaxStartDate+"01",
        	endDate : ajaxStartDate+"31",
        	eMail : '${eMail}',
        },
        success: calendar,
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
		
		for(var i=0; i<resultData.length; i++){
			str += "<a href='#' class='user-list-item' id="+parseInt(addMinCnt+i)+">";
			
			if(resultData[i].PAY_DIV_CD == "A1"){
				str += "<img src='<c:url value='/images/icon/beer_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A2"){
				str += "<img src='<c:url value='/images/icon/chicken_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A3"){
				str += "<img src='<c:url value='/images/icon/coffe_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A4"){
				str += "<img src='<c:url value='/images/icon/drug_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A5"){
				str += "<img src='<c:url value='/images/icon/game_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A6"){
				str += "<img src='<c:url value='/images/icon/hospital_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A7"){
				str += "<img src='<c:url value='/images/icon/movie_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A8"){
				str += "<img src='<c:url value='/images/icon/present_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A9"){
				str += "<img src='<c:url value='/images/icon/shop_icon.png'/>' alt='img'>";
			}else{
				str += "<img src='<c:url value='/images/icon/shop_icon.png'/>' alt='img'>";
			}
			
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
		if(resultData.length<9){
			$("#addDivBtn").hide();
		}else{
			str += "<div id='addDivBtn'>";
			str += "<a class='button center-button button-green full-bottom detected-button' href='#' id='addBtn' >더보기</a>";
			str += "</div>";					
		}
	}
	$("#listTab1").append(str);
}

function whenSuccess2(resdata){
	//alert(resdata.resultMap.length);
	$("#listTab1").empty();

	var str = "";
	$("#totCntArea").empty();
	var totStr = "<h3>최근 영수증 내역 확인</h3>";
	var resultData = resdata.resultMap;
	if(resultData.length>0){
		totStr += "<b>총"+ resultData[0].TOTAL_CNT+" 건 /  "+resultData[0].TOTAL_AM+" 원</b>";
	    totalCnt = resultData[0].TOTAL_CNT;        
	    totalAm = resultData[0].TOTAL_AM;        
		for(var i=0; i<resultData.length; i++){
			str += "<a href='#' class='user-list-item' id="+parseInt(i+1)+">";
			if(resultData[i].PAY_DIV_CD == "A1"){
				str += "<img src='<c:url value='/images/icon/beer_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A2"){
				str += "<img src='<c:url value='/images/icon/chicken_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A3"){
				str += "<img src='<c:url value='/images/icon/coffe_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A4"){
				str += "<img src='<c:url value='/images/icon/drug_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A5"){
				str += "<img src='<c:url value='/images/icon/game_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A6"){
				str += "<img src='<c:url value='/images/icon/hospital_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A7"){
				str += "<img src='<c:url value='/images/icon/movie_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A8"){
				str += "<img src='<c:url value='/images/icon/present_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A9"){
				str += "<img src='<c:url value='/images/icon/shop_icon.png'/>' alt='img'>";
			}else{
				str += "<img src='<c:url value='/images/icon/shop_icon.png'/>' alt='img'>";
			}
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
		if(resultData.length<9){
			$("#addDivBtn").hide();
		}else{
			str += "<div id='addDivBtn'>";
			str += "<a class='button center-button button-green full-bottom detected-button' href='#' id='addBtn' >더보기</a>";
			str += "</div>";					
		}
	}else{
			totStr += "<b>총"+ resultData[0].TOTAL_CNT+" 건 /  "+resultData[0].TOTAL_AM+" 원</b>";
			str += "<img src='<c:url value='/images/icon/empty_paper.png'/>'>";
			str += "<br/><h3>조회된 결과가 없습니다.</h3>";
			
	}
	$("#listTab1").append(str);
	$("#totCntArea").append(totStr);
}

function optionChange(){
    select_name = $("#period_fix1").children("option:selected").text();
    divSearch(ajaxStartDate+"01",ajaxStartDate+lastDay(selectDate),$("#period_fix1").children("option:selected").val(),select_name);
}

function whenSuccess3(resdata){
	//alert(resdata.resultMap.length);
	$("#listTab3").empty();
	$("#totCntArea").empty();

	//select_name = $("#period_fix1").children("option:selected").text();
/* 	if(select_name == ""){
		select_name = "선택12"
	} */
	var str = "";
	var totStr = "<h3>최근 영수증 내역 확인</h3>";
	str += "<div class='select_box'>";
	str += "<label for='period_fix'>"+select_name+"</label>";
	str += "<select name='period_fix' id='period_fix1' onchange='optionChange()'>";
	str += "<option value=''>선택</option>";
	str += "<option value='A1'>A1</option>";
	str += "<option value='A2'>A2</option>";
	str += "<option value='A3'>A3</option>";
	str += "<option value='A4'>A4</option>";
	str += "<option value='A5'>A5</option>";
	str += "<option value='A6'>A6</option>";
	str += "<option value='A7'>A7</option>";
	str += "</select>";
	str += "</div>";	  	
	
	var resultData = resdata.resultMap;
	if(resultData.length>0){
		if($('#btnTab3').hasClass('tab-active')){
			totStr += "<b>총"+ resultData[0].TOTAL_CNT+" 건 /  "+resultData[0].TOTAL_AM+" 원</b>";
		}
          
		for(var i=0; i<resultData.length; i++){
			str += "<a href='#' class='user-list-item' id="+parseInt(i+1)+">";
			if(resultData[i].PAY_DIV_CD == "A1"){
				str += "<img src='<c:url value='/images/icon/beer_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A2"){
				str += "<img src='<c:url value='/images/icon/chicken_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A3"){
				str += "<img src='<c:url value='/images/icon/coffe_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A4"){
				str += "<img src='<c:url value='/images/icon/drug_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A5"){
				str += "<img src='<c:url value='/images/icon/game_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A6"){
				str += "<img src='<c:url value='/images/icon/hospital_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A7"){
				str += "<img src='<c:url value='/images/icon/movie_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A8"){
				str += "<img src='<c:url value='/images/icon/present_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A9"){
				str += "<img src='<c:url value='/images/icon/shop_icon.png'/>' alt='img'>";
			}else{
				str += "<img src='<c:url value='/images/icon/shop_icon.png'/>' alt='img'>";
			}
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
			if(resultData.length<9){
				$("#addDivBtn").hide();
			}else{
				str += "<div id='addDivBtn'>";
				str += "<a class='button center-button button-green full-bottom detected-button' href='#' id='addBtn' >더보기</a>";
				str += "</div>";					
			}
	}else{
			totStr += "<b>총0건 /  0원</b>";		
			str += "<img src='<c:url value='/images/icon/empty_paper.png'/>'>";
			str += "<br/><h3>조회된 결과가 없습니다.</h3>";
				
	}
	$("#listTab3").append(str);
	$("#totCntArea").append(totStr);
	select_name = "선택";
}

function whenError(request,status,error){
	alert("ERROR");
}

//현재달의 마지막날(일자) 구하기
function lastDay(strDate){
   var d,d2, s = "";           
   //d = new Date();             
   d2 = new Date(strDate.getYear(),strDate.getMonth()+1,"");                      
   //s += d2.getYear()+ kind;                       
   //s += (d2.getMonth()+1) + kind;  //여기에 꼭 +1이 있어야됨 생략하고 위에다가 +1하면 1월이 0으로 나옴
   s += d2.getDate();
   return(s);                             
}

function	 goDailyList(id){
	if(parseInt(id)<10){
		id = "0"+id;
	}
    $.ajax({
        type: "post",
        url : "/receipt/theReal/selectDailyList.do",
        /* dataType : "html", */
        dataType:"json",
        data: {
        	date : ajaxStartDate+id,
        	eMail : '${eMail}',
        },
        success: dailySuccess,
        error: whenError
    	});	
}

function dailySuccess(resdata){ //달력 함수
	$("#listTab1").show();
	$("#listTab1").css("display","none");
	$("#listTab2").css("display","none");
	$("#listTab2L").css("display","block");
	$("#listTab2").hide();
	$("#listTab2L").empty();
	$("#totCntArea").empty();
	var str = "";
	var totStr = "<h3>최근 영수증 내역 확인</h3>";
	var resultData = resdata.resultMap;
	if(resultData.length>0){
		
		totStr += "<b>총"+ resultData[0].TOTAL_CNT+" 건 /  "+resultData[0].TOTAL_AM+" 원</b>";
	            
		for(var i=0; i<resultData.length; i++){
			console.log(resultData);
			str += "<form id='dailyForm"+parseInt(i+1)+"' name='dailyForm"+parseInt(i+1)+"' action='/receipt/theReal/F03.do'  method='post'>";
			str += "<a href='#' class='user-list-item' name = 'dailyList' id='"+parseInt(i+1)+"'>";
			if(resultData[i].PAY_DIV_CD == "A1"){
				str += "<img src='<c:url value='/images/icon/beer_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A2"){
				str += "<img src='<c:url value='/images/icon/chicken_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A3"){
				str += "<img src='<c:url value='/images/icon/coffe_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A4"){
				str += "<img src='<c:url value='/images/icon/drug_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A5"){
				str += "<img src='<c:url value='/images/icon/game_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A6"){
				str += "<img src='<c:url value='/images/icon/hospital_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A7"){
				str += "<img src='<c:url value='/images/icon/movie_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A8"){
				str += "<img src='<c:url value='/images/icon/present_icon.png'/>' alt='img'>";
			}else if(resultData[i].PAY_DIV_CD == "A9"){
				str += "<img src='<c:url value='/images/icon/shop_icon.png'/>' alt='img'>";
			}else{
				str += "<img src='<c:url value='/images/icon/shop_icon.png'/>' alt='img'>";
			}
			str += "<strong>"+resultData[i].USE_DATE+"<br></strong>";
			str += "<strong>"+resultData[i].PAY_DIV+"</strong>";
			str += "<input type='hidden'  name='dailyUseDate' id='dailyUseDate"+parseInt(i+1)+ "' value='"+resultData[i].USE_DATE+"'/>";
			str += "<input type='hidden'  name='dailyUseDateOrg' id='dailyUseDateOrg"+parseInt(i+1)+ "' value='"+resultData[i].USE_DATE_ORG+"'/>";
			str += "<input type='hidden'  name='dailyPayDiv' id='dailyPayDiv"+parseInt(i+1)+ "' value='"+resultData[i].PAY_DIV+"'/>";
			str += "<input type='hidden'  name='dailyEtpNm' id='dailyEtpNm"+parseInt(i+1)+ "' value='"+resultData[i].ETP_NM+"'/>";
			str += "<input type='hidden'  name='dailyPayAm' id='dailyPayAm"+parseInt(i+1)+ "' value='"+resultData[i].PAY_AM+"'/>";
			str += "<input type='hidden'  name='dailyEmail' id='dailyEmail"+parseInt(i+1)+ "' value='"+resultData[i].EMAIL+"'/>";
			str += "<input type='hidden'  name='dailyPayGoods' id='dailyPayGoods"+parseInt(i+1)+ "' value='"+resultData[i].PAY_GOODS+"'/>";
			str += "<i><b>"+resultData[i].ETP_NM+" : "+resultData[i].PAY_AM+"원</b></i>";
			str += "</a>";
			str += "</form>";
		}
	}else{
			str += "<img src='<c:url value='/images/icon/empty_paper.png'/>'>";
			str += "<br/><h3>조회된 결과가 없습니다.</h3>";
	}
	$("#listTab2L").append(str);
	$("#totCntArea").append(totStr);	
    $("a[name='dailyList']").click(function(){
    	var id = $(this).attr('id');
    	$("#dailyForm"+id).submit();
    })
}

function goF03Success(resdata){
	// location.href='/receipt/theReal/F03.do';
	console.log("resdata:"+resdata);
	console.log("F03이동");
}

function calendar(resdata){ //달력 함수

	var tYear = ajaxStartDate.substring(0,4);
	var tMonth = (month+1);
	if(tMonth == "12"){
		tMonth = "0";
	}
	$(".calendar-days").empty();
	var nowDate = new Date();               //오늘 날짜 객체 선언
	var nYear = nowDate.getFullYear();      //오늘의 년도
	var nMonth = nowDate.getMonth() ;       //오늘의 월 ※ 0월부터 시작
	var nDate = nowDate.getDate();           //오늘의 날
	var nNumday = nowDate.getDay();         //오늘의 요일 0=일요일...6=토요일
	var endDay=new Array(31,28,31,30,31,30,31,31,30,31,30,31);      //각달의 마지막 날짜
	var dayName=new Array("일", "월", "화", "수", "목", "금", "토"); // 숫자 요일을 문자 요일 바꿀 함수
	var col=0;  //나중에 앞뒤 빈 날짜칸 계산 
	var str = "";
	if (tYear==null)   //null 일경우, 처음 페이지가 로드 될때의 년도는 
		{tYear=nYear;} // 현재 년도를 가져오고

	if (tMonth==null)   //null 일경우, 처음 페이지가 로드 될때의 월은
		{tMonth=nMonth;}//현재 월을 가져오고
		eDate= new Date();       // 변경된 날짜 객체 선언
		eDate.setFullYear(tYear);// 변경된 년도 세팅
		eDate.setMonth(tMonth);  // 변경된 월 세팅
		eDate.setDate(1);        // 날짜는 1일로 설정해서
		var fNumday=eDate.getDay();    // 첫번째 날짜 1일의 숫자 요일
		var lastDay=endDay[eDate.getMonth()]; //변경된 월의 마지막 날짜

		if ((eDate.getMonth()==1)&&(((eDate.getYear()%4==0)&&(eDate.getYear() %100 !=0))||eDate.getYear() % 400 ==0 ))
			{lastDay=29;} // 0월 부터 시작하므로 1는 2월임. 윤달 계산 4년마다 29일 , 100년는 28일, 400년 째는 29일

		for (i=0;i<fNumday;i++){          // 첫번째 날짜의 숫자 요일을 구해서 그전까지는 빈칸 처리
			col++;                     
			str += "<a class='clear-day'><i></i></a>";
		}
		for ( i=1; i<=lastDay; i++){       // 해당 월의 달력 
			col++;
		if(col%7 == 1 || col%7 == 0){
			if(col<10){
				str += "<a name = 'calDay' id='"+i+"' class='light-day' href='#' onclick=goDailyList($(this).attr('id'))><i id= 'data0"+i+"'></i>"+i+"</a>";
			}else{
				str += "<a name = 'calDay' id='"+i+"' class='light-day' href='#' onclick=goDailyList($(this).attr('id'))><i id= 'data"+i+"'></i>"+i+"</a>";
			}
		}else{
			if(col<10){
				str += "<a name = 'calDay' id='"+i+"' class='clear-day' href='#' onclick=goDailyList($(this).attr('id'))><i id= 'data0"+i+"'></i>"+i+"</a>";
			}else{
				str += "<a name = 'calDay' id='"+i+"' class='clear-day' href='#' onclick=goDailyList($(this).attr('id'))><i id= 'data"+i+"'></i>"+i+"</a>";
			}
		}
		}   

		var resultData = resdata.resultMap;
		
		for (i=col;i<dayName.length;i++){        //마지막 날에서 남은 요일의 빈 칸 만들기
			str += "<a id='day1' class='' clear-day' href='#' onclick=goDailyList($(this).attr('id'))><i></i></a>"; 
		}
		
		$(".calendar-days").append(str);
		
/* 			if($('#btnTab2').hasClass('tab-active')){
				alert("2");
			} */
		if(resultData.length>0){
			for(var i=0; i<resultData.length; i++){
				$("#data"+resultData[i].CNT_DATE).text("총 "+resultData[i].CONUT+"건"); //resultData[i].USE_DATE.substring(8,10);
			}
		}

}		
</script>    
</body>