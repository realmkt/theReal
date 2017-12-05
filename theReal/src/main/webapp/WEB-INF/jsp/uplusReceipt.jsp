<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet" media="all" href="../uplusCode/code/layout.css" />
<link rel="stylesheet" media="all" href="../uplusCode/code/common.css" />
<link rel="stylesheet" href="../uplusCode/code/fm.scrollator.jquery.css" />
<link rel="stylesheet" href="../uplusCode/css/my-app.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Uplus 전자영수증</title>


<style type="text/css">
/* 폰트 */
@import url( "../uplusCode/lg_img/dsf.css" );
body { font-family:'Droid Sans Fallback', sans-serif; color:#999; font-size:1em; letter-spacing:-0.02em; line-height:1; }
a { font-family:'Droid Sans Fallback', sans-serif; color:#999; text-decoration:none; }
/* lguplus style */
.navbar-through .page-content, .navbar-fixed .page-content { padding-top:0 !important;}

.latest_contitle { color:#747474; background:#0d0d0d; }
.latest_contitle h2 { padding:15px 15px; font-size:1em; font-weight:400; color:#fff; line-height:25px; }
.latest_contitle h2 span { float:right; color:#999; border:2px solid #333; border-radius:8px; font-size:0.7em; padding:0 5px; margin-left:5px; line-height:21px; }
.latest_contitle h2 span.active { border:2px solid #e100ff; color:#fff; }

.latest_coninfo { padding:8px 15px; color:#bbb; background:#222; font-size:0.7em; text-align:left; border-bottom:1px solid #333;}
.latest_coninfo .latest_allsms { width:50%; float:left; background:url(../uplusCode/common/sms.png) no-repeat left; line-height:20px; padding-left:25px;}
.latest_coninfo .latest_allrec { width:35%; float:right; background:url(../uplusCode/common/receipt.png) no-repeat left; line-height:20px; padding-left:25px;}
.latest_coninfo > div span { color:#e5e5e5; }

.latest_conser { margin:3% 2% 0; background:#fff; text-align:center; }
.latest_conser input[type=radio] { display:none; margin:10px; }
.latest_conser input[type=radio] + label { float:left; padding:7px 12px; width:25%; background:#fff; font-size:12px; border-right:1px solid #e5e5e5;}
.latest_conser input[type=radio] + label:last-child { border-right:0; }
.latest_conser input[type=radio]:checked + label { background:#e100ff; color:#fff; }

.latest_conlist { margin:3% 2%; background:#fff; text-align:center; }
.latest_conlist ul { padding:15px 10px; position:relative; border-bottom:1px solid #e5e5e5;}
.latest_conlist li { float:left; width:60%; text-align:left; }
.latest_conlist li h2 { font-size:1em; font-weight:400; color:#000; padding-bottom:3px; }
.latest_conlist li p { font-size:0.8em; font-weight:400; color:#555; padding-bottom:20px; }
.latest_conlist li h3 { font-size:0.8em; font-weight:400; color:#555; padding-left:27px; line-height:16px; }
.latest_conlist li h3 span { background:#f2f2f2; border:1px solid #ddd; border-radius:3px; padding:2px 5px; font-size:0.85em; color:#999; }
.latest_conlist li h3.mal_credit { background:url(../uplusCode/lg_img/credit.gif) no-repeat left; }
.latest_conlist li h3.mal_cash { background:url(../uplusCode/lg_img/cash.gif) no-repeat left; }
.latest_conlist li.mal_price { width:40%; text-align:right; font-size:0.8em; font-weight:400; color:#555;  position:absolute; top:40%; right:10px; }
.latest_conlist li.mal_price span { font-size:1.4em; font-weight:700; color:#000; }
.mal_more { background:#f2f2f2; color:#666; font-size:0.8em; padding:10px 0; }

.cpop_btn { font-size:3vmin; padding:0.3em 1.5em; background-color:#222; color:#e5e5e5; text-decoration:none; display:inline; border-radius:4px; -webkit-transition: background-color 1s ease; -moz-transition: background-color 1s ease; transition: background-color 1s ease; }
.cpop_btn:hover { -webkit-transition:background-color 1s ease; -moz-transition:background-color 1s ease; transition:background-color 1s ease; }
.cpop_btn_small { padding:.5em 1.5em; font-size:0.8em; }

.modal_box { display: none; position:fixed; z-index: 1000; background: white; width:90%; border-bottom: 1px solid #aaa; border-radius: 4px; box-shadow: 0 3px 9px rgba(0, 0, 0, 0.1); border: 1px solid rgba(0, 0, 0, 0.1); background-clip: padding-box; }
.modal_box .modal_body { padding: 1em 1em; }
.modal_box footer, .modal_box .modal-footer { padding: 1em; border-top: 1px solid #ddd; background: rgba(0, 0, 0, 0.02); text-align: center; }

.alen_modal_overlay { opacity:0; filter:alpha(opacity=1); position: absolute; top:0; left:0; z-index:900; width:100%; height:100%; background:rgba(0, 0, 0, 1) !important;}

.ser_datebox { margin:0; background:#fff; text-align:center; border:1px solid #ddd; margin-top:2%;  }
.ser_datebox input[type=date] { width:45%; height:40px; line-height:40px; border:none; box-sizing:border-box; text-align:center;  }

.ser_2box { margin:0; background:#fff; text-align:center; border:1px solid #ddd; margin-top:2%;  }
.ser_2box input[type=radio] { display:none; margin:10px; }
.ser_2box input[type=radio] + label { float:left; padding:10px 0; width:50%; background:#fff; font-size:12px; border-right:1px solid #ddd;}
.ser_2box input[type=radio] + label.slast_radio { border-right:0; }
.ser_2box input[type=radio]:checked + label { background:#f2f2f2; color:#000; }

.ser_4box { margin:0; background:#fff; text-align:center; border:1px solid #ddd; margin-top:2%;  }
.ser_4box input[type=radio] { display:none; margin:10px; }
.ser_4box input[type=radio] + label { float:left; padding:10px 0; width:25%; background:#fff; font-size:12px; border-right:1px solid #ddd;}
.ser_4box input[type=radio] + label.slast_radio { border-right:0; }
.ser_4box input[type=radio]:checked + label { background:#f2f2f2; color:#000; }

.recdetail { max-width:500px; padding:1%; height:90%; top:5% !important; }
.recdetail .modal_body { height:100%; padding:0; }
.close_icon { position:absolute; background-image:url(../uplusCode/common/close.png); background-repeat:no-repeat; background-size:cover; width:30px; height:30px; left:50%; top:1%; margin-left:-15px; }

.moreBtn { height: 41px;  padding: 14px;  background-color: #272727; color: #f2f2f2; position: relative; top: -12px; margin: 8px;text-align: center}
.disnone {display: none}
</style>
</head>
<body>
 <!-- Status bar overlay for fullscreen mode-->
    <div class="statusbar-overlay"></div>


    <!-- Panels overlay-->
    <div class="panel-overlay"></div>


   
    
    <div class="views">
		<div class="view view-main">

<div class="pages navbar-through toolbar-through">
                <div data-page="index" class="page">
                    <div class="page-content">

                   
                    <div class="latest_contitle">
                    	<h2>최근사용내역<span class="active" id="serbtn">전자영수증</span>
                    	<!-- <span>SMS</span>
                    	<span >전체</span> -->
                    	</h2>
                    </div>
                    <div class="latest_coninfo" id="latest_coninfo">
                    	<!-- <div class="latest_allsms">7건 <span>175.700원</span></div> -->
                    	<div class="latest_allrec">전자영수증 <span>${receiptCnt}건</span> </div>
                        <hr>
                    </div>
                    
                    <div class="latest_conser alen_outbox">
                    
                    <input type="radio" id="latest01" name="latestsbox" value="latest" checked>
                    <label for="latest01">최근</label>
                    
                    <input type="radio" id="latest02" name="latestsbox" value="week">
                    <label for="latest02">일주일</label>
                    
                    <input type="radio" id="latest03" name="latestsbox" value="month">
                    <label for="latest03">한달</label>
                    
                    <input type="radio" id="latest04" name="latestsbox" value="detail">
                    <label for="latest04" data-modal-id="ser_box" >상세</label>
                    
                    <hr>
                    </div>
                    <input type="hidden" value="${userKey }" id="userKey">
                    <input type="hidden" value="${telNo }" id="telNo">
                    <input type="hidden" value="${maxSize}" id="maxSize">
                    <div class="latest_conlist alen_outbox" id="latest_conlist">
                    
                    	<c:forEach var="map" items="${resultMap.resultMap}" varStatus="status">
                    	<ul onclick="detailReceipt(${map.SALES_BARCODE},'${map.SHOP_BIZNO}')">
                        	<li>
                            <h2>${map.SHOP_NAME }</h2>
                            <p>${fn:substring(map.SALES_DATE,0,4)}-${fn:substring(map.SALES_DATE,4,6)}-${fn:substring(map.SALES_DATE,6,8)}&nbsp;${fn:substring(map.SALES_DATE,8,10)}:${fn:substring(map.SALES_DATE,10,12)}:${fn:substring(map.SALES_DATE,12,14)}  </p>
                            
                            <c:if test="${map.CARD_ICOM != '' }">
                            <h3 class="mal_credit">${map.CARD_ICOM} 
                            	<span>
                            		<c:if test="${map.SMS_DIV =='00' }"> 전자영수증</c:if>
                            		<c:if test="${map.SMS_DIV =='01' }"> SMS	</c:if>
                            	</span></h3>
                            </c:if>
                            
                            <c:if test="${map.CARD_ICOM == '' }">
                            <h3 class="mal_cash">현금 
                            	<span>
                            		<c:if test="${map.SMS_DIV =='00' }"> 전자영수증</c:if>
                            		<c:if test="${map.SMS_DIV =='01' }"> SMS	</c:if>
                            	</span></h3>
                            </c:if>
                            </li>
                            
                            <li class="mal_price">
                            <span><fmt:formatNumber value="${map.TOTAL_AM}" groupingUsed="true"/></span> 원
                            </li>
                            <hr>
                            </ul>
                            </c:forEach>	
                            
					
                    </div>
                    		<c:choose>
                            	<c:when test="${receiptCnt == 10 && maxSize != 10}">
                            		<div class="moreBtn" id="moreBtn">더보기</div>
                            	</c:when>
                            	<c:otherwise>
                            		<div class="moreBtn disnone" id="moreBtn">더보기</div>
                            	</c:otherwise>
                            </c:choose>
                    
                    </div>
                </div>
                


<div id="ser_box" class="modal_box">
    
    <div class="modal_body">
    
        <div class="ser_datebox">
        <input type="date" id="startDate" onchange="startDateCh()"> - <input type="date" id="endDate" onchange="startDateCh()">
        </div>
	
        <div class="ser_4box">
        <input type="radio" id="pays01" name="paysort" value="all" checked>
        <label for="pays01">전체</label>
        
        <input type="radio" id="pays02" name="paysort" value="card">
        <label for="pays02">카드</label>
        
        <input type="radio" id="pays03" name="paysort" value="cash">
        <label for="pays03">현금</label>
        
        <input type="radio" id="pays04" name="paysort" value="etc">
        <label for="pays04" class="slast_radio">기타</label>
        
        <hr>
        </div>
        
        <div class="ser_4box">
        <input type="radio" id="array01" name="arraysort" value="new" checked>
        <label for="array01">최신</label>
        
        <input type="radio" id="array02" name="arraysort" value="past">
        <label for="array02">과거</label>
        
        <input type="radio" id="array03" name="arraysort" value="highp">
        <label for="array03">높은금액</label>
        
        <input type="radio" id="array04" name="arraysort" value="lowp">
        <label for="array04" class="slast_radio">낮은금액</label>
        
        <hr>
        </div>
    
    </div>
    
	<footer> 
		<a href="#" class="cpop_btn cpop_btn_small" id="detailBtn">조회</a> 
	    <a href="#" class="cpop_btn cpop_btn_small js-modal-close alen_arrow">취소</a> 
	</footer>
</div>

<!-- <div id="receip_box" class="modal_box recdetail">
    
    <div class="close_icon js-modal-close"  id="modal_body" ><a href="#" class="js-modal-close"></a></div>
    
    <div class="modal_body">
		<iframe src="lg_receipt_pop.html" frameborder="0" scrolling="no" width="100%" height="100%" style="padding:0; margin:0;"></iframe>
    </div>

</div> -->

               <!--  
                <div data-page="agree01" class="page cached">
                    <div class="page-content agree_acon">
                        <div class="content-block">
						<iframe src="agree01.html" frameborder="0" width="100%" height="100%"></iframe>
                        </div>
                    </div>
                </div>
                
                
                <div data-page="agree03" class="page cached">
                    <div class="page-content agree_acon">
                        <div class="content-block">
						<iframe src="agree03.html" frameborder="0" width="100%" height="100%"></iframe>
                        </div>
                    </div>
                </div> -->
                
			</div>

		</div>
    </div>








<script type="text/javascript" src="js/framework7.js"></script>

<script type="text/javascript" src="js/my-app.js"></script>




<script type="text/javascript">
function numCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}  



//날짜 끝

var maxSize = 0;
var receiptCnt = 10;
var userKey   = $("#userKey").val();
var telNo     = $("#telNo").val();
var startDate = "";
var endDate   = "";
var payDivCd  = "ALL";
var orderByCd = "NEW";
var dateDivCd = "";
var dateSrchCd = "";

function allClear(){
	startDate = "";
	endDate   = "";
	payDivCd  = "";
	orderByCd = "";
	dateDivCd = "";
	dateSrchCd = "";
}



$(document).ready(function(){
	$(".moreBtn").click(function(){
		receiptCnt += 10;
		getReceiptData(receiptCnt,startDate,endDate,payDivCd,orderByCd,dateDivCd,dateSrchCd);
	});
	$("#latest01").click(function(){
		receiptCnt = 10;
		allClear();
		dateDivCd = "ALL";
		getReceiptData(receiptCnt,startDate,endDate,payDivCd,orderByCd,dateDivCd,dateSrchCd);
	});	
	$("#latest02").click(function(){
		receiptCnt = 10;
		allClear();
		dateDivCd = "WEEK";
		getReceiptData(receiptCnt,startDate,endDate,payDivCd,orderByCd,dateDivCd,dateSrchCd);
	});	
	$("#latest03").click(function(){
		receiptCnt = 10;
		allClear();
		dateDivCd = "MONTH";
		getReceiptData(receiptCnt,startDate,endDate,payDivCd,orderByCd,dateDivCd,dateSrchCd);
	});	
	$("#pays01").click(function(){
		payDivCd = "ALL";
	});
	$("#pays02").click(function(){
		payDivCd = "CARD";
	});
	$("#pays03").click(function(){
		payDivCd = "CASH";
	});
	$("#pays04").click(function(){
		payDivCd = "ETC";
	});
	$("#array01").click(function(){
		orderByCd = "NEW";
	});
	$("#array02").click(function(){
		orderByCd = "D1";
	});
	$("#array03").click(function(){
		orderByCd = "A2";
	});
	$("#array04").click(function(){
		orderByCd = "A1";
	});
	
	$("#detailBtn").click(function(){
		startDate = $("#startDate").val();
		endDate = $("#endDate").val();
		receiptCnt = 10;
		
		dateSrchCd = "";
		var startDate2 = startDate.substring(0,4) + startDate.substring(5,7) + startDate.substring(8,10); 
		var endDate2 = endDate.substring(0,4) + endDate.substring(5,7) + endDate.substring(8,10); 
		
		if(startDate == ''){
			alert("시작날짜를 입력하세요");
			return false;
		}
		
		if(endDate == ''){
			alert("종료날짜를 입력하세요");
			return false;
		}
		
		if(startDate2 > endDate2){
			alert("종료날짜가 시작날짜보다 이전입니다.");
			return false;
		}
		$("#latest04").prop("checked",true);
		dateSrchCd = "SRCHY";
		
		$('#ser_box').hide();
		$('#modal_body').hide();
		$(".modal_box, .alen_modal_overlay").fadeOut(300, function() {
			$(".alen_modal_overlay").remove();
		});
			 
		
		getReceiptData(receiptCnt,startDate,endDate,payDivCd,orderByCd,dateDivCd,dateSrchCd);
	});
});




	
	
	
	
	
function getReceiptData(receiptCnt,startDate,endDate,payDivCd,orderByCd,dateDivCd,dateSrchCh){
	maxSize = $("#maxSize").val();
	$.ajax({
		type : "post",
		url : "https://www.realmktshop.kr/theReal/receipt/uplusReceiptReload.do",
		data : {
			telNo : telNo,
			userKey : userKey,
			receiptCnt : receiptCnt,
			startDate : startDate,
			endDate : endDate,
			payDivCd : payDivCd,
			orderByCd : orderByCd,
			dateDivCd : dateDivCd,
			dateSrchCd: dateSrchCd
		},
		success : fn_latestData
	})
}	
	
	
function fn_latestData(data){
	var res = data.resultMap;
	var str = "";
	var cnt = res.length;
	$("#latest_coninfo").empty();
	$("#latest_coninfo").append('<div class="latest_allrec">전자영수증 <span>'+cnt+'건</span> </div><hr>');
	if(cnt > 0){
		for (var i = 0; i < res.length; i++) {
			str += '<ul data-modal-id="receip_box" onclick="detailReceipt('+res[i].SALES_BARCODE+',\''+res[i].SHOP_BIZNO+'\')">';
			str += '	<li>';
			str += '		<h2>'+res[i].SHOP_NAME+'</h2>';
			var tempDate = res[i].SALES_DATE;
			tempDate = tempDate.substring(0,4)+"-"+tempDate.substring(4,6)+"-"+tempDate.substring(6,8)+" "+tempDate.substring(8,10)+":"+tempDate.substring(10,12)+":"+tempDate.substring(12);
			str += '		<p>'+ tempDate  +'</p>';
			if(res[i].CARD_ICOM != ''){
			str += '<h3 class="mal_credit">'+res[i].CARD_ICOM+'&nbsp;<span> ';
				if(res[i].SMS_DIV == '00'){ str += '전자영수증'; }else 
				if(res[i].SMS_DIV == '01'){ str += 'SMS'; }
				str += '</span></h3>';
			}else if(res[i].CARD_ICOM == ''){
			str += '<h3 class="mal_cash">현금&nbsp;<span>';
				if(res[i].SMS_DIV == '00'){ str += '전자영수증'; }else 
				if(res[i].SMS_DIV == '01'){ str += 'SMS'; }
				str += '</span></h3>';
			}
			str += '</li>';
			str += '<li class="mal_price">';
			str += '<span>'+numCommas(res[i].TOTAL_AM)+'</span> 원';
			str += '</li>';
			str += '<hr></ul>';
		}
		if(cnt % 10 == 0 && maxSize != cnt){
			$("#moreBtn").removeClass("disnone");
		}else{
			$("#moreBtn").addClass("disnone");
		}
	}
	
	$("#latest_conlist").empty();
	$("#latest_conlist").append(str);
}
	
	function detailReceipt(barcode, bizNo){
		location.href = "https://www.realmktshop.kr/theReal/receipt/uplusReceiptDetail.do?telNo="+telNo+"&userKey="+userKey+"&barcode="+barcode+"&bizNo="+bizNo;
	  //location.href = "http://117.52.97.40/theReal/receipt/uplusReceipt.do?telNo=01074505585&userKey=U20171113125226000000"; 
		//uplusReceiptDetail
	}
	
	
$(function(){

	var appendthis =  ("<div class='alen_modal_overlay js-modal-close'></div>");

		$('[data-modal-id]').click(function(e) {
			e.preventDefault();
	    $(".pages").append(appendthis);
	    $(".alen_modal_overlay").fadeTo(300, 0.7);
	    //$(".js-modalbox").fadeIn(500);
			var modalBox = $(this).attr('data-modal-id');
			$('#'+modalBox).fadeIn($(this).data());
		});  
	  
	  
	$(".js-modal-close, .alen_modal_overlay").click(function() {
	    $(".modal_box, .alen_modal_overlay").fadeOut(300, function() {
	        $(".alen_modal_overlay").remove();
	    });
	 
	});

	$(".alen_arrow").click(function() {
		$('input[name="latestsbox"]:radio[value="detail"]').prop('checked',true);
	});
	 
	$(window).resize(function() {
	    $(".modal_box").css({
	        top: ($(window).height() - $(".modal_box").outerHeight()) / 2,
	        left: ($(window).width() - $(".modal_box").outerWidth()) / 2
	    });
	});
	 
	$(window).resize();
	 
	});
</script>


</body>
</html>