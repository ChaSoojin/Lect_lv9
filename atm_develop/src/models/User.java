package models;

import java.util.ArrayList;

public class User {
	private long code;
	private String id;
	private String pw;
	private String name;
	private ArrayList<Account> accs = null;
	
	public User(long code, String id, String pw, String name) {
		this.code = code;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.accs = new ArrayList<Account>();
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
	
	public Account getAcc(int idx) {
		return this.accs.get(idx);
	}
	
	public ArrayList<Account> getAccs(){
		return this.accs;
	}
	
	public int getAccsSize() {
		return this.accs.size();
	}	
	
	public void clearAccs() {
		this.accs.clear();
	}
}
