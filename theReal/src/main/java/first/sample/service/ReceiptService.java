package first.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ReceiptService {

	String getCi(Map<String, Object> map) throws Exception;
	
	Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception;

	void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;

	Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception;

	void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;

	void deleteBoard(Map<String, Object> map) throws Exception;

	Integer insertReceiptInfo(Map<String, Object> map) throws Exception;

	Integer selectMemberChk(HashMap<String, String> map) throws Exception;

	void insertMember(HashMap<String, String> map) throws Exception;

	public Integer selectLgnChk(HashMap<String, String> map) throws Exception;

	Map<String, Object> selectUserInfoList(Map<String, Object> map) throws Exception;

	Map<String, Object> selectE01List(Map<String, Object> map) throws Exception;

	Map<String, Object> selectF01List(Map<String, Object> map) throws Exception;

	Map<String, Object> selectMonthF01List(HashMap<String, Object> map) throws Exception;

	Map<String, Object> selectAddListF01(HashMap<String, Object> map) throws Exception;

	Map<String, Object> selectDateCntListF01(HashMap<String, Object> map) throws Exception;

	Map<String, Object> selectDailyListF01(HashMap<String, Object> map) throws Exception;

	Map<String, Object> selectNoticeMove(HashMap<String, Object> map) throws Exception;

	Map<String, Object> selectDivSearch(HashMap<String, Object> map) throws Exception;

	void updateAppD01AddCnt(Map<String, Object> map);

	Map<String, Object> selectBoardMng(HashMap<String, Object> map) throws Exception;

	Map<String, Object> selectBoardWriteDetail(Map<String, Object> map) throws Exception;

	void updateAdminBoard(Map<String, Object> map);

	Map<String, Object> selectAppE01() throws Exception;

	Map<String, Object> selectAppChart(HashMap<String, Object> map) throws Exception;

	Map<String, Object> appChartTest2(Map<String, Object> map) throws Exception;

	void insertReceiptData(HashMap<String, Object> map) throws Exception;
	
	void insertReceiptDataRenew(HashMap<String, Object> map) throws Exception;
	
	void insertCancleReceiptData(Map<String, Object> map) throws Exception;

	void insertReceiptDeatailData(HashMap<String, Object> map) throws Exception;
	
	void insertReceiptDeatailDataRenew(HashMap<String, Object> map) throws Exception;

	/**
	 * 설유진 추가
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */

	void insertSmsData(HashMap<String, Object> map) throws Exception;

	void appMemberInsert(Map<String, Object> map) throws Exception;
	
	void telNumInit(Map<String, Object> map) throws Exception;

	void appMemberUpdate(Map<String, Object> map) throws Exception;

	Map<String, Object> lgnChk(HashMap<String, String> map);
	
	Map<String, Object> lgnChk2(HashMap<String, String> map);

	Map<String, Object> getId(HashMap<String, String> map);

	String getPw(HashMap<String, String> map);

	Map<String, Object> depth01List(Map<String, Object> map) throws Exception;

	Map<String, Object> depth02CardList(Map<String, Object> map) throws Exception;

	Map<String, Object> depth03CardList(Map<String, Object> map) throws Exception;

	Map<String, Object> depth02DayList(Map<String, Object> map) throws Exception;

	Map<String, Object> depth03DayList(Map<String, Object> map) throws Exception;

	Map<String, Object> depth02DivList(Map<String, Object> map) throws Exception;

	Map<String, Object> depth03DivList(Map<String, Object> map) throws Exception;

	Map<String, Object> latestData(Map<String, Object> map) throws Exception;

	Map<String, Object> smsLatestData(Map<String, Object> map) throws Exception;

	Map<String, Object> latestDetailData(Map<String, Object> map) throws Exception;

	Map<String, Object> startCurData(Map<String, Object> map) throws Exception;

	Map<String, Object> startUserData(Map<String, Object> map) throws Exception;

	Map<String, Object> startSumData(Map<String, Object> map) throws Exception;

	Map<String, Object> startRecYnData(Map<String, Object> map) throws Exception;
	
	Map<String, Object> affliateData(Map<String, Object> map) throws Exception;
	
	//리뉴얼 mobile
	
	String userMinDate(Map<String, Object> map) throws Exception;
	
	Map<String, Object> list01(Map<String, Object> map) throws Exception;
	
	Map<String, Object> dayList02(Map<String, Object> map) throws Exception;
	
	Map<String, Object> dayList03(Map<String, Object> map) throws Exception;
	
	Map<String, Object> cardList02(Map<String, Object> map) throws Exception;
	
	Map<String, Object> cardList03(Map<String, Object> map) throws Exception;
	
	Map<String, Object> divList02(Map<String, Object> map) throws Exception;
	
	Map<String, Object> divList03(Map<String, Object> map) throws Exception;
	
	void cateChange(Map<String, Object> map) throws Exception;
	
	void cateChangeBack(Map<String, Object> map) throws Exception;

	String eMailChk(String telNo);

	Integer joinChkId(String eMail);

	String pushChk(String eMailChk);

	void latestUpdateData(Map<String, Object> map) throws Exception;
	
	void latestUpdateDataRenew(Map<String, Object> map) throws Exception;

	Map<String, Object> houseHold(HashMap<String, Object> map) throws Exception;

	Map<String, Object> houseHoldDetail(HashMap<String, Object> map) throws Exception;

	Map<String, Object> monthChartData(HashMap<String, Object> map) throws Exception;

	Map<String, Object> dayDivChartData(HashMap<String, Object> map) throws Exception;

	Map<String, Object> instDivChartData(HashMap<String, Object> map) throws Exception;

	Map<String, Object> dayOfMonthChartData(HashMap<String, Object> map) throws Exception;

	void updatePushYn(Map<String, Object> map) throws Exception;

	void settingChkPw(Map<String, Object> map) throws Exception;

	Map<String, Object> eventBoardList(HashMap<String, Object> map) throws Exception;

	Map<String, Object> receiveUplusDtlData(HashMap<String, Object> map) throws Exception;

	List<Map<String, Object>> receiveUplusDtlArrayData(HashMap<String, Object> map) throws Exception;

	Integer selectUplusJoinChk(HashMap<String, Object> map);
	
	Integer selectUsedTelChk(HashMap<String, Object> map);
	
	String selectEmail(HashMap<String, Object> map);

	void updateUsedData(HashMap<String, Object> map);
	
	void receiveUplusJoin(HashMap<String, Object> map);
	
	String originUplusUserKey(HashMap<String, Object> map);
	
	void updateUplusData(HashMap<String, Object> map);
	
	Integer updateUplusDrop(HashMap<String, Object> map);
	
	String uPlusChk(String telNo);

	/**
	 * 강진우 추가
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */

	// SALES_TYPE 바코드로 가져오기
	String getSalesType(HashMap<String, Object> map) throws Exception;
	
	Map<String, Object> affDetail(Map<String, Object> map) throws Exception; 

	HashMap<String, Object> monthAllDate(HashMap<String, Object> map) throws Exception;
	
	Map<String, Object> couponList(HashMap<String, Object> map) throws Exception;
	
	Map<String, Object> couponRead(HashMap<String, Object> map) throws Exception;
	
	void coucntAdd(HashMap<String, Object> map) throws Exception;
	
	Integer valiCoupon(HashMap<String, Object> map);
	
	void couponAdd(HashMap<String, Object> map);
	
	Map<String, Object> couponMyList(HashMap<String, Object> map) throws Exception;
	
	Map<String, Object> couponDown(HashMap<String, Object> map) throws Exception;
	
	Map<String, Object> couponDetail(HashMap<String, Object> map) throws Exception;
	
	Map<String, Object> cancleReceipt(HashMap<String, Object> map) throws Exception;
	
	HashMap<String, Object> cancleGetReceipt(HashMap<String, Object> map) throws Exception;
	
	void couponUsing(HashMap<String, Object> map);
	
	Integer joinChk(HashMap<String, Object> map) throws Exception;
	
	String getCreaDate(Map<String, Object> map);
	
	Map<String, Object> getKakaoApi(Map<String, Object> map);
	
	
	/* uplus */
	
	Map<String, Object> uplusReceiptData(Map<String, Object> map) throws Exception;
	
	Map<String, Object> getShopInfo(Map<String, Object> map) throws Exception;
	
	Map<String, Object> getShopInfoRenew(Map<String, Object> map) throws Exception;
	
	Map<String, Object> ReceiptDetail(Map<String, Object> map) throws Exception;
	
	Map<String, Object> kakaoReceiptDetail(Map<String, Object> map) throws Exception;
	
	Map<String, Object> getDetailReceipt(Map<String, Object> map) throws Exception;


	String userState(String userKey);

	Map<String, Object> uplusUserData(Map<String, Object> userMap);

	Map<String, Object> uplusReceipeDataDetail(Map<String, Object> userMap2);

	Map<String, java.lang.Object> allPaid(Map<String, Object> userMap);

	Map<String, Object> uplusReceiptData2(Map<String, Object> userMap);
	
	
	
	Map<String, Object> latestDataEmail(List<String> list) throws Exception;
	
	Map<String, Object> latestDataEmailDetail(List<String> list) throws Exception;
	
	void transUp(List<String> list) throws Exception;
	
	Map<String, Object> emailList(String telNo);

	void lastEmailUpdate(Map<String, Object> emailMap);

	Map<String, Object> smsDetailData(Map<String, Object> map) throws Exception;

	Map<String, java.lang.Object> affliateDetail(String affliate_no);
	
	Map<String, java.lang.Object> payment(Map<String, Object> payImportant);

	void mailPush(Map<String, Object> mailMap);

	Map<String, Object> affliate(Map<String, Object> findMap);
	
	Map<String, java.lang.Object> reviewList(Map<String, java.lang.Object> reviewMap);

	void reviewInsert(Map<String, java.lang.Object> map);
	
	float reviewStarAvg(Map<String, java.lang.Object> map);

	void starUpdate(Map<String, Object> starMap);

	void starUpdateA(Map<String, java.lang.Object> starMap);
	
	void reviewDelete(int review_num);
	
	int reviewCount(String affliate_no);
	
	void countUp(String affliate_no);
	
	void countDown(String affliate_no);
	

	void leave(String CI);
	
	
}
