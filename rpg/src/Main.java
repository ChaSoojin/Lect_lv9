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
		System.out.println("1.길드관리\n2.상점\n3.인벤토리\n4.저장\n5.로드\n0.종료");
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
			else if(sel == 0) {
				System.out.println("GAME 종료");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return true;
	}

	private void guildMenu() {
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

public class Main {
	public static void main(String[] args) {
		MainGame mg = new MainGame();
		mg.run();
	}
}
