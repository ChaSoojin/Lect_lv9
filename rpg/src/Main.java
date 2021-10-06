import Controller.FileController;
import Controller.Guild;

class MainGame {
	private Guild guild = Guild.instance;
	private FileController fc = FileController.instance;
	
	public void run() {
		boolean isRun = true;
		while(isRun) {
			isRun = mainMenu();
		}
	}
	
	private boolean mainMenu() {
		System.out.println("= RPG MAIN MENU =");
		System.out.println("1.������\n2.����\n3.�κ��丮\n4.����\n5.�ε�\n0.����");
		System.out.println("=================");
		System.out.print("����> ");
		String select = guild.sc.next();
		
		try {
			int sel = Integer.parseInt(select);
			
			if(sel == 1) guildMenu();
			else if(sel == 2) {}
			else if(sel == 3) {}
			else if(sel == 4) fc.save();
			else if(sel == 5) fc.load();
			else if(sel == 0) {
				System.out.println("GAME ����");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return true;
	}

	private void guildMenu() {
		System.out.println("===== Guild Menu =====");
		System.out.println("1.�����\n2.�����߰�\n3.��������\n4.��Ƽ����ü\n5.����\n0.�ڷΰ���");
		System.out.println("======================");
		System.out.print("����> ");
		String select = guild.sc.next();
		
		try {
			int sel = Integer.parseInt(select);
			
			if(sel == 1) guild.showGuildList();
			else if(sel == 2) guild.addGuildMember();
			else if(sel == 3) guild.removeGuildMember();
			else if(sel == 4) guild.changePartyMember();
			else if(sel == 5) guild.sort();
			else if(sel == 0) return;
			
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
}

public class Main {
	public static void main(String[] args) {
		MainGame mg = new MainGame();
		mg.run();
	}
}
