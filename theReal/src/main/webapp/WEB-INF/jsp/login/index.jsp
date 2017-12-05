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
<script src="<c:url value='/scripts/jquery.placeholder.js'/>" charset="utf-8"></script>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
<script src="<c:url value='/scripts/jquery.js'/>" charset="utf-8"></script>
<script src="<c:url value='/scripts/jqueryui.js'/>" charset="utf-8"></script>
<script src="<c:url value='/scripts/framework.plugins.js'/>" charset="utf-8"></script>
<script src="<c:url value='/scripts/custom.js'/>" charset="utf-8"></script>

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
        
<!-- <div class="all-elements">
    <div class="header">
        <h1 align="center">전자영수증</h1>
    </div>    
    <div class="header-clear"></div>
    
    <div class="menu-background"></div>
</div> -->
    
<!--Fullscreen Pages Go Under the all-elements class -->
    
<div class="login-page">
    <div class="login-page-overlay"></div>
    
    <div class="login-page-wrapper">
        <h3>전자영수증</h3>
        <p>
            쉽고 편한 영수증 보관/<br/>
            가계부를 통한 생활비 절감
        </p>
        <input type="text"     class="login-username" value="" placeholder="ID" id="username">
        <input type="password" class="login-password" value="" placeholder="PW" id="password">
        <div class="one-half">
            <a href="#" class="button button-green" id="loginBtn">로그인</a>
        </div>
        <div class="one-half last-column">
            <a href="#" class="button button-blue" id="signBtn">회원가입</a>
        </div>
        <div class="clear"></div>
        <!-- <a class="forgot" href="#">비밀번호찾기</a> -->
    </div>            
`</div>
<script type="text/javascript">
/* $(function() {
	}); */
 $(document).ready(function(){
	  //$('input, textarea').placeholder();
	 
		$('input').keyup(function(e) {
		    if (e.keyCode == 13){
		    	goAjax();
		    }
		});
		
		$("#loginBtn").click(function(){
			goAjax();
	 })
	 
	 $("#signBtn").click(function(){
		 location.href='/receipt/theReal/member.do';
	 })
})

	function goAjax(){
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
	}

	function callAjax(){
	     $.ajax({
	      type: "post",
	      url : "/receipt/theReal/loginChk.do",
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
        	var getId = $('#username').val();
    		location.href='/receipt/theReal/loginSucess.do?id='+getId;
        }else{
        	alert("회원정보를 잘못입력하였거나 회원이 아닙니다.");
        	 $('#username').val("");
        	 $('#password').val("");
        	 $('#username').focus();
        	 return;
        }
        
    }
    
    function whenError(){
        alert("Error");
    }
</script>
</body>
