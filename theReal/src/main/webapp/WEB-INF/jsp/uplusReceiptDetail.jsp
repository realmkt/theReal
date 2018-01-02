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

   
    
</head>

<body>





    
    
    <div class="views">
		<div class="view view-main">
        


<style type="text/css">
.page { background:#333333 !important; overflow:visible !important; }
.page-content { padding-top:0 !important; max-width: 450px; margin: 0 auto;}

.latest_contitle { color:#747474; background:#0d0d0d; }
.latest_contitle h2 { padding:15px 15px; font-size:1em; font-weight:400; color:#fff; line-height:25px; }
.latest_contitle h2 span { float:right; color:#ddd; border:1px solid #666; border-radius:8px; font-size:0.7em; padding:0 5px; margin-left:5px; line-height:21px; }
.latest_contitle h2 span.active { border:1px solid #539bff; color:#539bff; }

.latest_coninfo { padding:8px 15px; color:#bbb; background:#222; font-size:0.7em; text-align:left; border-bottom:1px solid #333;}
.latest_coninfo .latest_allsms { width:50%; float:left; background:url(common/sms.png) no-repeat left; line-height:20px; padding-left:25px;}
.latest_coninfo .latest_allrec { width:50%; float:right; background:url(common/receipt.png) no-repeat left; line-height:20px; padding-left:25px;}
.latest_coninfo > div span { color:#e5e5e5; }


.rec_top { position:relative; width:100%; height:40px; border-bottom:1px solid #e5e5e5;  }
.rec_top .rec_arrow { width:30px; height:40px; position:absolute; z-index:20; top:0px; }
.rec_top .left_arrow { background:url(common/left.png) no-repeat; left:20px; }
.rec_top .right_arrow { background:url(common/right.png) no-repeat; right:20px; }

.rtpage_box { background:#333333; margin-bottom:20px; font-size:12px; line-height:1.2em; }
.rtpage_inbox { background:#fff; padding:15px 15px; border-radius:4px; margin: 20px }
.rtpage_holl { padding:5px 5px 15px 5px; height:35px; }
.rtpage_holl div { width:6%; height:100%; margin:0 2%; border-radius:30px; border:1px solid #e5e5e5; float:left; background:#f2f2f2; }

.rt_title { padding:40px 0 30px 0; }
.rt_title h2 { text-align:center; font-size:2em; font-weight:700; color:#000; clear:both; padding:5px 0 15px 0; }
.rt_title p { text-align:center; font-size:1em; color:#000; clear:both; }

.rt_txt01 { text-align:left; font-size:1em; color:#666; clear:both; padding:0px 10px; }
.rt_txt01 span { text-align:right; float:right; }

.rt_tax { text-align:left; font-size:1.3em; font-weight:500; color:#333; clear:both; padding:10px 10px; border-top:1px dashed #aaa;border-bottom:1px dashed #aaa; margin-top:10px;}
.rt_tax span { text-align:right; float:right; }

.rt_total { text-align:left; font-size:1.5em; font-weight:700; color:#000; clear:both; padding:20px 10px; border-bottom:1px dashed #aaa;  }
.rt_total span { text-align:right; float:right; }

.rt_orderno { border-bottom:1px dashed #aaa; border-top:1px dashed #aaa; padding:20px 0; margin-top:20px; }
.rt_orderno p { text-align:center; font-size:1.2em; color:#666; clear:both; }
.rt_orderno h2 { text-align:center; font-size:1.8em; color:#000; clear:both; padding:5px 0; }

.rt_table { border-top:1px solid #ddd; margin:20px 5px 0px 5px;  }
.rt_table .rt_tabletitle { border-bottom:1px solid #ddd; background:#fff;}
.rt_table li { float:left; padding:5px 0; text-align:center; color:#333; }
.rt_table li.rt_mlat01 { width:45%; }
.rt_table li.rt_mlat02 { width:20%; }
.rt_table li.rt_mlat03 { width:10%; }
.rt_table li.rt_mlat04 { width:25%; text-align:right; padding-right:5px; }

.rt_etc { text-align:left; font-size:1.3em; font-weight:700; color:#333; clear:both; padding:10px 10px; border-bottom:1px dashed #aaa; }
.rt_etc span { text-align:right; float:right; }

.rt_copy { text-align:center; font-size:0.7em; margin-bottom: 30px; }

.rt_btn { margin-bottom:30px; text-align:center;}
.rt_email { border-radius:4px; background:#222; color:#fff; text-align:center; padding:8px 10px; width:35%; font-size:13px; margin:0 auto; }

.rt_card{  padding: 10px; color: #232323; font-size: 12px; border-bottom: 1px dashed #aaaaaa; }

.exit_a img{width: 30px;position: absolute;right: 35px;}
</style>
			<!-- <div class="rec_top">
            
                <div class="rec_arrow left_arrow"></div>
                
                <div class="rec_arrow right_arrow"></div>
            
            </div> -->

			<div class="pages navbar-through toolbar-through">
                <div data-page="index" class="page">
                    <div class="page-content">

					<div class="receipt_con">
                    
						<div class="rtpage_box">
                        	<div class="rtpage_inbox">
                            
                            	<!-- <div class="rtpage_holl">
                                	<div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <hr>
                                </div> -->
                                <a class="exit_a" href="javascript:exit()">
                                	<img alt="exit" src="../uplusCode/lg_img/exit.png" style="width: 15px"> 
                                </a>
                                
                                
                                <div class="rt_title">
                                	<h2>${shopInfo.SHOP_NAME }</h2>
                                </div>
                                
                                
								<div class="rt_txt01">${shopInfo.SHOP_NAME }<span>${salesDate }</span></div>
								<div class="rt_txt01">사업자번호:${shopInfo.SHOP_BIZNO }</div>
								<div class="rt_txt01">대표:${shopInfo.SHOP_CEO }</div>
                                <div class="rt_txt01">전화:${shopInfo.SHOP_TEL_NUM }</div>
								<div class="rt_txt01">주소:${shopInfo.SHOP_ADDR}</div>
                                
                               <!--  <div class="rt_orderno">
                                	<p>주문번호</p>
                                	<h2>1225911584-11584</h2>
                                </div> -->
                                
                                
                                <div class="rt_table">
                                	<ul class="rt_tabletitle">
                                      <li class="rt_mlat01">상품명</li>
                                      <li class="rt_mlat02">단가</li>
                                      <li class="rt_mlat03">수량</li>
                                      <li class="rt_mlat04">금액</li>
                                      <hr>
                                    </ul>
                                    
                                    <c:forEach var="list" items="${detailMap.resultMap}" varStatus="status">
                                    	<ul>
                                     		<li class="rt_mlat01">${list.SALES_PNAME }</li>
                                      		<li class="rt_mlat02"><fmt:formatNumber value="${list.SALES_SL_AMT/list.SALES_QTY}"  groupingUsed="true"></fmt:formatNumber> </li>
                                      		<li class="rt_mlat03">${list.SALES_QTY}</li>
                                      		<li class="rt_mlat04"><fmt:formatNumber value="${list.SALES_SL_AMT}" groupingUsed="true"/></li>
                                      		<hr>
                                    	</ul>
                                    </c:forEach>
                                </div>
                                
								
                                <div class="rt_tax">주문합계<span><fmt:formatNumber value="${shopInfo.SALES_PAID_AMT }" groupingUsed="true"/></span><br><br>
                               						 공급가금액<span><fmt:formatNumber value="${shopInfo.SALES_SUM_FP_AMT }" groupingUsed="true"/></span><br><br>
                               						 부가세<span><fmt:formatNumber value="${shopInfo.SALES_SUM_TAX_AMT }" groupingUsed="true"/></span></div>
                                
                                <div class="rt_total">Total<span><fmt:formatNumber value="${shopInfo.SALES_PAID_AMT }" groupingUsed="true"/></span></div>
                                
                                <!-- <div class="rt_etc">스타벅스카드<span>500</span></div> -->
                                
                                <c:if test="${shopInfo.CARD_ICOM != ''}">
                                	<div class="rt_card">
                               			<div>거래종류 : <span>카드거래</span> </div>
                                		<div>카드승인 : <span>
											<c:if test="${shopInfo.SALES_TYPE == 'RCP01' }">
											승인
											</c:if>
											<c:if test="${shopInfo.SALES_TYPE == 'RCP02' }">
											취소
											</c:if>
											<c:if test="${shopInfo.SALES_TYPE == 'RCP99' }">
											거래실패
											</c:if>
										</span> </div>
										<div>카드번호 : 
										<span>
											<c:set var="cardNo" value="${shopInfo.CARD_NO }"/>
											${fn:substring(cardNo,0,4) }-${fn:substring(cardNo,4,6) }**-****-${fn:substring(cardNo,12,16) }
										</span></div>
										<div>승인번호 : <span>${shopInfo.CARD_APP_NO }</span></div>
                                		<div>카드종류 : <span>${shopInfo.CARD_ICOM }</span> </div>
                                		<div>할부정보 : <span>
                                		
                                		<c:choose>
                                		
                                		<c:when test="${shopInfo.CARD_INSTALLMENT == '0'}">
                                			일시불
                                		</c:when>
                                		<c:otherwise>
                                			${shopInfo.CARD_INSTALLMENT } 개월
                                		</c:otherwise>
                                		</c:choose>
                                		</span> </div>
                                		<div>거래일시 : <span>${salesDate}</span> </div>
                                	</div>
                                </c:if>
                                <c:if test="${shopInfo.CARD_ICOM == ''}">
                                	<div class="rt_card">
                               			<div>거래종류 : <span>현금거래</span> </div>
                                		<div>거래일시 : <span>${salesDate}</span> </div>
                                	</div>
                                </c:if>
                                
                                
                                
                                
                                <c:if test="${(shopInfo.POINT_CARD != '' )}">
                                	<div  class="rt_card">
                                	<c:if test="${shopInfo.GET_POINT == '0'}">
                                		<div>포인트카드 : <span>${shopInfo.POINT_CARD }</span> </div>
                                		<div>포인트적립/사용 : <span>포인트적립</span> </div>
                                		<div>사용포인트 : <span>1000</span> </div>
                                		<div>남은포인트 : <span>5000</span> </div>
                                	</c:if>
                                	<c:if test="${shopInfo.GET_POINT != '0'}">
                                		<div>포인트카드 : <span>${shopInfo.POINT_CARD }</span> </div>
                                		<div>포인트적립/사용 : <span>포인트적립</span> </div>
                                		<div>적립포인트 : <span>50</span> </div>
                                		<div>남은포인트 : <span>5000</span> </div>
                                	</c:if>
                            	</div>
                                </c:if>
                                
                                
                                
                                <!-- <div class="rt_etc">제휴할인(KT)<span>1,500</span></div> -->
                                
                                <!-- <div class="rt_etc">적립포인트<span>300포인트</span></div> -->
                                
                                
                                
                                
                                <div class="rt_copy">
                                	본 영수증은 거래의 참고용으로 사용하시기 바랍니다.
                                </div>
                            </div>
                        </div>
                        
                        <div style="height:50px;"></div>
                    
                    </div>
                    </div>
                </div>
			</div>
		</div>
    </div>

<script type="text/javascript">
function exit(){
	window.history.back();
}
</script>

</body>
</html>
