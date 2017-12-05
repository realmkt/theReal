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
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
<style type="text/css">
.placeholder { color: #aaa; }
.txtArea { position: relative;display: table; width: 100%; height: 61px;background: #f0f0f0; border: 1px solid #ccc; border-radius: 4px; padding: 10px 4px 10px 20px; margin-bottom: 10px; font-size: 14px; letter-spacing: -0.7px;line-height: 20px; color: #666;box-sizing: border-box;},
.txtArea.info_check {padding: 10px 20px;},
.txtArea.info_check>span {width: 75%;},
.txtArea.info_check .checkBox li {padding-right: 0;padding-left: 8px;}
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
	<!--     <div class="header">
	        <h1 align="center">전자영수증</h1>
	    </div>    
	    <div class="header-clear"></div> -->
    
    <div class="menu-background"></div>
</div>
    
	<div class="signup-page">
		<div class="signup-page-overlay"></div>
		<div class="signup-page-wrapper">
	        <h3>전자영수증</h3>
	        <p>
	            사용중이신 이메일주소로<br/>
	            간편한 회원가입을 하실수 있습니다.
	        </p>
			<input type="text" class="signup-email" value="" placeholder="Email"  id="username">
			<input type="text" class="signup-password" value="" placeholder="Password" id="password">
	        <div class="one-full">
	            <a href="#" class="button button-green full-button" id="memberBtn">간편가입하기</a>
	            <a href="#" class="button button-blue full-button" id="goLogin">목록</a>
	        </div>                          
			<div class="clear"></div>

		</div>            
	</div>    
    
    
<!--Fullscreen Pages Go Under the all-elements class -->
    
<!-- <div class="signup-page">
    <div class="signup-page-overlay"></div>
    <div class="signup-page-wrapper2"> -->
<!--         <h3>Wheels</h3>
        <p>
            Create an account bellow, it's really easy! 
        </p> -->
<!--                 <div class="one-half">
                    <a href="#" class="checkbox checkbox-one">Checkbox</a>
                    <a href="#" class="checkbox checkbox-one">Checkbox</a>
                    <a href="#" class="checkbox checkbox-one">Checkbox</a>
                    <a href="#" class="checkbox checkbox-one">Checkbox</a>
                    <a href="#" class="checkbox checkbox-one">Checkbox</a>
                </div> -->
                
<!-- <div class="txtArea info_check">
            <p class="user-list-follow pArea">
                <span><a href="#" class="checkbox2 checkbox-one fntgray"><em>전자영수증이용약관</em></a></span>
                <a href="#" class="follow2">전체보기</a>
            </p>
            <p class="user-list-follow pArea">
                <a href="#" class="checkbox2 checkbox-one fntgray"><em>개인정보 수집 및 이용</em></a>
                <a href="#" class="follow2">전체보기</a>
            </p>
            <p class="user-list-follow pArea">
                <a href="#" class="checkbox2 checkbox-one fntgray"><em>서비스 제공을 위한 취급 위탁</em></a>
                <a href="#" class="follow2">전체보기</a>
            </p>
            <p class="user-list-follow pArea">
                <span><a href="#" class="checkbox2 checkbox-one fntgray"><em>이메일고지서, 이메일 소식지 수신동의</em></a></span>
                <a href="#" class="follow2">전체보기</a>
            </p>
            <p class="user-list-follow pArea">
                <a href="#" class="checkbox2 checkbox-one fntgray"><em>전자금융거래이용약관</em></a>
                <a href="#" class="follow2">전체보기</a>
            </p>
</div>     -->
<!-- 
         <div class="one-third-responsive" style="border: 5px; border-color: blue;">
            <p class="user-list-follow">
                <a href="#" class="checkbox2 checkbox-one">전자영수증이용약관</a>
                <a href="#" class="follow2">전체보기</a>
            </p>
            <p class="user-list-follow">
                <a href="#" class="checkbox2 checkbox-one">전자영수증이용약관</a>
                <a href="#" class="follow2">전체보기</a>
            </p>
            <p class="user-list-follow">
                <a href="#" class="checkbox2 checkbox-one">전자영수증이용약관</a>
                <a href="#" class="follow2">전체보기</a>
            </p>
            <p class="user-list-follow">
                <a href="#" class="checkbox2 checkbox-one">전자영수증이용약관</a>
                <a href="#" class="follow2">전체보기</a>
            </p>
            <p class="user-list-follow">
                <a href="#" class="checkbox2 checkbox-one">전자영수증이용약관</a>
                <a href="#" class="follow2">전체보기</a>
            </p>
             <a href="#" class="checkbox2 checkbox-one">모두 동의</a>
        </div>   -->              
<!--         <input type="text"     class="login-username" value="" placeholder="ID(영문+숫자 8자리 이상)" id="username">
        <input type="password" class="login-password" value="" placeholder="PW(영문+숫자 8자리 이상)" id="password">        
        <div class="one-full">
            <a href="#" class="button button-green full-button">간편가입하기</a>
        </div>                
        <div class="clear"></div> -->

    </div>            
</div>
<script type="text/javascript">
/* $(function() {
	}); */
 $(document).ready(function(){
	  //$('input, textarea').placeholder();
	 
	 $("#goLogin").click(function(){
		location.href='/receipt/theReal/index.do';
	 })
	 
	 $("#memberBtn").click(function(){
		 if($('#username').val() == ""){
			 alert("이메일주소를 입력해주시기 바랍니다.");
			 $('#username').focus();
			 return;
		 }
		 if($('#password').val() == ""){
			 alert("비밀번호를 입력해주시기 바랍니다.");
			 $('#password').focus();
			 return;
		 }
		 callAjax();
	 })
})
   function callAjax(){
        $.ajax({
	        type: "post",
	        url : "/receipt/theReal/memberChk.do",
	        data: {
	        	id : $('#username').val(),
       			password : $('#password').val(),
	        },
	        success: whenSuccess,
	        error: whenError
     	});
    }
	
    function whenSuccess(resdata){
        if(resdata == 1){
        	alert("이미 가입된 메일주소입니다.");
        	 $('#username').val("");
        	 $('#password').val("");
        	 $('#username').focus();
        	 return;
        }else{
        	alert("회원가입이 완료되었습니다.");
        	var getId = $('#username').val();
    		location.href='/receipt/theReal/loginSucess.do?id='+getId;
        }
        
    }
    
    function whenError(){
        alert("Error");
    }
</script>
</body>
