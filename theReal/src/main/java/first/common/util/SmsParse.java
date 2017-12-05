package first.common.util;

import java.util.HashMap;

public class SmsParse {

	public static void main(String[] args) {

		HashMap<String,String> map = new HashMap<String,String>();
		String divAppCo = "주유";
		String divAppCoCd = "";
		if(divAppCo.contains("CGV") || divAppCo.contains("롯데시네마")||divAppCo.contains("메가박스")||divAppCo.contains("아트홀")){
			divAppCoCd = "A01";
		}else if(divAppCo.contains("헤어")||divAppCo.contains("네일아트")||divAppCo.contains("타투")||divAppCo.contains("이발소")||divAppCo.contains("미용실")||divAppCo.contains("블루클럽")||divAppCo.contains("뷰티")||divAppCo.contains("살롱")||divAppCo.contains("HAIR")){
			divAppCoCd = "B01";
		}else if(divAppCo.contains("스타벅스") || divAppCo.contains("탐앤탐스")||divAppCo.contains("엔젤리너스")||divAppCo.contains("이디아")||divAppCo.contains("달콤") || divAppCo.contains("커피빈") || divAppCo.contains("커핀그루나루")||divAppCo.contains("카페베네")||divAppCo.contains("투썸")||divAppCo.contains("커피에반하다") || divAppCo.contains("빠리바게트") || divAppCo.contains("뚜레주르")||divAppCo.contains("베이커리")||divAppCo.contains("커피")||divAppCo.contains("카페")){
			divAppCoCd = "C01";
		}else if(divAppCo.contains("GS칼텍스")||divAppCo.contains("SK주유소")||divAppCo.contains("오일뱅크")||divAppCo.contains("E1")||divAppCo.contains("LPG")||divAppCo.contains("주유")){
			divAppCoCd = "E01";
		}else if(divAppCo.contains("냉면") || divAppCo.contains("부대찌개")||divAppCo.contains("순대국")||divAppCo.contains("피자")||divAppCo.contains("짬뽕")||divAppCo.contains("짜장면") || divAppCo.contains("자장면")||divAppCo.contains("떡볶이")||divAppCo.contains("김밥")||divAppCo.contains("찜닭")||divAppCo.contains("국수") || divAppCo.contains("만두")||divAppCo.contains("돈까스")||divAppCo.contains("초밥")||divAppCo.contains("스시")||divAppCo.contains("짜장면") || divAppCo.contains("파스타")||divAppCo.contains("치킨")||divAppCo.contains("호프")||divAppCo.contains("곱창")||divAppCo.contains("닭발")||divAppCo.contains("짜장면") || divAppCo.contains("쭈꾸미")||divAppCo.contains("갈비")||divAppCo.contains("삼겹살")||divAppCo.contains("족발")||divAppCo.contains("보쌈")){
			divAppCoCd = "E02";
		}else if(divAppCo.contains("베니건스") || divAppCo.contains("아웃백")||divAppCo.contains("TGI")||divAppCo.contains("드마리스")||divAppCo.contains("VIPS")||divAppCo.contains("빕스") || divAppCo.contains("애슐리")||divAppCo.contains("블랙스미스")||divAppCo.contains("세븐스프링스")||divAppCo.contains("마이어스")||divAppCo.contains("샤브향") || divAppCo.contains("마루샤브")||divAppCo.contains("스시앤그릴")||divAppCo.contains("바이킹스")||divAppCo.contains("보노보노")||divAppCo.contains("메드포갈릭")){
			divAppCoCd = "F01";
		}else if(divAppCo.contains("세븐일레븐")||divAppCo.contains("GS25")||divAppCo.contains("미니스톱")||divAppCo.contains("롯데마트")||divAppCo.contains("이마트")||divAppCo.contains("편의점")||divAppCo.contains("마트")||divAppCo.contains("코스트코")||divAppCo.contains("홈플러스")||divAppCo.contains("CU")||divAppCo.contains("위드미")||divAppCo.contains("바이더웨이")){
			divAppCoCd = "M01";
		}else if(divAppCo.contains("롯데백화점")||divAppCo.contains("신세계")||divAppCo.contains("2002아울렛")||divAppCo.contains("현대아울렛")||divAppCo.contains("라붐아울렛")||divAppCo.contains("뉴코어아울렛")||divAppCo.contains("마리오아울렛")||divAppCo.contains("이케아")||divAppCo.contains("타임스퀘어")||divAppCo.contains("마리오아울렛")){
			divAppCoCd = "S01";
		}else if(divAppCo.contains("약국")||divAppCo.contains("병원")){
			divAppCoCd = "M02";
		}else{
			divAppCoCd = "ETC";
		}
		System.out.println(map);
	}

}
