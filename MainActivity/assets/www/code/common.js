var affliateFlag = false;

$(function () {
	var $activate_scrollator4 = $('#activate_scrollator4');

	$activate_scrollator4.bind('click', function () {
		var $document_body = $('body');
		
		if ($document_body.data('scrollator') === undefined) {
			$document_body.scrollator({
				custom_class: 'body_scroller'
			});
			$activate_scrollator4.val('destroy scrollator')
		} else {
			$document_body.scrollator('destroy');
			$activate_scrollator4.val('activate scrollator')
		}
	});

	$activate_scrollator4.trigger('click');
});




$(document).ready(function(){
	
 	$(document).on('click','#leftMenuLatest',function(){
 		location.href='latest.html?CI='+CI+'&telNo='+request.getParameter('telNo');
 	});
/* 	$(document).on('click','#leftMenuHistory',function(){
 		location.href='latest.html?telNo='+request.getParameter('telNo');
 	});*/
/* 	$(document).on('click','#leftMenuComHistiry',function(){
 		location.href='household.html?CI='+request.getParameter('CI')+'&telNo='+request.getParameter('telNo');
 	});
 	$(document).on('click','#leftMenuSmsHistory',function(){
 		location.href='mobile.html?CI='+request.getParameter('CI')+'&telNo='+request.getParameter('telNo');
 	});*/
 	$(document).on('click','#leftMenuAffliate',function(){
 		location.href='affliate.html?CI='+CI+'&telNo='+request.getParameter('telNo');
 	});
 	$(document).on('click','#leftMenuComHistiry',function(){
 		location.href='mobile.html?CI='+CI+'&telNo='+request.getParameter('telNo');
 	}); 	
 	$(document).on('click','#leftMenuEvent',function(){
 		location.href='event.html?CI='+CI+'&telNo='+request.getParameter('telNo');
 	});
 	$(document).on('click','#leftMenuCoupon',function(){
 		location.href='coupon.html?CI='+CI+'&telNo='+request.getParameter('telNo');
 	});
 	$(document).on('click','#leftMenuNotice',function(){
 		location.href='notice.html?CI='+CI+'&telNo='+request.getParameter('telNo');
 	});
 	$(document).on('click','.left-setting-icon',function(){
 		location.href='setting.html?CI='+CI+'&telNo='+request.getParameter('telNo');
 	});
 	$(document).on('click','.alen_logo',function(){
 		location.href='index.html?CI='+CI+'&telNo='+request.getParameter('telNo');
 	});
 	$(document).on('click','#settingBtn',function(){
 		location.href='setting.html?CI='+CI+'&telNo='+request.getParameter('telNo');
 	});
 	$(document).on('click','#leftMenuLogOut',function(){
	  	var storage = window.localStorage;
	  	localStorage.removeItem("CI");
		location.href='../login.html';
 	});
 	
 	$(document).on('click','#myCoupon',function(){
 		location.href='couponMy.html?telNo='+request.getParameter('telNo');
 	});
	/*   $("#allCheck").on("click",function(){
alert();
   var _value = $(this).is(":checked");
   $('input:checkbox[name="subCheck"]').each(function () { 
    this.checked = _value; 
   });
  });*/
 });
 
function replaceAll(content,before,after)
{
    return content.split(before).join(after);
}	

 
 function isNull(val)
{
    if (val == null || val == undefined || val == "") {
    	return true;
    }
    return false;
}

function alltrim(str) {
	var ret = "";
	if (str.length == 0) return ret;

	for (var i=0; i<str.length; i++) {
		if (str.charAt(i) != " ") ret += str.charAt(i);
	}
	return ret;
}	

