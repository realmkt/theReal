package com.company.first;

import java.io.*;
import java.net.*;

class TestData {

	byte[] IUD = new byte[4]; // 0.I(insert) 1.U(update 2.D(delete)
	byte[] GM_TR = new byte[8]; // GMSH0001
	byte[] gubn = new byte[4]; // 0.시장동향 1.리포트
	byte[] subg = new byte[4]; // 세부구분
	byte[] date = new byte[8]; // 입력일자(KEY)
	byte[] seqn = new byte[8]; // 일련번호(=입력시간)
	byte[] dept = new byte[20]; // 입력부서
	byte[] iemp = new byte[20]; // 입력자
	byte[] cod2 = new byte[12]; // 종목코드
	byte[] title = new byte[80]; // 제목
	byte[] sour = new byte[20]; // 자료제공원
	byte[] html = new byte[128]; // HTML PATH
	byte[] sugg = new byte[80]; // 의견

	// 디폴트 생성자
	public TestData() {
	}

	// 실제데이터값으로 초기화 생성자
	public TestData(String IUD, String GM_TR, String gubn, String subg, String date, String seqn, String dept,
			String iemp, String cod2, String title, String sour, String html, String sugg)
					throws UnsupportedEncodingException {

		setData(this.IUD, IUD);
		setData(this.GM_TR, GM_TR);
		setData(this.gubn, gubn);
		setData(this.subg, subg);
		setData(this.date, date);
		setData(this.seqn, seqn);
		setData(this.dept, dept);
		setData(this.iemp, iemp);
		setData(this.cod2, cod2);
		setData(this.title, title);
		setData(this.sour, sour);
		setData(this.html, html);
		setData(this.sugg, sugg);

	}

	/** 필드값들을 설정하고 빈배열을 공백으로 채운다. */
	public void setData(byte[] arryByte, String str) throws UnsupportedEncodingException {

		// 문자열 값이 널이면 전부 공백으로 채우기 위해
		if (str == null) {
			str = "";
		}

		// 필드에 채울 문자열을 바이트배열로 변환
		byte[] bytes = str.getBytes("EUC_KR");

		// 실제데이터 값의 바이트배열길이
		int endIdx = 0;

		// 실제데이터 바이트배열 설정
		if (arryByte.length >= bytes.length) {
			endIdx = bytes.length; // 필드의 배열이 실제데이터배열보다 크거나 같을 경우
		} else {
			endIdx = arryByte.length; // 필드의 배열이 실제데이터배열보다 작을 경우
		}

		// 필드배열에 실제데이터배열값으로 채운다.
		for (int i = 0; i < endIdx; i++) {
			arryByte[i] = bytes[i];
		}
		// 실제데이터값이 채워지지 않은 배열은 공백으로 채운다.
		for (int j = endIdx; j < arryByte.length; j++) {
			arryByte[j] = ' ';
		}
	}

	public String getData(byte[] arryByte) throws UnsupportedEncodingException {

		if (arryByte == null) {
			return "";
		}

		return new String(arryByte, "EUC-KR");

	}

	public void writeDataExternal(java.io.DataOutputStream stream) throws IOException {

		stream.write(IUD);
		stream.write(GM_TR);
		stream.write(gubn);
		stream.write(subg);
		stream.write(date);
		stream.write(seqn);
		stream.write(dept);
		stream.write(iemp);
		stream.write(cod2);
		stream.write(title);
		stream.write(sour);
		stream.write(html);
		stream.write(sugg);
	}

	public void readDataExternal(java.io.DataInputStream stream) throws IOException {

		stream.read(IUD, 0, IUD.length);
		stream.read(GM_TR, 0, GM_TR.length);
		stream.read(gubn, 0, gubn.length);
		stream.read(subg, 0, subg.length);
		stream.read(date, 0, date.length);
		stream.read(seqn, 0, seqn.length);
		stream.read(dept, 0, dept.length);
		stream.read(iemp, 0, iemp.length);
		stream.read(cod2, 0, cod2.length);
		stream.read(title, 0, title.length);
		stream.read(sour, 0, sour.length);
		stream.read(html, 0, html.length);
		stream.read(sugg, 0, sugg.length);
	}

	public void print() throws IOException {

		System.out.println("IDU: " + getData(IUD) + "\tSize:" + IUD.length);
		System.out.println("GM_TR: " + getData(GM_TR) + "\tSize:" + GM_TR.length);
		System.out.println("gubn: " + getData(gubn) + "\tSize:" + gubn.length);
		System.out.println("subg: " + getData(subg) + "\tSize:" + subg.length);
		System.out.println("date: " + getData(date) + "\tSize:" + date.length);
		System.out.println("seqn: " + getData(seqn) + "\tSize:" + seqn.length);
		System.out.println("dept: " + getData(dept) + "\tSize:" + dept.length);
		System.out.println("iemp: " + getData(iemp) + "\tSize:" + iemp.length);
		System.out.println("cod2: " + getData(cod2) + "\tSize:" + cod2.length);
		System.out.println("title: " + getData(title) + "\tSize:" + title.length);
		System.out.println("sour: " + getData(sour) + "\tSize:" + sour.length);
		System.out.println("html: " + getData(html) + "\tSize:" + html.length);
		System.out.println("sugg: " + getData(sugg) + "\tSize:" + sugg.length);

	}

}

class TestClient2 {
	public static void usage() {
		System.out.println("\nUsage : java TestClient server port");
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			usage();
			System.exit(1);
		}

		Socket socket = null;
		try {
			socket = new Socket(args[0], Integer.parseInt(args[1]));
		} catch (IOException ie) {
			System.out.println("cannot establish socket connection to " + args[0] + ":" + args[1] + " - " + ie);
			System.exit(2);
		}

		TestData td = new TestData("I", "GMSH0001", "0", "0001", "20020930", "09000000", "트레이딩", "박대박", "123456789012",
				"대박을 건지자!", "검은손", "/best/good.pdf", "니들이 대박맛을 알어!");

		TestData td1 = new TestData();
		TestData td2 = new TestData();

		System.out.println("td2" + td);
		System.out.println("td2" + td1);
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		System.out.println("td2" + td2);
		System.out.println("in" + in);
		System.out.println("out:" + out);

		try {
			// first write to server socket
			td1.print();
			td1.writeDataExternal(out);
			out.flush();
			System.out.println("write to socket server ends");

			// later, read from server socket
			System.out.println("0");
			td2.readDataExternal(in);
			System.out.println("1");
			td2.print();
			System.out.println("read from socket server ends");
		} finally {
			out.close();
			in.close();
			socket.close();
		}
	}
}
