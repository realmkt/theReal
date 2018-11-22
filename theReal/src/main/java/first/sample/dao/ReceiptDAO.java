package first.sample.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dao.AbstractDAO;

@Repository("receiptDAO")
public class ReceiptDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception {
		return selectPagingList("sample.selectBoardList", map);
	}

	@SuppressWarnings("unchecked")
	public String getCi(Map<String, Object> map) {
		return (String) selectOne("sample.getCi", map);
	}

	public void insertBoard(Map<String, Object> map) throws Exception {
		insert("sample.insertBoard", map);
	}

	public void updateHitCnt(Map<String, Object> map) throws Exception {
		update("sample.updateHitCnt", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("sample.selectBoardDetail", map);
	}

	public void updateBoard(Map<String, Object> map) throws Exception {
		update("sample.updateBoard", map);
	}

	public void deleteBoard(Map<String, Object> map) throws Exception {
		update("sample.deleteBoard", map);
	}

	public void insertFile(Map<String, Object> map) throws Exception {
		insert("sample.insertFile", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFileList(Map<String, Object> map) throws Exception {
		return selectList("sample.selectFileList", map);
	}

	public void deleteFileList(Map<String, Object> map) throws Exception {
		update("sample.deleteFileList", map);
	}

	public void updateFile(Map<String, Object> map) throws Exception {
		update("sample.updateFile", map);
	}

	public Integer insertReceiptInfo(Map<String, Object> map) throws Exception {
		log.debug("map:" + map);
		Integer autoKeyValue = (Integer) insert("sample.insertReceiptInfo", map);
		return autoKeyValue;
	}

	public Integer selectMemberChk(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		Integer identify = (Integer) selectOne("sample.selectMemberChk", map);
		return identify;
	}

	public Integer selectLgnChk(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		Integer identify = (Integer) selectOne("sample.selectLgnChk", map);
		return identify;
	}

	public void insertMember(HashMap<String, String> map) {
		log.debug("map:" + map);
		insert("sample.insertMember", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectUserInfoList(Map<String, Object> map) throws Exception {
		// return (List<Map<String,
		// Object>>)selectList("sample.selectUserInfoList", map);
		return selectList("sample.selectUserInfoList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectE01List(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return selectList("sample.selectE01List", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectF01List(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.selectF01List", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMonthF01List(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.selectMonthF01List", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectAddListF01(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.selectAddListF01", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectDateCntListF01(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.selectDateCntListF01", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectDailyListF01(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.selectDailyListF01", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectNoticeMove(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.selectNoticeMove", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectDivSearch(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.selectDivSearch", map);
	}

	public void updateAppD01AddCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		update("sample.updateHitCnt", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardMng(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.selectBoardMng", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardWriteDetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.selectBoardDetail", map);
	}

	public void updateAdminBoard(Map<String, Object> map) {
		// TODO Auto-generated method stub
		update("sample.updateAdminBoard", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectAppE01() {
		// TODO Auto-generated method stub
		return selectList("sample.selectAppE01");
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectAppChart(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.selectAppChart", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> appChartTest2(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("sample.selectAppChart", map);
	}

	public void insertReceiptData(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("dao@@@@" + map);
		insert("sample.insertReceiptData", map);
	}
	
	public void insertReceiptDataRenew(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("dao@@@@" + map);
		insert("sample.insertReceiptDataRenew", map);
	}
	
	public void insertCancelReceiptData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("dao@@@@" + map);
		insert("sample.insertCancelReceiptData", map);
	}

	public void insertReceiptDeatailData(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("dao@@@@" + map);
		insert("sample.insertReceiptDeatailData", map);
	}
	
	public HashMap<String, Object> getReceiptDeatailData(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("dao@@@@" + map);
		HashMap<String, Object> resultMap = (HashMap<String, Object>) selectOne("sample.getReceiptDeatailData", map);
		return resultMap;
	}
	
	public void deleteFailDate(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("dao@@@@" + map);
		delete("sample.deleteFailDate", map);
		delete("sample.deleteFailDateDetail", map);
	}
	
	public Map<String, Object> getRcp02Data(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("dao@@@@" + map);
		Map<String, Object> resultMap = (Map<String, Object>) selectOne("sample.getRcp02Data", map);
		return resultMap;
	}
	
	public void insertReceiptDeatailDataRenew(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("dao@@@@" + map);
		insert("sample.insertReceiptDeatailDataRenew", map);
	}
	
	public Integer insertCancelReceiptDeatailDataRenew(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("dao@@@@" + map);
		insert("sample.insertCancelReceiptDeatailDataRenew", map);
		return (Integer)selectOne("sample.receiptDeatailCnt", map);
		
	}


	public void insertSmsData(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("dao@@@@" + map);
		insert("sample.insertSmsData", map);
	}

	public void appMemberInsert(Map<String, Object> map) throws Exception {
		log.debug("map:" + map);
		insert("sample.appMemberInsert", map);
	}

	public void telNumInit(Map<String, Object> map) throws Exception {
		log.debug("map:" + map);
		update("sample.telUserNumInit", map);
		update("sample.telDataNumInit", map);
		update("sample.telDetailDataNumInit", map);
	}

	public void appMemberUpdate(Map<String, Object> map) {
		log.debug("map:" + map);
		update("sample.appMemberUpdate", map);
	}

	public List<Map<String, Object>> lgnChk(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return selectList("sample.lgnChk", map);
	}

	public List<Map<String, Object>> lgnChk2(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return selectList("sample.lgnChk2", map);
	}

	public List<Map<String, Object>> getId(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return selectList("sample.getId", map);
	}

	public String getPw(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return (String) selectOne("sample.getPw", map);
	}

	public List<Map<String, Object>> depth02CardList(Map<String, Object> map) {
		return selectList("sample.depth02CardList", map);
	}

	public List<Map<String, Object>> depth03CardList(Map<String, Object> map) {
		return selectList("sample.depth03CardList", map);
	}

	public List<Map<String, Object>> depth02DayList(Map<String, Object> map) {
		return selectList("sample.depth02DayList", map);
	}

	public List<Map<String, Object>> depth03DayList(Map<String, Object> map) {
		return selectList("sample.depth03DayList", map);
	}

	public List<Map<String, Object>> depth02DivList(Map<String, Object> map) {
		return selectList("sample.depth02DivList", map);
	}

	public List<Map<String, Object>> depth03DivList(Map<String, Object> map) {
		return selectList("sample.depth03DivList", map);
	}
	
	public void cateChange(Map<String, Object> map) {
		update("sample.cateChange", map);
	}
	
	public void cateChangeBack(Map<String, Object> map) {
		update("sample.cateChangeBack", map);
	}

	public List<Map<String, Object>> depth01List(Map<String, Object> map) {
		return selectList("sample.depth01List", map);
	}

	// 리뉴얼 모바일
	public String userMinDate(Map<String, Object> map) {
		return (String)selectOne("sample.userMinDate", map);
	}
	
	
	public List<Map<String, Object>> list01(Map<String, Object> map) {
		return selectList("sample.list01", map);
	}

	public List<Map<String, Object>> dayList02(Map<String, Object> map) {
		return selectList("sample.dayList02", map);
	}

	public List<Map<String, Object>> dayList03(Map<String, Object> map) {
		return selectList("sample.dayList03", map);
	}
	
	public List<Map<String, Object>> cardList02(Map<String, Object> map) {
		return selectList("sample.cardList02", map);
	}

	public List<Map<String, Object>> cardList03(Map<String, Object> map) {
		return selectList("sample.cardList03", map);
	}
	
	public List<Map<String, Object>> divList02(Map<String, Object> map) {
		return selectList("sample.divList02", map);
	}

	public List<Map<String, Object>> divList03(Map<String, Object> map) {
		return selectList("sample.divList03", map);
	}

	public List<Map<String, Object>> receiptHeader(Map<String, Object> map) {
		return selectList("sample.receiptHeader", map);
	}
	
	public List<Map<String, Object>> receiptFooter(Map<String, Object> map) {
		return selectList("sample.receiptFooter", map); 
	}
	
	public List<Map<String, Object>> latestData(Map<String, Object> map) {
		return selectList("sample.latestData", map);
	}

	public List<Map<String, Object>> affliateData(Map<String, Object> map) {
		return selectList("sample.affliateData", map);
	}

	public List<Map<String, Object>> smsLatestData(Map<String, Object> map) {
		return selectList("sample.smsLatestData", map);
	}

	public List<Map<String, Object>> latestDetailData(Map<String, Object> map) {
		return selectList("sample.latestDetailData", map);
	}

	public List<Map<String, Object>> startCurData(Map<String, Object> map) {
		// return (List<Map<String,
		// Object>>)selectList("sample.selectUserInfoList", map);
		return selectList("sample.startCurData", map);
	}

	public List<Map<String, Object>> startUserData(Map<String, Object> map) {
		// return (List<Map<String,
		// Object>>)selectList("sample.selectUserInfoList", map);
		return selectList("sample.startUserData", map);
	}

	public List<Map<String, Object>> startSumData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.startSumData", map);
	}

	public List<Map<String, Object>> startRecYnData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.startRecYnData", map);
	}

	public String eMailChk(String telNo) {
		String eMail = (String) selectOne("sample.eMailChk", telNo);
		return eMail;
	}

	public Integer joinChkId(String eMail) {
		Integer identify = (Integer) selectOne("sample.joinChkId", eMail);
		return identify;
	}

	public String getCreaDate(Map<String, Object> map) {
		String userCreaDate = (String) selectOne("sample.getCreaDate", map);
		return userCreaDate;
	}
	
	public Map<String, Object> getKakaoApi(Map<String, Object> map) {
		Map<String, Object> userCreaDate = (Map<String, Object>) selectOne("sample.getKakaoApi", map);
		return userCreaDate;
	}

	////// uplus

	public String pushChk(String eMailChk) {
		String pushChk = (String) selectOne("sample.pushChk", eMailChk);
		return pushChk;
	}

	public String uPlusChk(String telNo) {
		String uPlusChk = (String) selectOne("sample.uPlusChk", telNo);
		return uPlusChk;
	}

	public void latestUpdateData(Map<String, Object> map) {
		log.debug("map:" + map);
		update("sample.latestUpdateData", map);
	}
	
	public void latestUpdateDataRenew(Map<String, Object> map) {
		log.debug("map:" + map);
		update("sample.latestUpdateDataRenew", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> houseHold(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.houseHold", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> houseHoldDetail(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.houseHoldDetail", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> monthChartData(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.monthChartData", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> dayDivChartData(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.dayDivChartData", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> instDivChartData(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.instDivChartData", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> dayOfMonthChartData(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.dayOfMonthChartData", map);
	}

	public void updatePushYn(Map<String, Object> map) {
		log.debug("map:" + map);
		update("sample.updatePushYn", map);
	}

	public void settingChkPw(Map<String, Object> map) {
		log.debug("map:" + map);
		update("sample.settingChkPw", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> eventBoardList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.eventBoardList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> monthAllDate(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.monthAllDate", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> receiveUplusDtlData(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		// return (List<Map<String,
		// Object>>)selectList("sample.receiveUplusDtlData", map);
		return (Map<String, Object>) selectOne("sample.receiveUplusDtlData", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> receiveUplusDtlArrayData(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		// return (List<Map<String,
		// Object>>)selectList("sample.receiveUplusDtlData", map);
		return selectList("sample.receiveUplusDtlArrayData", map);
	}

	public Integer selectUplusJoinChk(HashMap<String, Object> map) {
		Integer selectUplusJoinChk = (Integer) selectOne("sample.selectUplusJoinChk", map);
		return selectUplusJoinChk;
	}

	public Integer selectUsedTelChk(HashMap<String, Object> map) {
		Integer selectUsedTelChk = (Integer) selectOne("sample.selectUsedTelChk", map);
		return selectUsedTelChk;
	}

	public String selectEmail(HashMap<String, Object> map) {
		String selectEmail = (String) selectOne("sample.selectEmail", map);
		return selectEmail;
	}

	public void updateUsedData(HashMap<String, Object> map) {
		update("sample.updateUsedData", map);
	}

	public void receiveUplusJoin(HashMap<String, Object> map) {
		insert("sample.receiveUplusJoin", map);
	}

	public String originUplusUserKey(HashMap<String, Object> map) {
		return (String) selectOne("sample.originUplusUserKey", map);
	}

	public void updateUplusData(HashMap<String, Object> map) {
		insert("sample.updateUplusDataUser", map);
	}

	public Integer updateUplusDrop(HashMap<String, Object> map) {
		return (Integer) update("sample.updateUplusDrop", map);
	}

	public String getSalesType(HashMap<String, Object> map) {
		String salesType = (String) selectOne("sample.getSalesType", map);
		return salesType;
	}

	public Map<String, Object> affDetail(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("sample.affDetail", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> couponList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.couponList", map);
	}

	public Map<String, Object> couponRead(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("sample.couponRead", map);
	}

	public void coucntAdd(Map<String, Object> map) {
		update("sample.coucntAdd", map);
	}

	public Integer valiCoupon(HashMap<String, Object> map) {
		return (Integer) selectOne("sample.valiCoupon", map);
	}

	public void couponAdd(HashMap<String, Object> map) {
		selectOne("sample.couponAdd", map);
		update("sample.couponDounUp", map);
	}

	public Map<String, Object> couponDown(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("sample.couponDown", map);
	}

	public Map<String, Object> couponDetail(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("sample.couponDetail", map);
	}

	public HashMap<String, Object> cancelReceipt(Map<String, Object> map) {
		return (HashMap<String, Object>) selectOne("sample.cancelReceipt", map);
	}
	
	public HashMap<String, Object> cancelGetReceipt(HashMap<String, Object> map) {
		return (HashMap<String, Object>) selectOne("sample.cancelGetReceipt", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> couponMyList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.couponMyList", map);
	}

	public void couponUsing(Map<String, Object> map) {
		update("sample.couponUsing", map);
	}

	// uplus
	@SuppressWarnings("unchecked")
	public Integer joinChk(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) selectOne("sample.joinChk", map);
	}

	// uplus

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> uplusReceiptData(Map<String, Object> map) {
		return selectList("sample.uplusReceiptData", map);
	}

	public Map<String, Object> getShopInfo(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("sample.getShopInfo", map);
	}
	
	public Map<String, Object> getShopInfoRenew(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("sample.getShopInfoRenew", map);
	}

	public List<Map<String, Object>> ReceiptDetail(Map<String, Object> map) {
		return selectList("sample.ReceiptDetail", map);
	}
	
	public List<Map<String, Object>> receiptDetailRenew(Map<String, Object> map) {
		return selectList("sample.receiptDetailRenew", map);
	}

	public List<Map<String, Object>> getDetailReceipt(Map<String, Object> map) {
		return selectList("sample.getDetailReceipt", map);
	}

	public List<Map<String, Object>> latestDataEmail(List<String> list) {
		return selectList("sample.latestDataEmail", list);
	}

	public List<Map<String, Object>> latestDataEmailDetail(List<String> list) {
		return selectList("sample.latestDataEmailDetail", list);
	}

	public void transUp(List<String> list) {
		update("sample.transUp", list);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> emailList(String telNo) {
		return selectList("sample.emailList", telNo);
	}

	public void lastEmailUpdate(Map<String, Object> emailMap) {
		update("sample.lastEmailUpdate", emailMap);

	}

	public Map<String, Object> smsDetailData(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("sample.smsDetailData", map);
	}

	public List<Map<String, Object>> affliate(Map<String, Object> findMap) {
		// TODO Auto-generated method stub
		return selectList("sample.affliate", findMap);
	}

	public List<Map<String, Object>> affliateDetail(String affliate_no) {
		// TODO Auto-generated method stub
		return selectList("sample.affliateDetail", affliate_no);
	}

	public List<Map<String, Object>> payment(Map<String, Object> payImportant) {

		return selectList("sample.payment", payImportant);
	}

	public void mailPush(Map<String, Object> mailMap) {

		update("sample.mailPush", mailMap);
	}

	public List<Map<String, Object>> reviewList(Map<String, Object> reviewMap) {

		return selectList("sample.reviewList", reviewMap);

	}

	public void reviewInsert(Map<String, Object> map) {
		insert("sample.reviewInsert", map);
	}

	public float reviewStarAvg(Map<String, Object> map) {
		return (float) selectOne("sample.reviewStarAvg", map);
	}

	public void starUpdate(Map<String, Object> starMap) {
		update("sample.starUpdate", starMap);

	}

	public void starUpdateA(Map<String, Object> starMap) {
		update("sample.starUpdateA", starMap);
	}

	public void reviewDelete(int review_num) {
		delete("sample.reviewDelete", review_num);

	}

	// 1월 26일 추가
	public int reviewCount(String affliate_no) {
		return (int) selectOne("sample.reviewCount", affliate_no);
	}

	// 1월 29일 추가
	public void countUp(String affliate_no) {
		update("sample.countUp", affliate_no);

	}

	public void countDown(String affliate_no) {
		update("sample.countDown", affliate_no);
	}

	public void leave(String CI) {
		update("sample.leave", CI);
	}
	
	public String userState(String userKey) {

		return (String) selectOne("sample.userState", userKey);
	}

	public List<Map<String, Object>> uplusUserData(Map<String, Object> userMap) {
		return selectList("sample.uplusUserData", userMap);
	}

	public List<Map<String, Object>> uplusReceipeDataDetail(Map<String, Object> userMap2) {
		return selectList("sample.uplusReceipeDataDetail", userMap2);
	}

	public List<Map<String, Object>> allPaid(Map<String, Object> userMap) {
		return selectList("sample.allPaid", userMap);
	}

	public List<Map<String, Object>> uplusReceiptData2(Map<String, Object> userMap) {

		return selectList("sample.uplusReceiptData2", userMap);
	}
}