//허용된 byte만큼 입력도중 실시간으로 string자르기
//<textArea>등에 사용하면 됩니다.
//onKeyup="checkByte(this,제한할byte수,"현재byte정보뿌려줄영역의ID");"
//마지막 인자는 선택사항입니다.
//ex)  onKeyup="checkByte(this,200,'nowByteShowArea');"
function getBytes(aquery) {
 var tcount = 0;

 tmpStr = new String(aquery);
 temp = tmpStr.length;
 
 for (k=0;k<temp;k++)
 {
     onechar = tmpStr.charAt(k);
     if (escape(onechar) =='%0D') { } else if (escape(onechar).length > 4) { tcount += 2; } else { tcount++; }
 }

	return tcount;
}
function getByte(sChar) {
	var c = 0;
	var u = escape(sChar);
	if (u.length < 4) { // 반각문자 : 기본적인 영문, 숫자, 특수기호
		c++; // + 1byte
	} else {
		var s = parseInt(sChar.charCodeAt(0));
		if (((s >= 65377)&&(s <= 65500))||((s >= 65512)&&(s <= 65518))) // 반각문자 유니코드 10진수 범위 : 한국어, 일본어, 특수문자
			c++; // + 1byte
		else // 전각문자 : 위 조건을 제외한 모든 문자
			c += 2; // + 2byte
	}
	return c;
}
function cutOverText(obj,maxByte,viewAreaID) {
	var sString = obj.value;
	var c = 0;
	for (var i=0; i<sString.length; i++) {
		c += parseInt(getByte(sString.charAt(i)));
		if (c>maxByte) {
			obj.value = sString.substring(0,i);
			break;
		}
	}
	showNowByte(obj.value,viewAreaID);
}
function showNowByte(sString,viewAreaID) {
	var vArea = document.getElementById(viewAreaID);
	if (vArea) vArea.innerHTML = getBytes(sString);
}
function checkByte(obj,maxByte,viewAreaID) {
	var sString = obj.value;
	showNowByte(sString,viewAreaID);
	if (getBytes(sString) > maxByte) {
		alert("최대 "+maxByte+"Bytes(한글 "+(maxByte/2)+"자/영문 "+maxByte+"자)까지만 입력하실 수 있습니다.");
		cutOverText(obj,maxByte,viewAreaID);
	}
}

function onlyNumberInput(){
	var code = window.event.keyCode;

 	if ((code > 34 && code < 41) || (code > 47 && code < 58) || (code > 95 && code < 106) || code == 8 || code == 9 || code == 46){
  		window.event.returnValue = true;
  		return;
 	}
 	window.event.returnValue = false;
}


function emailcheck(strValue)
{
var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
//입력을 안했으면
if(strValue.lenght == 0)
{return false;}
//이메일 형식에 맞지않으면
if (!strValue.match(regExp))
{return false;}
return true;
}

//개발IP셋팅
function commonIp(){
	var commonIp = "";
	var dev = false;
	if(dev){
		commonIp = "http://182.162.84.177:80";
		//commonIp = "117.52.97.40";
	}else{
		commonIp = "http://110.45.190.114:80";
	}
	return commonIp; 
}


function chkPwd(str){

	 var pw = str;

	 var num = pw.search(/[0-9]/g);

	 var eng = pw.search(/[a-z]/ig);

	 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	 

	 if(pw.length < 8 || pw.length > 20){

		 alert("8자리 ~ 20자리 이내로 입력해주세요.");

	  return false;

	 }

	 if(pw.search(/₩s/) != -1){

	  alert("비밀번호는 공백업이 입력해주세요.");

	  return false;

	 } if(num < 0 || eng < 0 || spe < 0 ){

	  alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");

	  return false;

	 }

	 

	 return true;

	}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function parseAppCoDiv(appCoCd) {
	var appCoCdNm ="";
	switch (appCoCd) {
	/*case "000": appCoCdNm = "미확인"; break;*/
	case "001": appCoCdNm = "외식/식사" ;break;
	case "002": appCoCdNm = "카페/베이커리" ;break;
	case "003": appCoCdNm = "술/유흥" ;break;
	case "004": appCoCdNm = "마트/편의" ;break;
	case "005": appCoCdNm = "뷰티/미용" ;break;
	case "006": appCoCdNm = "주거/생활" ;break;
	case "007": appCoCdNm = "교통/주유" ;break;
	case "008": appCoCdNm = "통신"; break;
	case "009": appCoCdNm = "쇼핑"; break;
	case "010": appCoCdNm = "온라인쇼핑"; break;
	case "011": appCoCdNm = "문화/예술"; break;
	case "012": appCoCdNm = "서점/문구"; break;
	case "013": appCoCdNm = "레저/스포츠" ;break;
	case "014": appCoCdNm = "의료/건강" ;break;
	case "015": appCoCdNm = "여행/숙박"; break;
	case "016": appCoCdNm = "교육"; break;
	case "017": appCoCdNm = "육아/아동"; break;
	case "018": appCoCdNm = "경조사/종교"; break;
	case "019": appCoCdNm = "금융"; break;
	case "020": appCoCdNm = "세금"; break;
	case "021": appCoCdNm = "자동차/관리"; break;
	case "999": appCoCdNm = "기타/미확인"; break;
	}			
	return appCoCdNm;
}


