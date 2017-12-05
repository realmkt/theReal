<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
    
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>더리얼 전자영수증</title>
  <!-- CORE CSS-->
  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/css/materialize.min.css">
   <!-- jQuery Library -->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/ui.css'/>" />

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
  <!--materialize js-->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/js/materialize.min.js"></script>
  

<style type="text/css">
html,
body {
    height: 100%;
}
html {
    display: table;
    margin: auto;
    height: 100%;
    width: 50%;
}
body {
    display: table-cell;
    vertical-align: middle;
}
.margin {
  margin: 0 !important;
}
.row {
margin-left:-0.75rem;margin-right:-0.75rem
}
.col {
float:left;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;padding:0 0.75rem
}
.card-panel {
box-shadow:0 2px 5px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12)
}
body .container .content .submit-wrap {
  position: absolute;
  bottom: 0;
  width: 100%;
}
body .container .content .submit-wrap a {
  font-size: 12px;
  display: block;
  margin-top: 20px;
  text-align: center;
  text-decoration: none;
  color: #999999;
}
body .container .content .submit-wrap a:hover {
  text-decoration: underline;
}

body .container .content label:first-of-type, body .container .content input:first-of-type, body .container .content .more:first-of-type {
  -moz-animation: slideIn 0.4s cubic-bezier(0.37, 0.82, 0.2, 1);
  -webkit-animation: slideIn 0.4s cubic-bezier(0.37, 0.82, 0.2, 1);
  animation: slideIn 0.4s cubic-bezier(0.37, 0.82, 0.2, 1);
}
body .container .content label:nth-of-type(2), body .container .content input:nth-of-type(2), body .container .content .more:nth-of-type(2) {
  -moz-animation: slideIn 0.5s cubic-bezier(0.37, 0.82, 0.2, 1);
  -webkit-animation: slideIn 0.5s cubic-bezier(0.37, 0.82, 0.2, 1);
  animation: slideIn 0.5s cubic-bezier(0.37, 0.82, 0.2, 1);
}
body .container .content label:nth-of-type(3), body .container .content input:nth-of-type(3), body .container .content .more:nth-of-type(3) {
  -moz-animation: slideIn 0.6s cubic-bezier(0.37, 0.82, 0.2, 1);
  -webkit-animation: slideIn 0.6s cubic-bezier(0.37, 0.82, 0.2, 1);
  animation: slideIn 0.6s cubic-bezier(0.37, 0.82, 0.2, 1);
}
</style>
  
</head>

