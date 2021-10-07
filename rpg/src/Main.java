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
		System.out.println("1.가입  2.탈퇴  3.로그인  4.로그아웃  0.종료");
		System.out.print("선택> ");
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
				System.out.println("RPG 게임종료");
				return false;
			}
		} catch (Exception e) {}
		return true;
	}
	
	private void mainMenu() {
		while(true) {
			System.out.println("= RPG MAIN MENU =");
			System.out.println("1.길드관리\n2.상점\n3.인벤토리\n4.저장\n5.로드\n0.뒤로가기");
			System.out.println("=================");
			System.out.print("선택> ");
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
			System.out.println("1.길드목록\n2.길드원추가\n3.길드원삭제\n4.파티원교체\n5.정렬\n0.뒤로가기");
			System.out.println("======================");
			System.out.print("선택> ");
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
