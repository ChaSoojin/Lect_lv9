import Controller.FileController;
import Controller.Guild;
import Controller.PlayerController;

class MainGame {
	private Guild guild = Guild.instance;
	private FileController fc = FileController.instance;
	private PlayerController pc = PlayerController.instance;
	
	public void run() {
		boolean isRun = true;
		while(isRun) {
			printAllData();
			isRun = loginMenu();
		}
	}
	
	private boolean loginMenu() {
		System.out.println("=========== RPG Login Menu ==========");
		System.out.println("1.����  2.Ż��  3.�α���  4.�α׾ƿ�  0.����");
		System.out.print("����> ");
		String select = guild.sc.next();

		try {
			int sel = Integer.parseInt(select);

			if (sel == 1) pc.join();
			else if (sel == 2) pc.deletePlayer();
			else if (sel == 3) {
				if(pc.login()) mainMenu();
			}
			else if (sel == 4) pc.logout();
			else if (sel == 0) {
				fc.save();
				System.out.println("RPG ��������");
				return false;
			}
		} catch (Exception e) {}
		return true;
	}
	
	private void mainMenu() {
		while(true) {
			System.out.println("= RPG MAIN MENU =");
			System.out.println("1.������\n2.����\n3.�κ��丮\n4.����\n5.�ε�\n0.�ڷΰ���");
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
				else if(sel == 0) return;
				
			} catch (Exception e) {
				// TODO: handle exception
			}		
		}
	}

	private void guildMenu() {
		while(true) {
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
	
	private void printAllData() {
		for(int i = 0; i < pc.getPlayerSize(); i++) {
			String data = pc.getPlayer(i).toString();
			System.out.println(data);
		}
	}
}

public class Main {
	public static void main(String[] args) {
		MainGame mg = new MainGame();
		mg.run();
	}
}