function to_date_format(date_str, gubun){
    var yyyyMMdd = String(date_str);
    var sYear = yyyyMMdd.substring(0,4);
    var sMonth = yyyyMMdd.substring(4,6);
    var sDate = yyyyMMdd.substring(6,8);

    return sYear + gubun + sMonth + gubun + sDate;
}

function instFormat(gubun){
	var instFormat ="";
	switch (gubun) {
		case '일시불'  : instFormat ='일시불'; break;		
		default   : instFormat =gubun+'개월'; break;
	}
	return instFormat;
}



function phoneFomatter(num,type){
    
    var formatNum = '';
    
    if(num.length==11){
        if(type==0){
            formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
        }else{
            formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
        }
    }else if(num.length==8){
        formatNum = num.replace(/(\d{4})(\d{4})/, '$1-$2');
    }else{
        if(num.indexOf('02')==0){
            if(type==0){
                formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-****-$3');
            }else{
                formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
            }
        }else{
            if(type==0){
                formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-***-$3');
            }else{
                formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
            }
        }
    }
    return formatNum;
   
}


function commonUserData(){
	
    $.ajax({
        type: "post",
        url : commonIp()+"/theReal/receipt/startUserData.do",
        data: {
        	CI  	: window.localStorage.getItem("CI")
        },
        success: fn_commonUserData,
        error: fn_whenError
	});	
}

function fn_commonUserData(resdata){
	 var str = "";
	 var str2 = "";
	var resultData = resdata.resultMap;
	var cardCashDiv = "";
	$(".pf_txt").empty();
	$(".left_menu").empty();
	resultCnt = resultData.length;
	if(resultData.length>0){
		for(var i=0; i<1; i++){
			str += '<table><tr><td><img src="../common/mobile.png" onclick="mainLoca()"></td> '
			str += '<td><h2>'+resultData[i].USER_NM+'</h2><br>';  //<span>'+resultData[i].EMAIL+'</span></td></tr></table>';
			if(resultData[i].PUSH_YN == "Y"){
				$("#alarmBtn").prop('checked', true) ;
			}else{
				$("#alarmBtn").prop('checked', false) ;
			}
			/*str += '<a class="myCoupon" id="myCoupon" href="#"><img src="../common/myCoupon.png"><p> 내 쿠폰함</p></a>';*/
			
			$("#barcodeName").empty();
			$("#barcodeName").append(resultData[i].USER_NM);
		}
	}		 
		 
	else{
		str += '<h2></h2>';
		str += '<h1></h1>';
		//str += '<p>쿠폰 <span>2</span>개 <span> &emsp; 포인트 </span><span>1721</span>p</p>';		
	}
/*	str += '<div class="alen_setting" id="settingBtn">';
	str += '<a href="javascript:void(0);"><i class="icon icon-form-settings"></i></a>';
	str += '</div>	';*/
	
	$(".pf_txt").append(str);
	
	str2 += '<ul>';
	str2 += '	<li id="leftMenuLatest">';
	str2 += '    <div class="leftm_box leftm01"></div>';
	str2 += '    <a href="javascript:void(0)">최근사용내역</a>';
	str2 += '    </li>';
/*	str2 += '	<li id="leftMenuHistory">';
	str2 += '    <div class="leftm_box leftm02"></div>';
	str2 += '    <a href="javascript:void(0)">영수증확인</a>';
	str2 += '    </li>';*/
	str2 += '	<li id="leftMenuComHistiry">';
	str2 += '    <div class="leftm_box leftm03"></div>';
	str2 += '    <a href="javascript:void(0)">월별내역</a>';
	str2 += '    </li>';
	str2 += '	<li id="leftMenuAffliate">';
	str2 += '    <div class="leftm_box leftm08" style="left:5px; top:3px"></div>';
	str2 += '    <a href="javascript:void(0)">가맹점</a>';
	str2 += '    </li>';
/*	str2 += '	<li id="leftMenuSmsHistory">';
	str2 += '    <div class="leftm_box leftm07"></div>';
	str2 += '    <a href="javascript:void(0)">모바일영수증</a>';
	str2 += '    </li>';*/
	str2 += '	<li id="leftMenuEvent">';
	str2 += '    <div class="leftm_box leftm04"></div>';
	str2 += '    <a href="javascript:void(0)">이벤트</a>';
	str2 += '    </li>';
/*	str2 += '	<li id="leftMenuCoupon">';
	str2 += '    <div class="leftm_box leftm09"></div>';
	str2 += '    <a href="javascript:void(0)">더리얼 쿠폰</a>';
	str2 += '    </li>';*/
	str2 += '	<li id="leftMenuNotice">';
	str2 += '    <div class="leftm_box leftm05"></div>';
	str2 += '    <a href="javascript:void(0)">공지사항</a>';
	str2 += '    </li>';
	str2 += '    </li>';
/*	str2 += '	<li id="leftMenuLogOut">';
	str2 += '    <div class="leftm_box leftm05"></div>';
	str2 += '    <a href="javascript:void(0)">로그아웃</a>';
	str2 += '    </li>';*/
	str2 += '	<li id="settingBtn">';
	str2 += '    <div class="leftm_box leftm06"></div>';
	str2 += '    <a href="javascript:void(0)">설정</a>';
	str2 += '    </li>';
	str2 += '</ul>';	
	$(".left_menu").append(str2);
	
}

