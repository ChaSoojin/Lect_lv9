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
		
		System.out.println("---------------- ���� ����Ʈ ----------------");
		System.out.println("���: " + me.getGold());
		int numbering = 1;
		for(Unit unit : getPlayer().getGuildMemberList()) {
			System.out.print(numbering++ + ". ");
			unit.showUnit();
		}
		System.out.println();
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
		System.out.println("������ �߰��մϴ�.");
	}
	
	private Player getPlayer() {
		return pc.getPlayer(Player.log);
	}
	
	private String setRandomName() {
		int size = 7;
		String[] last = { "��", "��", "��", "��", "��", "��", "��"};
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
		showGuildList();
		int size = getPlayer().guildMemberSize();
		System.out.printf("������ ����[1~%d]: ", size);
		String select = sc.next();
		
		try {
			int sel = Integer.parseInt(select) - 1;
			
			if(sel >= 0 && sel < size) {
				System.out.printf("[�̸� : %s] ������ �����մϴ�.\n", getPlayer().getGuildMember(sel).getName());
				this.units.remove(sel);
				pc.removeGuildMember(Player.log, sel);
			}
			else System.out.println("[����] �߸� �����ϼ̽��ϴ�.");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void changePartyMember() {
		boolean chk = false;
		ArrayList<Unit> partyUnit = getPartyMember();
		
		if(partyUnit.size() > 0) {
			chk = true;			
			
			for(Unit unit : partyUnit) {
				unit.showUnit();
			}
		}
				
		if(chk) {
			System.out.printf("��ü�� ��Ƽ�� [1~%d]: ", partyUnit.size());
			String select = sc.next();
			
			try {
				int sel = Integer.parseInt(select) - 1;
				
				if(sel >= 0 && sel < partyUnit.size()) {
					showGuildList();
					int size = getPlayer().guildMemberSize();
					System.out.printf("������ ����[1~%d]: ", size);
					String select2 = sc.next();
					
					try {
						int sel2 = Integer.parseInt(select2) - 1;
						
						if(sel2 >= 0 && sel2 < size) {
							int idx = findIdx(partyUnit.get(sel).getName());
							String name = getPlayer().getGuildMember(idx).getName();
							String name2 = getPlayer().getGuildMember(sel2).getName();
							
							getPlayer().getGuildMember(idx).setParty(false);
							getPlayer().getGuildMember(sel2).setParty(true);
							System.out.printf("[�̸� : %s] ���� [�̸� : %s] �� ��Ƽ���� ��ü�մϴ�.\n", name, name2);
						}
						else System.out.println("[����] �߸� �����ϼ̽��ϴ�.");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				else System.out.println("[����] �߸� �����ϼ̽��ϴ�.");
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		else randomAddPartyMember();
	}

	private int findIdx(String name) {
		int idx = -1;
		for(int i = 0; i < this.units.size(); i++) {
			if(this.units.get(i).getName().equals(name)) idx = i;
		}
		
		return idx;
	}
	
	private ArrayList<Unit> getPartyMember(){
		ArrayList<Unit> partyUnit = new ArrayList<Unit>();
		
		for(Unit unit : getPlayer().getGuildMemberList()) {
			if(unit.getParty()) partyUnit.add(unit);
		}
		
		return partyUnit;
	}
	
	private void randomAddPartyMember() {
		System.out.println("��Ƽ�� �����ϴ� ��Ƽ���� �����ϴ�. �߰��Ͻðڽ��ϱ�? Yes:1 / No:0");
		String input = sc.next();
		
		try {
			int sel = Integer.parseInt(input);
			
			if(sel == 1) {
				int size = getPlayer().guildMemberSize();
				int maxParty = size / 2;
				
				for(int i = 0; i < maxParty; i++) {
					int r = ran.nextInt(size);
					
					Unit unit = getPlayer().getGuildMember(r);
					if(!unit.getParty()) {
						unit.setParty(true);							
					}
					else i--;
				}
				
				showGuildList();
				System.out.println("[����]���� ��Ƽ�� �߰��Ϸ�");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void sort() {
		System.out.println("1.������  2.ü�¼�  3.���ݷ¼�  4.���¼�  5.��Ƽ��");
		String input = sc.next();
		
		try {
			int sel = Integer.parseInt(input);
			
			if(sel >= 1 && sel <= 5) {
				if(sel == 1) sortLevel();
				else if(sel == 2) sortHp();
				else if(sel == 3) sortOff();
				else if(sel == 4) sortDef();
				else if(sel == 5) sortPartyMember();
				
				showGuildList();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void sortPartyMember() {
		ArrayList<Unit> partyMember = getPartyMember();
		for(Unit unit : partyMember) {
			unit.showUnit();
		}
	}
	
	private void sortDef() {
		ArrayList<Unit> units = getPlayer().getGuildMemberList();
		for(int i = 0; i < units.size(); i++) {
			for(int j = i+1; j < units.size(); j++) {
				if(units.get(i).getDef() < units.get(j).getDef()) {
					Unit tmp = units.get(i);
					units.set(i, units.get(j));
					units.set(j, tmp);
				}
			}
		}
	}

	private void sortOff() {
		ArrayList<Unit> units = getPlayer().getGuildMemberList();
		for(int i = 0; i < units.size(); i++) {
			for(int j = i+1; j < units.size(); j++) {
				if(units.get(i).getOff() < units.get(j).getOff()) {
					Unit tmp = units.get(i);
					units.set(i, units.get(j));
					units.set(j, tmp);
				}
			}
		}
	}

	private void sortHp() {
		ArrayList<Unit> units = getPlayer().getGuildMemberList();
		for(int i = 0; i < units.size(); i++) {
			for(int j = i+1; j < units.size(); j++) {
				if(units.get(i).getHp() < units.get(j).getHp()) {
					Unit tmp = units.get(i);
					units.set(i, units.get(j));
					units.set(j, tmp);
				}
			}
		}
	}

	private void sortLevel() {
		ArrayList<Unit> units = getPlayer().getGuildMemberList();
		for(int i = 0; i < units.size(); i++) {
			for(int j = i+1; j < units.size(); j++) {
				if(units.get(i).getLevel() < units.get(j).getLevel()) {
					Unit tmp = units.get(i);
					units.set(i, units.get(j));
					units.set(j, tmp);
				}
			}
		}
	}
}
