package Controller;

import java.util.ArrayList;
import models.Player;

public class PlayerController {
	public static PlayerController instance = new PlayerController();
	private ArrayList<Player> players = null;

	private PlayerController() {
		this.players = new ArrayList<Player>();
	}

	public void join() {
		if (Player.log == -1) {
			System.out.print("[회원가입]ID: ");
			String id = Guild.sc.next();
			System.out.print("[회원가입]PW: ");
			String pw = Guild.sc.next();

			int chk = check(id);

			if (chk == -1) {
				this.players.add(new Player(id, pw));
				System.out.println("[성공]회원가입이 완료되었습니다.");
			} 
			else System.out.println("[실패]ID 중복오류");
		}
	}

	public void deletePlayer() {
		if (Player.log != -1) {
			System.out.print("[회원탈퇴]PW: ");
			String pw = Guild.sc.next();

			if (players.get(Player.log).getPw().equals(pw)) {
				this.players.remove(Player.log);
				Player.log = -1;
				System.out.println("회원탈퇴완료");
			} 
			else System.out.println("[실패]회원정보를 다시 확인해주세요.");
		} 
		
		else System.out.println("로그인 후 이용 바랍니다.");
	}

	public boolean login() {
		if (Player.log == -1) {
			System.out.print("[로그인]ID: ");
			String id = Guild.sc.next();
			System.out.print("[로그인]PW: ");
			String pw = Guild.sc.next();

			int chk = check(id, pw);

			if (chk == -1)
				System.out.println("[로그인실패]회원정보를 다시 확인해주세요.");
			else {
				Player.log = chk;
				System.out.printf("[로그인성공]%s님 로그인되었습니다.\n", this.players.get(Player.log).getId());
				return true;
			}
		}
		return false;
	}

	public void logout() {
		if (Player.log != -1) {
			Player.log = -1;
			System.out.println("로그아웃 되었습니다.");
		} else
			System.out.println("로그인 후 이용 바랍니다.");
	}

	private int check(String id) {
		int chk = -1;

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId().equals(id))
				chk = i;
		}

		return chk;
	}

	private int check(String id, String pw) {
		int chk = -1;

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId().equals(id) && players.get(i).getPw().equals(pw))
				chk = i;
		}

		return chk;
	}

	public Player getPlayer(int log) {
		return this.players.get(log);
	}

	public ArrayList<Player> getPlayerList() {
		return this.players;
	}

	public int getPlayerSize() {
		return this.players.size();
	}
	
	public void removeGuildMember(int log, int idx) {
		this.players.get(log).removeGuildMember(idx);
	}
}
