package Controller;

import java.util.Random;
import java.util.Scanner;
import models.Player;

public class Guild {
	public static Guild instance = new Guild();
	public static Scanner sc = new Scanner(System.in);
	public static Random ran = new Random();
	private Player player = new Player();
	
	public void showGuildList() {
		System.out.println("°ñµå: " + player.getGold());
		for(int i = 0; i < player.guildMemberSize(); i++) {
			System.out.print((i+1) + ". ");
			player.getGuildMember(i).showUnit();
		}
	}

	public void addGuildMember() {
		String[] last = { "¹Ú", "ÀÌ", "±è", "ÃÖ", "À¯", "Áö", "¿À"};
		String[] middle = { "A", "B", "C", "D", "E", "F", "G"};
		String[] first = { "H", "I", "J", "K", "L", "M", "N"};
		
		
	}

	public void removeGuildMember() {
		
	}

	public void changePartyMember() {
		
	}

	public void sort() {
		
	}
}
