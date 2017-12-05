package first.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;

public class TestMapToList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        HashMap<String, String> studentPerformanceMap = new HashMap<String, String>();
        
        //Adding elements to HashMap
         
        studentPerformanceMap.put("John Kevin", "Average");
         
        studentPerformanceMap.put("Rakesh Sharma", "Good");
         
        studentPerformanceMap.put("Prachi D", "Very Good");
         
        studentPerformanceMap.put("Ivan Jose", "Very Bad");
         
        studentPerformanceMap.put("Smith Jacob", "Very Good");
         
        studentPerformanceMap.put("Anjali N", "Bad");
         
        //Getting Set of keys
         
        Set<String> keySet = studentPerformanceMap.keySet();
         
        //Creating an ArrayList of keys
         
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
         
        System.out.println(listOfKeys);
        System.out.println("ArrayList Of Keys :");
         
        for (String key : listOfKeys) 
        {
            System.out.println(key);
        }
         
        System.out.println("--------------------------");
         
        //Getting Collection of values
         
        Collection<String> values = studentPerformanceMap.values();
         
        //Creating an ArrayList of values
         
        ArrayList<String> listOfValues = new ArrayList<String>(values);
         
        System.out.println("ArrayList Of Values :");
         
        for (String value : listOfValues) 
        {
            System.out.println(value);
        }
         
        System.out.println("--------------------------");
         
        //Getting the Set of entries
         
        Set<Entry<String, String>> entrySet = studentPerformanceMap.entrySet();
         
        //Creating an ArrayList Of Entry objects
         
        ArrayList<Entry<String, String>> listOfEntry = new ArrayList<Entry<String,String>>(entrySet);
         
        System.out.println("ArrayList of Key-Values :");
        System.out.println(listOfEntry);
         
        for (Entry<String, String> entry : listOfEntry) 
        {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }		
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		String erecNo = "2017020810001";
		String userKey = "U20170228132038107125";		
		HashMap<String,Object> dtlmap = new HashMap<String,Object>();
		String erecNo2 = "2017020810001";
		String userKey2 = "U20170228132038107125";		
		map.put("erecNo", erecNo);
		map.put("userKey", userKey);
		dtlmap.put("erecNo2", erecNo2);
		dtlmap.put("userKey2", userKey2);
		map.put("test", dtlmap);
		
       HashMap<String,Object> map2 = new HashMap<String,Object>();
        System.out.println("\n @@@@@@"+map);
        map.put("test1212",listOfEntry);
        System.out.println("\n11111111111111"+map.get("mbrNo"));		
		
		
		Gson gson = new Gson(); 
		String json = gson.toJson(map);		
		System.out.println(json);
		
		
/*		
	    Map<String, Object> mapobject=new HashMap<String, Object>();
	    //임의로 여러개의 KEY값을 MAP 객체에 PUT
	    for(int i=0;i<3;i++){
	        mapobject.put("key"+i, i+24);
	    }
	    System.out.println(mapobject);
	    //MAP의 KEY값을 이용하여 VALUE값 가져오기
	    for (String mapkey : mapobject.keySet()){
	        System.out.println("key:"+mapkey+",value:"+mapobject.get(mapkey));
	    }
*/
	    
	    
	    
	    
	    
	    
	    
		
	}

}
