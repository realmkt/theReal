<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%
String seqParam = request.getParameter("IDX");
System.out.println("param111:"+seqParam);
%>
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

<script src="<c:url value='/editor/js/HuskyEZCreator.js'/>" charset="utf-8"></script>

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
        <a href="javascript:void(0)" class="header-icon-one show-top-menu bg6-blur"><i class="fa fa-navicon"></i></a>
    </div>    
    <div class="header-clear"></div>
    <div class="menu-background"></div>
    
    <div class="content">       

<table>
		<colgroup>
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
		</colgroup>
		<caption>게시글 작성</caption>
		<tbody>
		<tr>
			<th scope="row">제목</th>
			<td colspan="3"><input type="text" id="title" style="width:100%; height:50px; "/></td>
		</tr>
 		<tr>
			<th scope="row">메인설정여부</th>
			<td><a id="mainShow" href="javascript:void(0)" class="checkbox checkbox-one">메인에 노출할 경우 체크박스를 선택</a></td>
			<th scope="row">노출여부</th>
			<td><a id= "showDiv" href="javascript:void(0)" class="checkbox checkbox-one">컨텐츠를 노출할 경우 체크박스를 선택</a></td>
		</tr>
		<tr>
			<th scope="row">내용</th>
			<td colspan="3">
				<textarea rows="10" cols="30" id="ir1" name="content" style="width:100%; height:412px; "></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<a class="button button-green full-bottom detected-button" href="javascript:void(0)" id="btnSave" >글쓰기</a>
				<a class="button button-green full-bottom detected-button" href="javascript:void(0)" id="btnList" >목록</a>
			</td>
		</tr>
	</tbody>
</table>
        <div class="decoration"></div>
    </div>
    
    <!-- Footer -->
    
   <!-- Footer -->
    
    <div class="footer">
        <p class="center-text">Copyright (c) 2016 The REAL All rights reserved</p>
    </div>    
    <a href="javascript:void(0)" class="header-up"><i class="fa fa-caret-up"></i>맨위로</a>
    
    <!-- Share Menu -->
         
    
</div>
<script type="text/javascript">
var updateDiv = "N";
var oEditors = [];
function commonIp(div){
	var commonIp = "";
	if(div == "dev"){
		//commonIp = "121.138.82.229";
		//commonIp = "14.52.103.145";
		//commonIp = "121.134.168.194";
		//commonIp = "175.209.135.73";
		//commonIp = "121.138.82.220";
		commonIp = "192.168.0.39";
		//commonIp = "14.52.103.239";
	}else{
		commonIp = "221.148.29.120";
	}
	return commonIp; 
}	
$(function(){
	$.support.cors = true;
					nhn.husky.EZCreator.createInIFrame({
						oAppRef: oEditors,
						elPlaceHolder: "ir1",
						//SmartEditor2Skin.html 파일이 존재하는 경로
						//sSkinURI: "http://localhost:8080/receipt/editor/SmartEditor2Skin.html",
						sSkinURI: "http://"+commonIp('dev')+":8080/theReal/editor/SmartEditor2Skin.html",
						htParams : {
							// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseToolbar : true,				
							// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseVerticalResizer : true,		
							// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
							bUseModeChanger : true,			
							fOnBeforeUnload : function(){
								
							}
						}, 
						fOnAppLoad : function(){
							//기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
							//oEditors.getById["ir1"].exec("PASTE_HTML", ["기존 DB에 저장된 내용을 에디터에 적용할 문구"]);
							var seqParam = <%=seqParam%>;	
							if(null != seqParam){
								updateDiv = "Y";								
								$("#title").val("${list.resultMap[0].BOARD_TITLE}");
								oEditors.getById["ir1"].exec("PASTE_HTML", ['${list.resultMap[0].BOARD_CONTENTS}']);
								if("${list.resultMap[0].MAIN_GB}" == "Y"){
									$("#mainShow").addClass("checkbox-one-checked")
								}
								if("${list.resultMap[0].DEL_GB}" == "N"){
									$("#showDiv").addClass("checkbox-one-checked")
								}
							}
						},
						fCreator: "createSEditor2"
					});
	$("#btnSave").click(function(){
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", [document.getElementById("ir1").value]);
		alert(document.getElementById("ir1").value);
		var mainShowDiv = "";
		var delDiv = "";
		if($("#mainShow").hasClass("checkbox checkbox-one checkbox-one-checked")){
			mainShowDiv = "Y";
		}else{
			mainShowDiv = "N";
		}
		if($("#showDiv").hasClass("checkbox checkbox-one checkbox-one-checked")){
			delDiv = "N";
		}else{
			delDiv = "Y";
		}	
		
		console.log($("#title").val());
		console.log($("#ir1").val());
		console.log(mainShowDiv);
		console.log(showDiv);
		if(updateDiv == "N"){
		    $.ajax({
			     type: "post",
			     url : "/theReal/receipt/insertBoard.do",
			     data: {
						TITLE : $("#title").val(),
						CONTENTS : $("#ir1").val(),
						MAIN_GB : mainShowDiv,
						DEL_GB : delDiv,
			     },
			     success: whenSuccess,
			     error: whenError
			 	});	
		}else{
		    $.ajax({
			     type: "post",
			     url : "/theReal/receipt/Admin/updateAdminBoard.do",
			     data: {
			    	 	IDX : "${list.resultMap[0].SEQ}", 
						TITLE : $("#title").val(),
						CONTENTS : $("#ir1").val(),
						MAIN_GB : mainShowDiv,
						DEL_GB : delDiv,
			     },
			     success: whenSuccess,
			     error: whenError
			 	});	
			
		}
		
		
	})
	
	$("#btnList").click(function(){
		location.href='/receipt/theReal/Admin/boardMng.do';		
	})
	
});


function whenSuccess(){
	alert("성공");
	location.href='/receipt/theReal/Admin/boardMng.do';		
}

function whenError(){
	alert("실패");
}
</script>    
</body>
