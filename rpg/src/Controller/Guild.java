package Controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import models.Player;
import models.Unit;

public class Guild {
	public static Guild instance = new Guild();
	public static Scanner sc = new Scanner(System.in);
	public static Random ran = new Random();
	private ArrayList<Unit> units = null;
	private PlayerController pc = PlayerController.instance;
	
	private Guild() {
		this.units = new ArrayList<Unit>();
	}
	
	public void showGuildList() {
		Player me = getPlayer();
		
		System.out.println("골드: " + me.getGold());
		for(int i = 0; i < me.guildMemberSize(); i++) {
			System.out.print((i+1) + ". ");
			me.getGuildMember(i).showUnit();
		}
	}

	public void addGuildMember() {
		String name = setRandomName();
		int level = 1;
		int maxHp = ran.nextInt(201) + 50;
		int def = ran.nextInt(11) + 3;
		int off = ran.nextInt(11) + 3;
		int exp = 0;

		Unit newUnit = new Unit(name, level, maxHp, def, off, exp);
		this.units.add(newUnit);
		getPlayer().getGuildMemberList().add(newUnit);
		
		int size = getPlayer().guildMemberSize();
		getPlayer().getGuildMember(size-1).showUnit();
		System.out.println("길드원을 추가합니다.");
	}
	
	private Player getPlayer() {
		return pc.getPlayer(Player.log);
	}
	
	private String setRandomName() {
		int size = 7;
		String[] last = { "박", "이", "김", "최", "유", "지", "오"};
		String[] middle = { "A", "B", "C", "D", "E", "F", "G"};
		String[] first = { "H", "I", "J", "K", "L", "M", "N"};
		String name = "";
		
		while(true) {
			int ranLast = ran.nextInt(size);
			int ranMiddle = ran.nextInt(size);
			int ranFirst = ran.nextInt(size);

			name = "";
			name += last[ranLast] + middle[ranMiddle] + first[ranFirst];
			boolean chk = true;
			
			for(Unit unit : this.units) {
				if(unit.getName().equals(name)) chk = false;
			}
			
			if(chk) break;
		}	
		return name;
	}
	
	public void removeGuildMember() {
		showAllGuildMember();
		int size = getPlayer().guildMemberSize();
		System.out.printf("삭제할 길드원[1~%d]: ", size);
		String select = sc.next();
		
		try {
			int sel = Integer.parseInt(select) - 1;
			
			if(sel >= 0 && sel < size) {
				System.out.printf("[이름 : %s] 길드원을 삭제합니다.\n", getPlayer().getGuildMember(sel).getName());
			}
			else System.out.println("[실패] 잘못 선택하셨습니다.");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void changePartyMember() {
		int number = 0;
		boolean chk = false;
		for(Unit unit : getPlayer().getGuildMemberList()) {
			if(unit.getParty()) {
				System.out.print(++number + ". ");
				unit.showUnit();
				chk = true;
			}
		}
		
		if(chk) {
			System.out.printf("교체할 파티원 [1~%d]: ", number);
			String select = sc.next();
			
			try {
				int sel = Integer.parseInt(select) - 1;
				
				if(sel >= 0 && sel < number) {
					showAllGuildMember();
					int size = getPlayer().guildMemberSize();
					System.out.printf("참가할 길드원[1~%d]: ", size);
					String select2 = sc.next();
					
					try {
						int sel2 = Integer.parseInt(select2) - 1;
						
						if(sel2 >= 0 && sel2 < size) {
							String name = getPlayer().getGuildMember(sel).getName();//바꿔
							String name2 = getPlayer().getGuildMember(sel2).getName();
							
							System.out.printf("[이름 : %s] 에서 [이름 : %s] 로 길드원을 교체합니다.\n", name, name2);
						}
						else System.out.println("[실패] 잘못 선택하셨습니다.");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				else System.out.println("[실패] 잘못 선택하셨습니다.");
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void sort() {
		
	}
	
	private void showAllGuildMember() {
		int numbering = 1;
		for(Unit unit : getPlayer().getGuildMemberList()) {
			System.out.print(numbering++ + ". ");
			unit.showUnit();
		}
	}
}
