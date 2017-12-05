package com.company.first;
import java.util.ArrayList;
import java.util.HashMap;

public class Packet {
    private ArrayList<Item> items = new ArrayList<Item>();
    private HashMap<String, Item> nameAccess = new HashMap<String, Item>();

    public void addItem(Item item) {
        this.items.add(item);
        if(nameAccess.containsKey(item.getName())) {
            throw new RuntimeException(
                    "Duplicated item name:["+item.getName()+"]");
        }
        nameAccess.put(item.getName(), item);
    }

    public Item getItem(int index) {
        return this.items.get(index);
    }

    public Item getItem(String name) {
        return nameAccess.get(name);
    }

    public String raw() {
        StringBuffer result = new StringBuffer();
        for(Item item: items) {
            result.append(item.raw());
        }
        return result.toString();
    }
    
    public void parse(String data) {
        byte[] bdata = data.getBytes();
        int pos = 0;
        for(Item item: items) {
            byte[] temp = new byte[item.getLength()];
            System.arraycopy(bdata, pos, temp, 0, item.getLength());
            pos += item.getLength();
            item.setValue(new String(temp));
        }
    }

    public static void main(String[] args) {
        Packet packet = new Packet();
        Item item1 = Item.create("이름", 10, "설유진");
        Item item2 = Item.create("전화번호", 11, "01071064573");
        Item item3 = Item.create("이메일", 20, "god870225@naver.com");
        Item item4 = Item.create("테스트", 10, "1234567");
        Item item5 = Item.create("줄바꿈", 1, "\r");
        Item item6 = Item.create("이름1", 10, "설유진1");
        Item item7 = Item.create("전화번호2", 11, "01071064573");
        Item item8 = Item.create("이메일3", 20, "god870225@naver.com");
        Item item9 = Item.create("테스트4", 10, "1234567");
        Item item10 = Item.create("줄바꿈5", 4, "\r");
        packet.addItem(item1);
        packet.addItem(item2);
        packet.addItem(item3);
        packet.addItem(item4);
        packet.addItem(item5);
        packet.addItem(item6);
        packet.addItem(item7);
        packet.addItem(item8);
        packet.addItem(item9);
        packet.addItem(item10);
        System.out.println(packet.raw());
        Packet recvPacket = new Packet();
        recvPacket.addItem(Item.create("생일", 8, null));
        recvPacket.addItem(Item.create("주소", 30, null));
        recvPacket.parse("19801215서울시 송파구 잠실동 123-3    ");

        System.out.println(recvPacket.getItem("생일").raw());
        System.out.println(recvPacket.getItem("주소").raw());
        System.out.println();
    }
}