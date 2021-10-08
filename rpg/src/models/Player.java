package models;

import java.util.ArrayList;

public class Player {
	public static int log = -1;
	private String id;
	private String pw;
	private int gold;
	private ArrayList<Unit> guildMember = null;
	private ArrayList<Item> myItem = null;
	
	public Player(String id, String pw) {
		this.guildMember = new ArrayList<Unit>();
		this.myItem = new ArrayList<Item>();
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
	
	public Item getMyItem(int idx) {
		return this.myItem.get(idx);
	}
	
	public ArrayList<Item> getMyItems(){
		return this.myItem;
	}
	
	public int guildMemberSize() {
		return this.guildMember.size();
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void removeGuildMember(int idx) {
		this.guildMember.remove(idx);
	}
	
	@Override
	public String toString() {
		String data = "";
		
		data += "ID: " + this.id + " ";
		data += "PW: " + this.pw + " ";
		
		return data;
	}
}