/*function fn_commonUserData(resdata){
	 var str = "";
	 var str2 = "";
	var resultData = resdata.resultMap;
	var cardCashDiv = "";
	$(".pf_txt").empty();
	$(".left_menu").empty();
	resultCnt = resultData.length;
	if(resultData.length>0){
		for(var i=0; i<1; i++){
			str += '<table><tr><td><img src="../common/mobile.png" onclick="mainLoca()" style="width:30px"></td> ';
			str += '<td><h2>'+resultData[i].USER_NM+'</h2><br><span>'+resultData[i].EMAIL+'</span></td></tr></table>';
			str += '<td><h2>'+resultData[i].USER_NM+'님</h2></td>';
			str += '<td><img src="../common/setting_white.png" class="left-setting-icon"><img src="../common/x_white.png" class="left-close-icon" id="left-close-icon" onclick="javascript:close_left()"></td>';
			
			
			
			
			str += '</tr></table>';
			if(resultData[i].PUSH_YN == "Y"){
				$("#alarmBtn").prop('checked', true) ;
			}else{
				$("#alarmBtn").prop('checked', false) ;
			}
			str += '<a class="myCoupon" id="myCoupon" href="#"><img src="../common/myCoupon.png"><p> 내 쿠폰함</p></a>';
			
			$("#barcodeName").empty();
			$("#barcodeName").append(resultData[i].USER_NM);
		}
	}		 
	
	else{
		str += '<h2></h2>';
		str += '<h1></h1>';
		//str += '<p>쿠폰 <span>2</span>개 <span> &emsp; 포인트 </span><span>1721</span>p</p>';		
	}
	str += '<div class="alen_setting" id="settingBtn">';
	str += '<a href="javascript:void(0);"><i class="icon icon-form-settings"></i></a>';
	str += '</div>	';
	
	$(".pf_txt").append(str);
	
	str2 += '<ul>';
	str2 += '	<li>';
	str2 += '<a><img src="../common/mobile.png"></a>';
	str2 += '';
	str2 += '	</li>';
	str2 += '</ul>';
	str2 += '';
	str2 += '';
	str2 += '';
	str2 += '';
	str2 += '';
	str2 += '';
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	str2 += '<ul>';
	str2 += '	<li id="leftMenuLatest">';
	str2 += '    <div class="leftm_box leftm01"></div>';
	str2 += '    <a href="javascript:void(0)">최근사용내역</a>';
	str2 += '    </li>';
	str2 += '	<li id="leftMenuHistory">';
	str2 += '    <div class="leftm_box leftm02"></div>';
	str2 += '    <a href="javascript:void(0)">영수증확인</a>';
	str2 += '    </li>';  제거
	str2 += '	<li id="leftMenuComHistiry">';
	str2 += '    <div class="leftm_box leftm03"></div>';
	str2 += '    <a href="javascript:void(0)">월별내역</a>';
	str2 += '    </li>';
	str2 += '	<li id="leftMenuAffliate">';
	str2 += '    <div class="leftm_box leftm08" style="left:5px; top:3px"></div>';
	str2 += '    <a href="javascript:void(0)">가맹점</a>';
	str2 += '    </li>';
	str2 += '	<li id="leftMenuSmsHistory">';
	str2 += '    <div class="leftm_box leftm07"></div>';
	str2 += '    <a href="javascript:void(0)">모바일영수증</a>';
	str2 += '    </li>'; 제거  
	str2 += '	<li id="leftMenuEvent">';
	str2 += '    <div class="leftm_box leftm04"></div>';
	str2 += '    <a href="javascript:void(0)">이벤트</a>';
	str2 += '    </li>';
	str2 += '	<li id="leftMenuCoupon">';
	str2 += '    <div class="leftm_box leftm09"></div>';
	str2 += '    <a href="javascript:void(0)">더리얼 쿠폰</a>';
	str2 += '    </li>'; 제거 
	str2 += '	<li id="leftMenuNotice">';
	str2 += '    <div class="leftm_box leftm05"></div>';
	str2 += '    <a href="javascript:void(0)">공지사항</a>';
	str2 += '    </li>';
	str2 += '    </li>';
	str2 += '	<li id="leftMenuLogOut">';
	str2 += '    <div class="leftm_box leftm05"></div>';
	str2 += '    <a href="javascript:void(0)">로그아웃</a>';
	str2 += '    </li>'; 제거 
	str2 += '	<li id="settingBtn">';
	str2 += '    <div class="leftm_box leftm06"></div>';
	str2 += '    <a href="javascript:void(0)">설정</a>';
	str2 += '    </li>';
	str2 += '</ul>';	
	
	str2 += '';
	str2 += '';
	str2 += '';
	str2 += '';
	str2 += '';
	str2 += '';
	str2 += '';
	
	
	
	
	
	
	
	$(".left_menu").append(str2);
	
}*/

