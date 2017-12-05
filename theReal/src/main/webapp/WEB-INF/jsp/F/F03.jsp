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
<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;border-color:#aabcfe;}
.tg td{font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;}
.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#039;background-color:#b9c9fe;}
.tg .tg-m3kb{font-weight:bold;font-size:large;text-align:right;vertical-align:top}
.tg .tg-f9es{font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top}
.tg .tg-v6ky{font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top}
.tg .tg-rd6t{font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top}
th.tg-sort-header::-moz-selection { background:transparent; }th.tg-sort-header::selection      { background:transparent; }th.tg-sort-header { cursor:pointer; }table th.tg-sort-header:after {  content:'';  float:right;  margin-top:7px;  border-width:0 4px 4px;  border-style:solid;  border-color:#404040 transparent;  visibility:hidden;  }table th.tg-sort-header:hover:after {  visibility:visible;  }table th.tg-sort-desc:after,table th.tg-sort-asc:after,table th.tg-sort-asc:hover:after {  visibility:visible;  opacity:0.4;  }table th.tg-sort-desc:after {  border-bottom:none;  border-width:4px 4px 0;  }@media screen and (max-width: 767px) {.tg {width: auto !important;}.tg col {width: auto !important;}.tg-wrap {overflow-x: auto;-webkit-overflow-scrolling: touch;}}</style>
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
	<div class="tg-wrap">
	<table id="tg-YqJ3z" class="tg" style="undefined;table-layout: fixed; width: 555px">
	<colgroup>
	<col style="width: 101px">
	<col style="width: 177px">
	<col style="width: 101px">
	<col style="width: 176px">
	</colgroup>
	  <tr>
	    <th class="tg-m3kb" colspan="4">전자영수증</th>
	  </tr>
	  <tr>
	    <td class="tg-f9es">카드종류</td>
	    <td class="tg-v6ky" id="payDiv">${data.dailyPayDiv }</td>
	    <td class="tg-f9es">카드번호</td>
	    <td class="tg-rd6t">9410-0900-****-7624</td>
	  </tr>
	  <tr>
	    <td class="tg-f9es">유효기간</td>
	    <td class="tg-v6ky">**/**</td>
	    <td class="tg-f9es">거래일시</td>
	    <td class="tg-rd6t" id="useDate">${data.dailyUseDateOrg }</td>
	  </tr>
	  <tr>
	    <td class="tg-f9es">품명</td>
	    <td class="tg-v6ky" colspan="3" id="payGoods">${data.dailyPayGoods }</td>
	  </tr>
	  <tr>
	    <td class="tg-f9es">결제방법</td>
	    <td class="tg-v6ky">카드결제</td>
	    <td class="tg-f9es">금액</td>
	    <td class="tg-rd6t" id="payAm">${data.dailyPayAm }</td>
	  </tr>
	  <tr>
	    <td class="tg-f9es">거래종류</td>
	    <td class="tg-v6ky">신용 승인</td>
	    <td class="tg-f9es">세금</td>
	    <td class="tg-rd6t">0</td>
	  </tr>
	  <tr>
	    <td class="tg-f9es">대표자</td>
	    <td class="tg-v6ky">김해성외1</td>
	    <td class="tg-f9es">봉사료</td>
	    <td class="tg-rd6t">0</td>
	  </tr>
	  <tr>
	    <td class="tg-f9es">승인번호</td>
	    <td class="tg-v6ky">18222747</td>
	    <td class="tg-f9es">합계</td>
	    <td class="tg-rd6t" id="payAm">${data.dailyPayAm }</td>
	  </tr>
	  <tr>
	    <td class="tg-f9es">가맹점명</td>
	    <td class="tg-v6ky" colspan="3" id="etpNm">${data.dailyEtpNm }</td>
	  </tr>
	  <tr>
	    <td class="tg-f9es">가맹점주소</td>
	    <td class="tg-v6ky" colspan="3">서울 중구 퇴계로100</td>
	  </tr>
	  <tr>
	    <td class="tg-f9es">결제대행사</td>
	    <td class="tg-v6ky" colspan="3">(주)신세계페이먼츠</td>
	  </tr>
	  <tr>
	    <td class="tg-f9es">문의전화</td>
	    <td class="tg-v6ky">1588-4349</td>
	    <td class="tg-f9es">서명</td>
	    <td class="tg-rd6t">나병준</td>
	  </tr>
	</table>
	        <div id="addDivBtn">
				<a class="button center-button button-green full-bottom detected-button" href="#" id="sendMailBtn" >이메일보내기</a>
	        </div>
</div>
<script type="text/javascript" charset="utf-8">var TgTableSort=window.TgTableSort||function(n,t){"use strict";function r(n,t){for(var e=[],o=n.childNodes,i=0;i<o.length;++i){var u=o[i];if("."==t.substring(0,1)){var a=t.substring(1);f(u,a)&&e.push(u)}else u.nodeName.toLowerCase()==t&&e.push(u);var c=r(u,t);e=e.concat(c)}return e}function e(n,t){var e=[],o=r(n,"tr");return o.forEach(function(n){var o=r(n,"td");t>=0&&t<o.length&&e.push(o[t])}),e}function o(n){return n.textContent||n.innerText||""}function i(n){return n.innerHTML||""}function u(n,t){var r=e(n,t);return r.map(o)}function a(n,t){var r=e(n,t);return r.map(i)}function c(n){var t=n.className||"";return t.match(/\S+/g)||[]}function f(n,t){return-1!=c(n).indexOf(t)}function s(n,t){f(n,t)||(n.className+=" "+t)}function d(n,t){if(f(n,t)){var r=c(n),e=r.indexOf(t);r.splice(e,1),n.className=r.join(" ")}}function v(n){d(n,L),d(n,E)}function l(n,t,e){r(n,"."+E).map(v),r(n,"."+L).map(v),e==T?s(t,E):s(t,L)}function g(n){return function(t,r){var e=n*t.str.localeCompare(r.str);return 0==e&&(e=t.index-r.index),e}}function h(n){return function(t,r){var e=+t.str,o=+r.str;return e==o?t.index-r.index:n*(e-o)}}function m(n,t,r){var e=u(n,t),o=e.map(function(n,t){return{str:n,index:t}}),i=e&&-1==e.map(isNaN).indexOf(!0),a=i?h(r):g(r);return o.sort(a),o.map(function(n){return n.index})}function p(n,t,r,o){for(var i=f(o,E)?N:T,u=m(n,r,i),c=0;t>c;++c){var s=e(n,c),d=a(n,c);s.forEach(function(n,t){n.innerHTML=d[u[t]]})}l(n,o,i)}function x(n,t){var r=t.length;t.forEach(function(t,e){t.addEventListener("click",function(){p(n,r,e,t)}),s(t,"tg-sort-header")})}var T=1,N=-1,E="tg-sort-asc",L="tg-sort-desc";return function(t){var e=n.getElementById(t),o=r(e,"tr"),i=o.length>0?r(o[0],"td"):[];0==i.length&&(i=r(o[0],"th"));for(var u=1;u<o.length;++u){var a=r(o[u],"td");if(a.length!=i.length)return}x(e,i)}}(document);document.addEventListener("DOMContentLoaded",function(n){TgTableSort("tg-YqJ3z")});</script>
    <!-- Footer -->
    <div class="footer">
        <p class="center-text">Copyright (c) 2016 The REAL All rights reserved</p>
    </div>    
    <a href="#" class="header-up"><i class="fa fa-caret-up"></i>맨위로</a>
</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#sendMailBtn").click(function(){
		callAjax();
	})
})
function callAjax(){
    $.ajax({
     type: "post",
     url : "/receipt/theReal/sendMail.do",
     data: {
        	 useDate : $('#useDate').text(),
        	 payDiv : $('#payDiv').text(),
        	 payGoods : $('#payGoods').text(),
        	 etpNm : $('#etpNm').text(),
        	 payAm : $('#payAm').text(),
        	 eMail : '${data.dailyEmail }',
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