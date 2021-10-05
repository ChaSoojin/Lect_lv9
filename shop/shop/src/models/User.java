package models;

import java.util.ArrayList;

public class User {
	public static int log = -1;
	private long code;
	private String id;
	private String pw;
	private String name;
	private ArrayList<Cart> jangs = null;
	
	public User(long code, String id, String pw, String name) {
		this.code = code;
		this.id = id;
		this.pw = pw;
		this.name = name;
		jangs = new ArrayList<Cart>();
	}
	
	public long getCode() {
		return this.code;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPw() {
		return this.pw;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Cart> getJang(){
		return this.jangs;
	}
	
	public void addJang(Cart cart) {
		this.jangs.add(cart);
	}
	
	public void removeJang(int idx) {
		this.jangs.remove(idx);
	}
	
	public void clearJang() {
		this.jangs.clear();
	}
	
	public void printUserData() {
		System.out.printf("유저코드:%d 이름:%s\n", this.code, this.name);
	}
	
	@Override
	public String toString() {
		String data = "";
		
		data += "코드:" + this.code + " ";
		data += "ID: " + this.id + " ";
		data += "PW: " + this.pw + " ";
		data += "이름: " + this.name;
		
		return data;
	}
}
