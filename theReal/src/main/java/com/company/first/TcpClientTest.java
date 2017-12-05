package com.company.first;
//TcpClientTest.java

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.Socket;

public class TcpClientTest {
	public static void main(String[] args) {
		try {
			String serverIP = "127.0.0.1"; // 127.0.0.1 & localhost 본인
			System.out.println("서버에 연결중입니다. 서버 IP : " + serverIP);
			System.out.println("Total Memory : " + Runtime.getRuntime().totalMemory());
			System.out.println("Free Memory : " + Runtime.getRuntime().freeMemory());
			System.out.println(
					"Used Memory : " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));

			// 소켓을 생성하여 연결을 요청한다.
			Socket socket = new Socket(serverIP, 5010);

			/*
			 * 
			 * Packet packet = new Packet(); Item item1 = Item.create("이름", 10,
			 * "설유진"); Item item2 = Item.create("전화번호", 11, "01071064573"); Item
			 * item3 = Item.create("이메일", 20, "god870225@naver.com"); Item item4
			 * = Item.create("테스트", 10, "1234567"); Item item5 =
			 * Item.create("줄바꿈", 1, "\r"); Item item6 = Item.create("이름1", 10,
			 * "설유진1"); Item item7 = Item.create("전화번호2", 11, "01071064573");
			 * Item item8 = Item.create("이메일3", 20, "god870225@naver.com"); Item
			 * item9 = Item.create("테스트4", 10, "1234567"); Item item10 =
			 * Item.create("줄바꿈5", 4, "\r"); packet.addItem(item1);
			 * packet.addItem(item2); packet.addItem(item3);
			 * packet.addItem(item4); packet.addItem(item5);
			 * packet.addItem(item6); packet.addItem(item7);
			 * packet.addItem(item8); packet.addItem(item9);
			 * packet.addItem(item10); System.out.println(packet.raw());
			 * 
			 */

			// 소켓의 입력스트림을 얻는다.

			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in); // 기본형 단위로 처리하는 보조스트림

			// 소켓으로 부터 받은 데이터를 출력한다.
			System.out.println("서버로부터 받은 메세지 : " + dis.readUTF());
			System.out.println("연결을 종료합니다.");

			// 스트림과 소켓을 닫는다.
			dis.close();
			socket.close();

		} catch (ConnectException ce) {

			ce.printStackTrace();

		} catch (IOException ie) {

			ie.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} // try - catch
	} // main
} // TcpClientTest

/*
 *
 * 결과
 * 
 * 서버에 연결중입니다. 서버 IP : 127.0.0.1 서버로부터 받은 메세지 : 서버로부터의 메세지입니다. 연결을 종료합니다.
 * 
 */