<body class="white">


  <div id="login-page" class="row">
    <div class="col s12 z-depth-6 card-panel" style="height: 700px">
      <form style="height: 700px" name = "loginForm" id = "loginForm" class="login-form">
        <div class="row">
          <div class="input-field col s12 center">
           <%--  <img src="${pageContext.request.contextPath}/resources/img/thereal_logo.png" style="max-width: 300px;min-height: 300px"  alt="" class="responsive-img valign profile-image-login"> --%>
            <p class="center login-form-text">더리얼마케팅 전자영수증</p>
          </div>
        </div>
        <div class="row margin">
          <div class="input-field col s12">
            <i class="mdi-social-person-outline prefix"></i>
            <input class="validate" id="EMAIL" name="EMAIL"  type="text">
            <label for="이메일" class="center-align">이메일</label>
          </div>
          <div class="input-field col s12">
            <i class="mdi-social-person-outline prefix"></i>
            <input class="validate" id="TNB"  name="TNB" type="number">
            <label for="전화번호" class="center-align">전화번호</label>
          </div>
          <div class="input-field col s12">
            <i class="mdi-social-person-outline prefix"></i>
            <input class="validate" id="ETP_NM"  name="ETP_NM" type="text">
            <label for="업체명" class="center-align">업체명</label>
          </div>
          <div class="input-field col s12">
            <i class="mdi-social-person-outline prefix"></i>
            <input class="validate" id="BZN" name="BZN" type="number" maxlength="10">
            <label for="사업자등록번호" class="center-align">사업자등록번호</label>
          </div>
          <div class="input-field col s12">
            <i class="mdi-social-person-outline prefix"></i>
            <input class="validate" id="PAY_AM" name="PAY_AM" type="number">
            <label for="결제금액" class="center-align">결제금액</label>
          </div>
          <div class="input-field col s12">
            <i class="mdi-social-person-outline prefix"></i>
            <input class="validate" id="PAY_DIV" name="PAY_DIV"  type="text">
            <label for="카드종류" class="center-align">카드종류</label>
          </div>
          <div class="input-field col s12">
            <i class="mdi-social-person-outline prefix"></i>
            <input class="validate" id="PAY_GOODS" name="PAY_GOODS" type="text">
            <label for="품명" class="center-align">품명</label>
          </div>
          <div class="input-field col s12">
             <!-- <i class="mdi-social-person-outline prefix"></i> -->
            <!-- <input class="validate" id="PAY_GOODS" name="PAY_GOODS" type="text"> -->
            <div>
            <label for="품명" class="center-align">상품코드</label>
			<select name="PAY_DIV_CD" id="PAY_DIV_CD" style="display: block; text-align: center; margin-left: auto; width: 80%">
					<option value="A1">호프</option>
					<option value="A2">치킨</option>
					<option value="A3">커피</option>
					<option value="A4">약국</option>
					<option value="A5">게임</option>
					<option value="A6">병원</option>
					<option value="A7">영화</option>
			</select>
            </div>
          </div>
        </div>
        <div class="row" align="center">
         <div style="width: 50%">
          
            <a id = "submitBtn" class="btn waves-effect waves-light col s12">전송</a>
          </div>
        <div id="listLayout"></div>
        </div>
      </form>
    </div>
  </div>

   <footer>
		<div class="submit-wrap">
		     <a href="#" class="more">© 2015 THE REAL MARKETING</a>
		</div>    		
  </footer>
  <script type="text/javascript">
  
  function optionChange(){
	    alert($("#period_fix1 option:selected").val());
	    //divSearch(ajaxStartDate+"01",ajaxStartDate+lastDay(selectDate),$("#period_fix1").children("option:selected").val(),select_name);
	}
  
	$(function(){
    	$("#submitBtn").click(function(){
    		
    		if(!$("#EMAIL").val()){
    			alert("이메일을 입력해주시기 바랍니다.");
    			$("#EMAIL").focus();
    			return;
    		}
    		else if(!$("#TNB").val()){
    			alert("전화번호를 입력해주시기 바랍니다.");
    			$("#TNB").focus();
    			return;
    		}
    		else if(!$("#ETP_NM").val()){
    			alert("업체명을 입력해주시기 바랍니다.");
    			$("#ETP_NM").focus();
    			return;
    		}
    		else if(!$("#BZN").val()){
    			alert("사업자등록번호를 입력해주시기 바랍니다.");
    			$("#BZN").focus();
    			return;
    		}
    		else if(!$("#PAY_AM").val()){
    			alert("결제금액 입력해주시기 바랍니다.");
    			$("#PAY_AM").focus();
    			return;
    		}
    		else if(!$("#PAY_DIV").val()){
    			alert("카드종류를 입력해주시기 바랍니다.");
    			$("#PAY_DIV").focus();
    			return;
    		}
    		else if(!$("#PAY_GOODS").val()){
    			alert("품명을 입력해주시기 바랍니다.");
    			$("#PAY_GOODS").focus();
    			return;
    		}
    		else{
    			
				/*     			
				var testObj = {};
    			var testObj2 = {};
    			var test= new Array; 
				*/

				/*     			
				test.push("4444");
    			test.push("44444444411111");
    			console.log(test); 
				*/
				
				/*     			
				testObj=$("#TNB").val();
    			test.push = testObj;
    			console.log(test);
    			testObj2=$("#EMAIL").val();
    			testObj3=$("#ETP_NM").val();
    			testObj4=$("#BZN").val();
    			testObj5=$("#PAY_AM").val();
    			testObj6=$("#PAY_DIV").val(); 
    			*/
    			
				/*     			
 				testArr1.push= $("#PAY_GOODS").val();
    			testArr1.push= "12";
    			testArr1.push= "34";
    			testArr1.push= "56";
    			testArr1.push= $("#PAY_GOODS").val();
    			console.log("testArr1:"+testArr1);
    			*/    			

    			/*     			
    			testObj.push=testObj2; 
    			testObj.push=testObj3; 
    			testObj.push=testObj4; 
    			testObj.push=testObj5; 
    			testObj.push=testObj6; 
    			console.log(testObj); 
    			*/

    			var gilDong = {
    	   				"location" : "Korea",
    					"arr" : ["a", "b"],
    					"family" : {
    						"father" : "아버지",
    						"mother" : "어머니"
    					}
    				}    			
    			
/* 				var gilDong = new Object();
				gilDong.location = "Korea";
				gilDong.gender = "man";
				//배열 선언
				gilDong.arr = ["a", "b"];
				//Object 안 Object를 선언
				gilDong.family = new Object();
				gilDong.family.father = "아버지";
				gilDong.family.mother = "어머니";   			 */
/* 				console.log(gilDong);    	
    			callAjax(gilDong);
    			return false; */
 				var comSubmit = new ComSubmit("loginForm");
				comSubmit.setUrl("<c:url value='/receipt/posResult.do' />");
				comSubmit.submit();
    		}
    	})
	})
	
function callAjax(testObj){
    $.ajax({
     type: "post",
     url : "/receipt/theReal/posResult.do",
     data: {
    	 testObj : testObj
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
	
	
	function chkNull(str){
		if(str == "" || str == null || str == undefined){
			return false;
		}else{
			return true;
		}
	}
</script>
</body>

</html>