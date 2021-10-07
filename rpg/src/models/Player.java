package models;

import java.util.ArrayList;

public class Player {
	public static int log = -1;
	private String id;
	private String pw;
	private int gold;
	private ArrayList<Unit> guildMember = null;
	
	public Player(String id, String pw) {
		this.guildMember = new ArrayList<Unit>();
		this.id = id;
		this.pw = pw;
		gold = 5000;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPw() {
		return this.pw;
	}
	
	public int getGold() {
		return this.gold;
	}
	
	public Unit getGuildMember(int idx) {
		return this.guildMember.get(idx);
	}
	
	public ArrayList<Unit> getGuildMemberList(){
		return this.guildMember;
	}
	
	public int guildMemberSize() {
		return this.guildMember.size();
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	@Override
	public String toString() {
		String data = "";
		
		data += "ID: " + this.id + " ";
		data += "PW: " + this.pw + " ";
		
		return data;
	}
}
