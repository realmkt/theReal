package first.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;



public class LpointTest {
	

//----------------------------------------------
// 개방형 회원인증(요청)
//----------------------------------------------

	@SuppressWarnings("deprecation")
	public static String LpointO100(HttpServletRequest request, HttpServletResponse response, String json) throws IOException{
		System.out.println("■■■■시스템 호출 시작■■■■ ");
		
		int timeout = 10;
		String lpointResData = null;
		try {
		   /*URL 설정 */
		    HttpPost httpost = new HttpPost(new URI("https://op/devop.lpoint.com:8903/op"));
		    //httpost.addHeader("Content-Type", "application/json");
		   /*Time Out 시간설정 */
		    RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
		   .setConnectionRequestTimeout(timeout * 1000)
		   .setSocketTimeout(timeout * 1000).build(); 
			
		    CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
			System.out.println("■■■■연결성공■■■■ ");
		    RequestConfig.Builder requestBuilder = RequestConfig.custom();
		    HttpClientBuilder builder = HttpClientBuilder.create();	
		    builder.setDefaultRequestConfig(requestBuilder.build());
		    HttpClient client = builder.build();
		 
		    /*전문 데이타 셋팅*/
			String jsonData = "{";
			jsonData +=  "          \"control\" : ";													  /*컨트롤영역*/
			jsonData +=  "          { ";													
			jsonData +=  "                    flwNo : \"O110B20120160126000001\"";/*추적번호*/
			jsonData +=  "          }";
			jsonData +=  "          , wcc : \"3\"";			                      					 /*WCC(인증방식) / 1:MSR/2:IC/3:KEY IN*/
			jsonData +=  "          , aprAkMdDc : \"1\"";	                        			 /*승인요청방식구분코드/ 1:POS		*/
			jsonData +=  "          , cstDrmV : \"2\"";		                        		     /*고객식별 구분코드/ 1:카드번호 /2: 고객번호*/	
			jsonData +=  "          , cstDrmDc : \" \"";	                        				 /*★고객식별값/ 20자 (고객번호 지원받고 데이터 전송)*/
			jsonData +=  "          , copMcno : \" \"";		                       			     /*★개방형 제휴가맹점번호/10자(가맹점 번호 지원받고 데이터전송예정)*/
			jsonData +=  "          , posNo : \"\"";		                        				 /*★포스번호 8자(포스번호+점포코드)*/
			jsonData +=  "          , filler : \"2\"";		                        					 /*★filler ..?*/
			jsonData += "}";
		    System.out.println("■■JSON 데이터 셋팅 성공 ■■");
		    
		    /*JSON 데이터를 문자열 엔티티로..*/

		    StringEntity stringEntity = new StringEntity(jsonData,
		    													ContentType.create("application/json", "UTF-8"));
		    httpost.setEntity(stringEntity);
		    
		    /*전송*/
		    client.execute(httpost);
		    System.out.println("■■전송종료■■");
		    
		    /*응답데이터를 받기위한 서비스호출*/
		    
			String resData = "";
			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();
			
			
			//is = resEntity.getContent();
            			
			if(resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				System.out.println("■■L.POINT 응답데이터■■=="+resData);
			
			}else {
			System.out.println("응답없음 재적립 및 망취소요망");	
			}
			//return resData;
			
		    
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lpointResData ;
	}
	
	
	
	//----------------------------------------------
	// 휴대폰 회원정보요약 조회(요청)
	//----------------------------------------------

	public static String LpointO110(HttpServletRequest request, HttpServletResponse response, String json) throws IOException{
		System.out.println("■■■■시스템 호출 시작■■■■ ");
		
		int timeout = 10;
		String lpointResData = null;
		try {
		   /*URL 설정 */
		    HttpPost httpost = new HttpPost(new URI("https://op/devop.lpoint.com:8903/op"));
		    //httpost.addHeader("Content-Type", "application/json");
		   /*Time Out 시간설정 */
		    RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
		   .setConnectionRequestTimeout(timeout * 1000)
		   .setSocketTimeout(timeout * 1000).build(); 
			
		    CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
			System.out.println("■■■■연결성공■■■■ ");
		    RequestConfig.Builder requestBuilder = RequestConfig.custom();
		    HttpClientBuilder builder = HttpClientBuilder.create();
		    builder.setDefaultRequestConfig(requestBuilder.build());
		    HttpClient client = builder.build();
		 
		    /*전문 데이타 셋팅*/
			String jsonData = "{";
			jsonData +=  "          \"control\" : ";													  /*컨트롤영역*/
			jsonData +=  "          { ";													
			jsonData +=  "                    flwNo : \"O110B20120160126000001\"";/*추적번호*/
			jsonData +=  "          }";
			jsonData +=  "          , mblNo : \"010-1234-1234\"";       					 /*휴대전화번호-포함*/
			jsonData +=  "          , copMcno : \" \"";		                       			     /*★개방형 제휴가맹점번호/10자(가맹점 번호 지원받고 데이터전송예정)*/
			jsonData +=  "          , filler : \"\"";		                        					 /*filler .. 공백 데이터를 임의로 넣을수있는 공간*/
			jsonData += "}";
		    System.out.println("■■JSON 데이터 셋팅 성공 ■■");
		    
		    /*JSON 데이터를 문자열 엔티티로..*/

		    StringEntity stringEntity = new StringEntity(jsonData,
		    													ContentType.create("application/json", "UTF-8"));
		    httpost.setEntity(stringEntity);
		    
		    /*전송*/
		    client.execute(httpost);
		    System.out.println("■■전송종료■■");
		    
		    /*응답데이터를 받기위한 서비스호출*/
		    
			String resData = "";
			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();
			
			
			//is = resEntity.getContent();
            			
			if(resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				System.out.println("■■L.POINT 응답데이터■■=="+resData);
			
			}else {
			System.out.println("응답없음 재적립 및 망취소요망");	
			}
			//return resData;
			
		    
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lpointResData ;
	}
	
	
//----------------------------------------------
// 개방형 포인트 적립승인(요청)
//----------------------------------------------
	public static String LpointO200(HttpServletRequest request, HttpServletResponse response, String json) throws IOException{
		System.out.println("■■■■시스템 호출 시작■■■■ ");
		
		int timeout = 10;
		String lpointResData = null;
		try {
		   /*URL 설정 */
		    HttpPost httpost = new HttpPost(new URI("https://op/devop.lpoint.com:8903/op"));
		    //httpost.addHeader("Content-Type", "application/json");
		   /*Time Out 시간설정 */
		    RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
		   .setConnectionRequestTimeout(timeout * 1000)
		   .setSocketTimeout(timeout * 1000).build(); 
			
		    CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
			System.out.println("■■■■연결성공■■■■ ");
		    RequestConfig.Builder requestBuilder = RequestConfig.custom();
		    HttpClientBuilder builder = HttpClientBuilder.create();
		    builder.setDefaultRequestConfig(requestBuilder.build());
		    HttpClient client = builder.build();
		 
		    /*전문 데이타 셋팅*/
			String jsonData = "{";
			jsonData +=  "          \"control\" : ";													  /*컨트롤영역*/
			jsonData +=  "          { ";													
			jsonData +=  "                    flwNo : \"O110B20120160126000001\"";/*추적번호*/
			jsonData +=  "          }";
			jsonData +=  "          , wcc : \"3\"";			                      					 /*WCC(인증방식) / 1:MSR/2:IC/3:KEY IN*/
			jsonData +=  "          , aprAkMdDc : \"1\"";	                        			 /*승인요청방식구분코드/ 1:POS		*/
			jsonData +=  "          , cstDrmV : \"2\"";		                        		     /*고객식별 구분코드/ 1:카드번호 /2: 고객번호*/	
			jsonData +=  "          , cdno : \" \"";	                        						 /*★카드번호*/
			jsonData +=  "          , cstDrmDc : \" \"";	                        				 /*★고객식별값/ 20자 (고객번호 지원받고 데이터 전송)*/
			jsonData +=  "          , copMcno : \" \"";		                       			     /*★개방형 제휴가맹점번호/10자(가맹점 번호 지원받고 데이터전송예정)*/
			jsonData +=  "          , ccoAprno : \" \"";		                       			     /*★개방형 제휴사승인번호/19자*/
			jsonData +=  "          , deDt : \" \"";		                       			 	         /*★거래일자*/
			jsonData +=  "          , deHr : \" \"";		                       			 	         /*★거래시간*/
			jsonData +=  "          , deDc : \"10 \"";		                       			     	 /*★거래구분코드/10:포인트적립*/
			jsonData +=  "          , deRsc : \"100\"";		                       			     /*★거래사유코드/100:대금결제*/
			jsonData +=  "          , rvDc : \"1\"";		                       			 		     /*★적립구분코드/1:정상*/
			jsonData +=  "          , deAkMdDc : \"1\"";		                      	  	     /*★거래요청방식구분코드/0:멤버스카드제시/1:휴대폰번호/ 2:영수증(사후적립)/ 9:기타*/
			jsonData +=  "          , ptRvDc : \"1\"";		                       			 	     /*★포인트적립구분코드/1:포인트대상값기준*/
			jsonData +=  "          , totSlAm : 10000";												 /*(INT)총매출금액/모든결제수단금액입력*/
			jsonData +=  "          , ptOjAm : 10000";												 /*(INT)포인트대상금액*/
			jsonData +=  "          , cshSlAm : 0";													 /*(INT)현금 매출금액*/
			jsonData +=  "          , mbdSlAm  : 0";												 /*(INT)상품권 매출금액*/
			jsonData +=  "          , ccdSlAm  : 0";													 /*(INT)신용카드 매출금액*/
			jsonData +=  "          , ptSlAm  : 0";													     /*(INT)포인트 매출금액*/
			jsonData +=  "          , etcSlAm  : 0";													 /*(INT)기타 매출금액*/
			jsonData +=  "          , cponNo : \"\"";		                       			 	     /*★쿠폰번호*/
			jsonData +=  "          , deAkInf : \"010-1234-1234\"";						 /*★거래요청정보 1이면 휴대폰번호, 2이면 영수증번호*/
			jsonData +=  "          , copMbrGdC : \"\"";		                       			 /*★제휴사회원등급코드*/
			jsonData +=  "          , filler : \"\"";		                       						 /*★추가데이터 저장영역*/
			jsonData +=  "          , evnInfCt : 0";					                       			 /*★이벤트정보건수*/
			jsonData += "  \"evnInfLst\": [";														/*(LIST)이벤트정보목록*/
			jsonData += "    {";
			jsonData += "      \"evnC\": \"\",";												/*이벤트코드*/
			jsonData += "      \"evnNm\": \"\",";												/*이벤트명*/
			jsonData += "      ,	slAm: 0";															    /*이벤에 대한 매출금액*/
			jsonData += "      ,	ttnCrtPt: 0";															/*이벤에 대한 금회생성포인트*/
			jsonData += "      ,	vlMt: 00";																/*유효개월수*/
			jsonData += "    }";
			jsonData += "  ],";
			jsonData +=  "          , sttCdCt  : 0";													 /*(INT)결제카드건수*/
			jsonData += "  \"sttCdLst\": [";														/*(LIST)결제카드목록*/
			jsonData += "    {";
			jsonData += "      \"sttCdno\": \"\",";											/*결제카드번호*/
			jsonData += "      ,	sttCdCaSlAm: 0";													/*결제카드별매출금액*/
			jsonData += "    }";
			jsonData += "  ],";
			jsonData += "}";
		    System.out.println("■■JSON 데이터 셋팅 성공 ■■");
		    
		    /*JSON 데이터를 문자열 엔티티로..*/

		    StringEntity stringEntity = new StringEntity(jsonData,
		    													ContentType.create("application/json", "UTF-8"));
		    httpost.setEntity(stringEntity);
		    
		    /*전송*/
		    client.execute(httpost);
		    System.out.println("■■전송종료■■");
		    
		    /*응답데이터를 받기위한 서비스호출*/
		    
			String resData = "";
			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();
			
			
			//is = resEntity.getContent();
            			
			if(resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				System.out.println("■■L.POINT 응답데이터■■=="+resData);
			
			}else {
			System.out.println("응답없음 재적립 및 망취소요망");	
			}
			//return resData;
			
		    
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lpointResData ;
	}
	
	
	//----------------------------------------------
	// 개방형 포인트 적립취소(요청)
	//----------------------------------------------
	public static String LpointO210(HttpServletRequest request, HttpServletResponse response, String json) throws IOException{
		System.out.println("■■■■시스템 호출 시작■■■■ ");
		
		int timeout = 10;
		String lpointResData = null;
		try {
		   /*URL 설정 */
		    HttpPost httpost = new HttpPost(new URI("https://op/devop.lpoint.com:8903/op"));
		    //httpost.addHeader("Content-Type", "application/json");
		   /*Time Out 시간설정 */
		    RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
		   .setConnectionRequestTimeout(timeout * 1000)
		   .setSocketTimeout(timeout * 1000).build(); 
			
		    CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
			System.out.println("■■■■연결성공■■■■ ");
		    RequestConfig.Builder requestBuilder = RequestConfig.custom();
		    HttpClientBuilder builder = HttpClientBuilder.create();
		    builder.setDefaultRequestConfig(requestBuilder.build());
		    HttpClient client = builder.build();
		 
		    /*전문 데이타 셋팅*/
			String jsonData = "{";
			jsonData +=  "          \"control\" : ";													  /*컨트롤영역*/
			jsonData +=  "          { ";													
			jsonData +=  "                    flwNo : \"O110B20120160126000001\"";/*추적번호*/
			jsonData +=  "          }";
			jsonData +=  "          , wcc : \"3\"";			                      					 /*WCC(인증방식) / 1:MSR/2:IC/3:KEY IN*/
			jsonData +=  "          , aprAkMdD	c : \"1\"";	                        		 /*승인요청방식구분코드/ 1:POS		*/
			jsonData +=  "          , cstDrmV : \"2\"";		                        		     /*고객식별 구분코드/ 1:카드번호 /2: 고객번호*/	
			jsonData +=  "          , cdno : \" \"";	                        						 /*★카드번호*/
			jsonData +=  "          , cstDrmDc : \" \"";	                        				 /*★고객식별값/ 20자 (고객번호 지원받고 데이터 전송)*/
			jsonData +=  "          , copMcno : \" \"";		                       			     /*★개방형 제휴가맹점번호/10자(가맹점 번호 지원받고 데이터전송예정)*/
			jsonData +=  "          , ccoAprno : \" \"";		                       			     /*★개방형 제휴사승인번호/19자*/
			jsonData +=  "          , deDt : \" \"";					          			 	         /*★거래일자*/
			jsonData +=  "          , deHr : \" \"";		                       			 	         /*★거래시간*/
			jsonData +=  "          , deDc : \"10 \"";		                       			     	 /*거래	구분코드/10:포인트적립/30:포인트조정*/
			jsonData +=  "          , deRsc : \"100\"";		                       			     /*거래사유코드/100:대금결제*/
			jsonData +=  "          , rvDc : \"2\"";		                       			 		     /*적립구분코드/2:취소*/
			jsonData +=  "          , deAkMdDc : \"1\"";		                      	  	     /*거래요청방식구분코드/0:멤버스카드제시/1:휴대폰번호/ 2:영수증(사후적립)/ 9:기타*/
			jsonData +=  "          , ptRvDc : \"1\"";		                       			 	     /*포인트적립구분코드/1:포인트대상값기준*/
			jsonData +=  "          , totSlAm : 10000";												 /*(INT)총매출금액/모든결제수단금액입력*/
			jsonData +=  "          , ptOjAm : 10000";												 /*(INT)포인트대상금액*/
			jsonData +=  "          , cshSlAm : 0";													 /*(INT)현금 매출금액*/
			jsonData +=  "          , mbdSlAm  : 0";												 /*(INT)상품권 매출금액*/
			jsonData +=  "          , ccdSlAm  : 0";													 /*(INT)신용카드 매출금액*/
			jsonData +=  "          , ptSlAm  : 0";													     /*(INT)포인트 매출금액*/
			jsonData +=  "          , etcSlAm  : 0";													 /*(INT)기타 매출금액*/
			jsonData +=  "          , cponNo : \"1\"";		                       			 	 /*쿠폰번호*/
			jsonData +=  "          , rtgDc : \"1\"";		                       			 	     /*반품구분코드/0:자동/1:수기반품*/
			jsonData +=  "          , otInfYnDc : \"1\"";		                       			 	 /*원거래정보유무구분코드1:원거래있음/2;원거래없음*/
			jsonData +=  "          , otInfDc : \"2\"";	                       			 	     /*원거래정보구분코드/1:운영사원승인정보/2:제휴사원승인정보*/
			jsonData +=  "          , otAprno : \"\"";		                       			         /*★원거래승인번호(otInfDc의값에따라 운영사 제휴사 승인번호 다르게 셋팅)*/
			jsonData +=  "          , otDt : \"1\"";		                       			 	         /*★원거래일자*/
			jsonData +=  "          , deAkInf : \"010-1234-1234\"";	       			 	 /*거래요청정보*/
			jsonData +=  "          , filler : \"\"";		                       					 	 /*추가데이터저장공간*/
			jsonData += "}";
			
			System.out.println("■■JSON 데이터 셋팅 성공 ■■");
		    
		    /*JSON 데이터를 문자열 엔티티로..*/

		    StringEntity stringEntity = new StringEntity(jsonData,
		    													ContentType.create("application/json", "UTF-8"));
		    httpost.setEntity(stringEntity);
		    
		    /*전송*/
		    client.execute(httpost);
		    System.out.println("■■전송종료■■");
		    
		    /*응답데이터를 받기위한 서비스호출*/
		    
			String resData = "";
			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();
			
			
			//is = resEntity.getContent();
            			
			if(resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				System.out.println("■■L.POINT 응답데이터■■=="+resData);
			
			}else {
			System.out.println("응답없음 재적립 및 망취소요망");	
			}
			//return resData;
			
		    
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lpointResData ;
	}
	
//---------------------------------------------
//개방형 포인트 사용 승인 (요청)
//---------------------------------------------

	public static String LpointO240(HttpServletRequest request, HttpServletResponse response, String json) throws IOException{
		System.out.println("■■■■시스템 호출 시작■■■■ ");
		
		int timeout = 10;
		String lpointResData = null;
		try {
		   /*URL 설정 */
		    HttpPost httpost = new HttpPost(new URI("https://op/devop.lpoint.com:8903/op"));
		    //httpost.addHeader("Content-Type", "application/json");
		   /*Time Out 시간설정 */
		    RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
		   .setConnectionRequestTimeout(timeout * 1000)
		   .setSocketTimeout(timeout * 1000).build(); 
			
		    CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
			System.out.println("■■■■연결성공■■■■ ");
		    RequestConfig.Builder requestBuilder = RequestConfig.custom();
		    HttpClientBuilder builder = HttpClientBuilder.create();
		    builder.setDefaultRequestConfig(requestBuilder.build());
		    HttpClient client = builder.build();
		 
		    /*전문 데이타 셋팅*/
			String jsonData = "{";
			jsonData +=  "          \"control\" : ";													  /*컨트롤영역*/
			jsonData +=  "          { ";													
			jsonData +=  "                    flwNo : \"O110B20120160126000001\"";/*추적번호*/
			jsonData +=  "          }";
			jsonData +=  "          , wcc : \"3\"";			                      					 /*WCC(인증방식) / 1:MSR/2:IC/3:KEY IN*/
			jsonData +=  "          , aprAkMdDc : \"1\"";	                        			 /*승인요청방식구분코드/ 1:POS		*/
			jsonData +=  "          , cstDrmV : \"1\"";		                        		     /*고객식별 구분코드/ 1:카드번호 */	
			jsonData +=  "          , cdno : \" \"";	                        				 		 /*★카드식별값 37자*/
			jsonData +=  "          , copMcno : \" \"";		                       			     /*★개방형 제휴가맹점번호/10자(가맹점 번호 지원받고 데이터전송예정)*/
			jsonData +=  "          , posNo : \"\"";		                        				 /*★포스번호 8자(포스번호+점포코드)*/
			jsonData +=  "          , ccoAprno : \" \"";		                       			     /*★개방형 제휴사승인번호/19자*/
			jsonData +=  "          , deDt : \" \"";					          			 	         /*★거래일자*/
			jsonData +=  "          , deHr : \" \"";		                       			 	         /*★거래시간*/
			jsonData +=  "          , deDc : \"20 \"";		                       			     	 /*거래	구분코드/20:포인트사용*/
			jsonData +=  "          , deRsc : \"200\"";		                       			     /*거래사유코드/200:대금결제/212:차감할인/213:부분차감*/
			jsonData +=  "          , rvDc : \"1\"";		                       			 		     /*적립구분코드/1:정상*/
			jsonData +=  "          , deAkMdDc : \"0\"";		                      	  	     /*거래요청방식구분코드/0:멤버스카드제시/ 9:기타*/
			jsonData +=  "          , ptUDc : \"1\"";		                       			 	     /*포인트사용구분코드/1:일반거래/2:요청포인트 이내 가용포인트전부사용/3:포인트 차감할인 매출금액으로 사용포인트결정/4:포인트부분차감 매출금액으로 재적립요청*/
			jsonData +=  "          , ttnUPt : 10000";												 /*(INT)금회사용포인트 (포인트사용구분코드가 1,2이면 필수값 */
			jsonData +=  "          , cponNo : \"\"";		                      	  			     /*쿠폰번호*/
			jsonData +=  "          , slAm  : 10000";													 /*(INT)매출금액*/
			jsonData +=  "          , filler : \"\"";		                       					 	 /*추가데이터저장공간*/
			jsonData += "}";
		    System.out.println("■■JSON 데이터 셋팅 성공 ■■");
		    
		    /*JSON 데이터를 문자열 엔티티로..*/

		    StringEntity stringEntity = new StringEntity(jsonData,
		    													ContentType.create("application/json", "UTF-8"));
		    httpost.setEntity(stringEntity);
		    
		    /*전송*/
		    client.execute(httpost);
		    System.out.println("■■전송종료■■");
		    
		    /*응답데이터를 받기위한 서비스호출*/
		    
			String resData = "";
			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();
			
			
			//is = resEntity.getContent();
            			
			if(resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				System.out.println("■■L.POINT 응답데이터■■=="+resData);
			
			}else {
			System.out.println("응답없음 재적립 및 망취소요망");	
			}
			//return resData;
			
		    
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lpointResData ;
	}
	
	
//---------------------------------------------
//개방형 포인트 취소승인 (요청)
//---------------------------------------------
	public static String LpointO250(HttpServletRequest request, HttpServletResponse response, String json) throws IOException{
		System.out.println("■■■■시스템 호출 시작■■■■ ");
		
		int timeout = 10;
		String lpointResData = null;
		try {
		   /*URL 설정 */
		    HttpPost httpost = new HttpPost(new URI("https://op/devop.lpoint.com:8903/op"));
		    //httpost.addHeader("Content-Type", "application/json");
		   /*Time Out 시간설정 */
		    RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
		   .setConnectionRequestTimeout(timeout * 1000)
		   .setSocketTimeout(timeout * 1000).build(); 
			
		    CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
			System.out.println("■■■■연결성공■■■■ ");
		    RequestConfig.Builder requestBuilder = RequestConfig.custom();
		    HttpClientBuilder builder = HttpClientBuilder.create();
		    builder.setDefaultRequestConfig(requestBuilder.build());
		    HttpClient client = builder.build();
		 
		    /*전문 데이타 셋팅*/
		    String jsonData = "{";
			jsonData +=  "          \"control\" : ";													  /*컨트롤영역*/
			jsonData +=  "          { ";													
			jsonData +=  "                    flwNo : \"O110B20120160126000001\"";/*추적번호*/
			jsonData +=  "          }";
			jsonData +=  "          , wcc : \"3\"";			                      					 /*WCC(인증방식) / 1:MSR/2:IC/3:KEY IN*/
			jsonData +=  "          , aprAkMdDc : \"1\"";	                        			 /*승인요청방식구분코드/ 1:POS		*/
			jsonData +=  "          , cstDrmV : \"1\"";		                        		     /*고객식별 구분코드/ 1:카드번호 */	
			jsonData +=  "          , cdno : \" \"";	                        				 		 /*★카드식별값 37자*/
			jsonData +=  "          , copMcno : \" \"";		                       			     /*★개방형 제휴가맹점번호/10자(가맹점 번호 지원받고 데이터전송예정)*/
			jsonData +=  "          , posNo : \"\"";		                        				 /*★포스번호 8자(포스번호+점포코드)*/
			jsonData +=  "          , ccoAprno : \" \"";		                       			     /*★개방형 제휴사승인번호/19자*/
			jsonData +=  "          , deDt : \" \"";					          			 	         /*★거래일자*/
			jsonData +=  "          , deHr : \" \"";		                       			 	         /*★거래시간*/
			jsonData +=  "          , deDc : \"20 \"";		                       			     	 /*거래	구분코드/20:포인트사용*/
			jsonData +=  "          , deRsc : \"200\"";		                       			     /*거래사유코드/200:대금결제/212:차감할인/213:부분차감*/
			jsonData +=  "          , rvDc : \"2\"";		                       			 		     /*적립구분코드/2:취소*/
			jsonData +=  "          , deAkMdDc : \"0\"";		                      	  	     /*거래요청방식구분코드/0:멤버스카드제시/ 9:기타*/
			jsonData +=  "          , ptUDc : \"1\"";		                       			 	     /*포인트사용구분코드/1:일반거래/2:요청포인트 이내 가용포인트전부사용/3:포인트 차감할인 매출금액으로 사용포인트결정/4:포인트부분차감 매출금액으로 재적립요청*/
			jsonData +=  "          , ttnUPt : 10000";												 /*(INT)금회사용포인트 (포인트사용구분코드가 1,2이면 필수값 */
			jsonData +=  "          , cponNo : \"\"";		                      	  			     /*쿠폰번호*/
			jsonData +=  "          , rtgDc : \"1\"";		                       			 	     /*반품구분코드/0:자동/1:수기반품*/
			jsonData +=  "          , otInfYnDc : \"1\"";		                       			 	 /*원거래정보유무구분코드/1:원거래정보있음 / 2:원거래정보없음*/
			jsonData +=  "          , otInfDc : \"2\"";		                       			  	 /*원거래정보유무구분코드/1:운영사원승인정보 / 2:제휴사원승인정보*/
			jsonData +=  "          , otAprno	 : \"\"";		                       			 	 /*★원거래승인번호 	/원거래정보구분코드에 따라서 승인번호를 입력함 */
			jsonData +=  "          , otDt : \"\"2";		                       			 			 /*원거래일자/1: 운영사원승인일자 2: 	제휴사원거래일자*/
			jsonData +=  "          , filler : \"\"";		                       					 	 /*추가데이터저장공간*/
			jsonData += "}";
		    System.out.println("■■JSON 데이터 셋팅 성공 ■■");
		    
		    /*JSON 데이터를 문자열 엔티티로..*/

		    StringEntity stringEntity = new StringEntity(jsonData,
		    													ContentType.create("application/json", "UTF-8"));
		    httpost.setEntity(stringEntity);
		    
		    /*전송*/
		    client.execute(httpost);
		    System.out.println("■■전송종료■■");
		    
		    /*응답데이터를 받기위한 서비스호출*/
		    
			String resData = "";
			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();
			
			//is = resEntity.getContent();
			if(resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				System.out.println("■■L.POINT 응답데이터■■=="+resData);
			}else{
			System.out.println("응답없음 재적립 및 망취소요망");	
			}
			//return resData;
		    
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lpointResData ;
	}
}
