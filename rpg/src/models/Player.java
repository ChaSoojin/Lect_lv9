package models;

import java.util.ArrayList;

public class Player {
	private int gold;
	private ArrayList<Unit> guildMember = null;
	
	public Player() {
		this.guildMember = new ArrayList<Unit>();
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
}
