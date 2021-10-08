package models;

import java.util.ArrayList;

import Controller.Guild;

public class Player {
	public static int log = -1;
	public Guild guild = Guild.instance;
	private String id;
	private String pw;
	private int gold;
	private ArrayList<Unit> guildMember = null;
	private ArrayList<Item> myItem = null;
	
	public Player() {
		this.guildMember = new ArrayList<Unit>();
		this.myItem = new ArrayList<Item>();
		gold = 5000;		
		guild.setGuild();
		init();
	}
	
	private void init() {
		this.guildMember.add(new Unit("È£¶ûÀÌ", 1, 100, 10, 5, 0));
		this.guildMember.add(new Unit("°­¾ÆÁö", 1, 80, 7, 3, 0));
		this.guildMember.add(new Unit("»ç½¿", 1, 50, 3, 1, 0));
		this.guildMember.add(new Unit("µÎ´õÁö", 1, 70, 5, 2, 0));
		this.guildMember.add(new Unit("µÅÁö", 1, 200, 4, 8, 0));
		this.guildMember.add(new Unit("»çÀÚ", 1, 120, 11, 7, 0));
	}
	
	public Player(String id, String pw) {
		this.id = id;
		this.pw = pw;
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
