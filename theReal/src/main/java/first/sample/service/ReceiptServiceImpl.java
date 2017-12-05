package first.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import first.common.util.FileUtils;
import first.sample.dao.ReceiptDAO;

@Service("receiptService")
public class ReceiptServiceImpl implements ReceiptService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	@Resource(name = "receiptDAO")
	private ReceiptDAO receiptDAO;

	@Override
	public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception {
		return receiptDAO.selectBoardList(map);
	}

	@Override
	public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		receiptDAO.insertBoard(map);

		/*
		 * List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map,
		 * request); for(int i=0, size=list.size(); i<size; i++){
		 * receiptDAO.insertFile(list.get(i)); }
		 */
	}

	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		receiptDAO.updateHitCnt(map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> tempMap = receiptDAO.selectBoardDetail(map);
		resultMap.put("map", tempMap);

		List<Map<String, Object>> list = receiptDAO.selectFileList(map);
		resultMap.put("list", list);

		return resultMap;
	}

	@Override
	public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		receiptDAO.updateBoard(map);

		receiptDAO.deleteFileList(map);
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(map, request);
		Map<String, Object> tempMap = null;
		for (int i = 0, size = list.size(); i < size; i++) {
			tempMap = list.get(i);
			if (tempMap.get("IS_NEW").equals("Y")) {
				receiptDAO.insertFile(tempMap);
			} else {
				receiptDAO.updateFile(tempMap);
			}
		}
	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		receiptDAO.deleteBoard(map);
	}

	@Override
	public Integer insertReceiptInfo(Map<String, Object> map) throws Exception {
		Integer autoKeyValue = receiptDAO.insertReceiptInfo(map);
		return autoKeyValue;
	}

	@Override
	public Integer selectMemberChk(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		Integer identify = receiptDAO.selectMemberChk(map);
		return identify;
	}

	@Override
	public void insertMember(HashMap<String, String> map) throws Exception {
		receiptDAO.insertMember(map);
	}

	@Override
	public Integer selectLgnChk(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		Integer identify = receiptDAO.selectLgnChk(map);

		return identify;
	}

	@Override
	public Map<String, Object> selectUserInfoList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.selectUserInfoList(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectE01List(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.selectE01List(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectF01List(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.selectF01List(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectMonthF01List(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.selectMonthF01List(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectAddListF01(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.selectAddListF01(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectDateCntListF01(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.selectDateCntListF01(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectDailyListF01(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.selectDailyListF01(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectNoticeMove(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.selectNoticeMove(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectDivSearch(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.selectDivSearch(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public void updateAppD01AddCnt(Map<String, Object> map) {
		receiptDAO.updateAppD01AddCnt(map);
	}

	@Override
	public Map<String, Object> selectBoardMng(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.selectBoardMng(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectBoardWriteDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, Object>> list = receiptDAO.selectBoardWriteDetail(map);
		resultMap.put("resultMap", list);

		return resultMap;
	}

	@Override
	public void updateAdminBoard(Map<String, Object> map) {
		receiptDAO.updateAdminBoard(map);
	}

	@Override
	public Map<String, Object> selectAppE01() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, Object>> list = receiptDAO.selectAppE01();
		resultMap.put("resultMap", list);

		return resultMap;
	}

	@Override
	public Map<String, Object> selectAppChart(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.selectAppChart(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> appChartTest2(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> tempMap = receiptDAO.appChartTest2(map);
		resultMap.put("resultMap", tempMap);
		return tempMap;
	}

	@Override
	public void insertReceiptData(HashMap<String, Object> map) throws Exception {
		receiptDAO.insertReceiptData(map);
		System.out.println("impl@@@" + map);
	}

	@Override
	public void insertReceiptDeatailData(HashMap<String, Object> map) throws Exception {
		receiptDAO.insertReceiptDeatailData(map);
		System.out.println("impl@@@" + map);
	}

	/**
	 * 설유진 추가
	 */

	@Override
	public void insertSmsData(HashMap<String, Object> map) throws Exception {
		System.out.println("impl@@@" + map);
		receiptDAO.insertSmsData(map);
	}

	@Override
	public void appMemberInsert(Map<String, Object> map) throws Exception {
		receiptDAO.appMemberInsert(map);
	}

	@Override
	public void telNumInit(Map<String, Object> map) throws Exception {
		receiptDAO.telNumInit(map);
	}

	@Override
	public void appMemberUpdate(Map<String, Object> map) throws Exception {
		receiptDAO.appMemberUpdate(map);
	}

	@Override
	public Map<String, Object> lgnChk(HashMap<String, String> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.lgnChk(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> getId(HashMap<String, String> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.getId(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public String getPw(HashMap<String, String> map) {
		return receiptDAO.getPw(map);
	}

	@Override
	public Map<String, Object> depth01List(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.depth01List(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> depth02CardList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.depth02CardList(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> depth03CardList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.depth03CardList(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> depth02DayList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.depth02DayList(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> depth03DayList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.depth03DayList(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> depth02DivList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.depth02DivList(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> depth03DivList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.depth03DivList(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> latestData(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.latestData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> smsLatestData(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.smsLatestData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> latestDetailData(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.latestDetailData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> affliateData(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.affliateData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> startCurData(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.startCurData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> startUserData(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.startUserData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> startSumData(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.startSumData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> startRecYnData(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.startRecYnData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public String eMailChk(String telNo) {
		return receiptDAO.eMailChk(telNo);
	}

	@Override
	public String uPlusChk(String telNo) {
		return receiptDAO.uPlusChk(telNo);
	}

	@Override
	public String pushChk(String eMailChk) {
		return receiptDAO.pushChk(eMailChk);
	}

	@Override
	public Integer joinChkId(String eMail) {
		Integer identify = receiptDAO.joinChkId(eMail);

		return identify;
	}

	@Override
	public void latestUpdateData(Map<String, Object> map) throws Exception {
		receiptDAO.latestUpdateData(map);
	}

	@Override
	public Map<String, Object> houseHold(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.houseHold(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> houseHoldDetail(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.houseHoldDetail(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> monthChartData(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.monthChartData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> dayDivChartData(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.dayDivChartData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> instDivChartData(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.instDivChartData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> dayOfMonthChartData(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.dayOfMonthChartData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public void updatePushYn(Map<String, Object> map) throws Exception {
		receiptDAO.updatePushYn(map);
	}

	@Override
	public void settingChkPw(Map<String, Object> map) throws Exception {
		receiptDAO.settingChkPw(map);
	}

	@Override
	public Map<String, Object> eventBoardList(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.eventBoardList(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}
	
	@Override
	public HashMap<String, Object> monthAllDate(HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.monthAllDate(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}

	@Override
	public Map<String, Object> receiveUplusDtlData(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// List<Map<String,Object>> list = receiptDAO.receiveUplusDtlData(map);
		/*
		 * resultMap.put("resultMap", receiptDAO.receiveUplusDtlData(map));
		 * log.debug("resultMapIMPL:"+resultMap);
		 */
		// return resultMap;
		return receiptDAO.receiveUplusDtlData(map);
	}

	@Override
	public List<Map<String, Object>> receiveUplusDtlArrayData(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// List<Map<String,Object>> list = receiptDAO.receiveUplusDtlData(map);
		/*
		 * resultMap.put("resultMap", receiptDAO.receiveUplusDtlData(map));
		 * log.debug("resultMapIMPL:"+resultMap);
		 */
		// return resultMap;
		List<Map<String, Object>> list = receiptDAO.receiveUplusDtlArrayData(map);

		resultMap.put("resultMap", list);
		return list;
	}

	@Override
	public Integer selectUplusJoinChk(HashMap<String, Object> map) {
		return receiptDAO.selectUplusJoinChk(map);
	}
	
	@Override
	public Integer selectUsedTelChk(HashMap<String, Object> map) {
		return receiptDAO.selectUsedTelChk(map);
	}  
	
	@Override
	public String selectEmail(HashMap<String, Object> map) {
		return receiptDAO.selectEmail(map);
	}  
	
	@Override
	public void updateUsedData(HashMap<String, Object> map) {
		receiptDAO.updateUsedData(map);
	}  
	
	@Override
	public void receiveUplusJoin(HashMap<String, Object> map) {
		receiptDAO.receiveUplusJoin(map);
	}
	
	@Override
	public String originUplusUserKey(HashMap<String, Object> map) {
		return receiptDAO.originUplusUserKey(map); 
	}
	
	@Override
	public void updateUplusData(HashMap<String, Object> map) {
		receiptDAO.updateUplusData(map); 
	}
	
	@Override
	public Integer updateUplusDrop(HashMap<String, Object> map) {
		return receiptDAO.updateUplusDrop(map);
	}  

	@Override
	public String getSalesType(HashMap<String, Object> map) {
		return receiptDAO.getSalesType(map);
	}
	
	@Override
	public Map<String, Object> affDetail(Map<String, Object> map) {
		Map<String, Object> resultMap =   receiptDAO.affDetail(map);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> couponList(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.couponList(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> couponRead(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = receiptDAO.couponRead(map); 
		return resultMap;
	}
	
	@Override
	public void coucntAdd(HashMap<String, Object> map) throws Exception {
		receiptDAO.coucntAdd(map); 
		
	}
	
	@Override
	public Integer valiCoupon(HashMap<String, Object> map) {
		return receiptDAO.valiCoupon(map);
	}
	
	@Override
	public void couponAdd(HashMap<String, Object> map) {
		receiptDAO.couponAdd(map);
	}
	
	@Override
	public Map<String, Object> couponDown(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = receiptDAO.couponDown(map); 
		return resultMap;
	}
	
	@Override
	public Map<String, Object> couponDetail(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = receiptDAO.couponDetail(map); 
		return resultMap;
	}
	
	
	@Override
	public Map<String, Object> couponMyList(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.couponMyList(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> cancleReceipt(HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = receiptDAO.cancleReceipt(map); 
		return resultMap;
	}
	
	@Override
	public void couponUsing(HashMap<String, Object> map) {
		receiptDAO.couponUsing(map); 
	}
	
	//uplus
	
	public Map<String, Object> uplusReceiptData(Map<String,Object> map) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.uplusReceiptData(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getShopInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = receiptDAO.getShopInfo(map); 
		return resultMap;
	}
	
	public Map<String, Object> uplusReceiptDetail(Map<String,Object> map) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.uplusReceiptDetail(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}
	
	public Map<String, Object> getDetailReceipt(Map<String,Object> map) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = receiptDAO.getDetailReceipt(map);
		resultMap.put("resultMap", list);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> latestDataEmail(List<String> list) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
	      List<Map<String, Object>> list2 = receiptDAO.latestDataEmail(list);
	      resultMap.put("resultMap", list2);  
	      return resultMap;  
	}

	
	@Override
	public Map<String, Object> latestDataEmailDetail(List<String> list) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
	      List<Map<String, Object>> list2 = receiptDAO.latestDataEmailDetail(list);
	      resultMap.put("resultMap", list2);  
	      return resultMap;  
	}

	
}