function fn_whenError(){
	//alert("실패.??");
}

function mainLoca(){
	location.href='index.html?CI='+CI+'&telNo='+request.getParameter('telNo');
}

var Request = function()
{
    this.getParameter = function( name )
    {
        var rtnval = '';
        var nowAddress = unescape(location.href);
        var parameters = (nowAddress.slice(nowAddress.indexOf('?')+1,nowAddress.length)).split('&');
        for(var i = 0 ; i < parameters.length ; i++)
        {
            var varName = parameters[i].split('=')[0];
            if(varName.toUpperCase() == name.toUpperCase())
            {
                rtnval = parameters[i].split('=')[1];
                break;
            }
        }
        return rtnval;
    }
}


function category(divCd){
	var div
	
	switch (divCd) {
	case "000": div = "미확인"; break;
	case "001": div = "외식/식사" ;break;
	case "002": div = "카페/베이커리" ;break;
	case "003": div = "술/유흥" ;break;
	case "004": div = "마트/편의" ;break;
	case "005": div = "뷰티/미용" ;break;
	case "006": div = "주거/생활" ;break;
	case "007": div = "교통/주유" ;break;
	case "008": div = "통신"; break;
	case "009": div = "쇼핑"; break;
	case "010": div = "온라인쇼핑"; break;
	case "011": div = "문화/예술"; break;
	case "012": div = "서점/문구"; break;
	case "013": div = "레저/스포츠" ;break;
	case "014": div = "의료/건강" ;break;
	case "015": div = "여행/숙박"; break;
	case "016": div = "교육"; break;
	case "017": div = "육아/아동"; break;
	case "018": div = "경조사/종교"; break;
	case "019": div = "금융"; break;
	case "020": div = "세금"; break;
	case "021": div = "자동차/관리"; break;
	case "999": div = "기타/미확인";break;
	default:
		break;
	}
	
	
	
}


function close_left(){
	$(".panel-overlay").trigger("click"); 	
	$(".panel-overlay").trigger("click"); 	
}



