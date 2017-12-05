package com.company.first;

import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.fabric.xmlrpc.base.Array;

public class TestJava {
	public static void main(String[] args) {
		
		printMap();
		
	}
	
	public static void printMap(){
		
		HashMap<String, String> testMap = new HashMap<String, String >();
		
		testMap.put("test", "test12123");
		
		System.out.println(testMap);
		
		System.out.println(testMap.get("test"));
		
		System.out.println(printTest(10,6));
		
	}	
	
	public static int printTest(int i, int j){
		
		Array test = new Array();
		
		System.out.println("test.getData():"+test.getData());
		
		ArrayList<String> ar = new ArrayList<String>();
		
		String s1 ="Test1";
		String s2 ="Test2";
		String s3 ="Test3";
		String s4 ="Test4";
		
		ar.add(s1);
		ar.add(s2);
		ar.add(s3);
		ar.add(s4);

		System.out.println("ar:"+ar);
		System.out.println(ar.get(0));

		String[] members = new String[4];
		
		int i0 = 0;
		int i1 = 1;
		int i2 = 2;
		int i3 = 3;
		
		members[i0] = "1";
		members[i1] = "2";
		members[i2] = "3";
		members[i3] = "4";
		
		System.out.println("member0:"+members[0]);
		
		System.out.println("member1:"+members[1]);
		
		System.out.println("member2:"+members[2]);
		
		System.out.println("member3:"+members[3]);
		
		for(int k = 0; k<i; k++){
			
			System.out.println("k:"+k);
			
			j = k;
			
		}
		
		return j; 
	}
}
