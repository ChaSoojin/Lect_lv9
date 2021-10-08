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
			System.out.print("[ȸ������]ID: ");
			String id = Guild.sc.next();
			System.out.print("[ȸ������]PW: ");
			String pw = Guild.sc.next();

			int chk = check(id);

			if (chk == -1) {
				this.players.add(new Player(id, pw));
				System.out.println("[����]ȸ�������� �Ϸ�Ǿ����ϴ�.");
			} 
			else System.out.println("[����]ID �ߺ�����");
		}
	}

	public void deletePlayer() {
		if (Player.log != -1) {
			System.out.print("[ȸ��Ż��]PW: ");
			String pw = Guild.sc.next();

			if (players.get(Player.log).getPw().equals(pw)) {
				this.players.remove(Player.log);
				Player.log = -1;
				System.out.println("ȸ��Ż��Ϸ�");
			} 
			else System.out.println("[����]ȸ�������� �ٽ� Ȯ�����ּ���.");
		} 
		
		else System.out.println("�α��� �� �̿� �ٶ��ϴ�.");
	}

	public boolean login() {
		if (Player.log == -1) {
			System.out.print("[�α���]ID: ");
			String id = Guild.sc.next();
			System.out.print("[�α���]PW: ");
			String pw = Guild.sc.next();

			int chk = check(id, pw);

			if (chk == -1)
				System.out.println("[�α��ν���]ȸ�������� �ٽ� Ȯ�����ּ���.");
			else {
				Player.log = chk;
				System.out.printf("[�α��μ���]%s�� �α��εǾ����ϴ�.\n", this.players.get(Player.log).getId());
				return true;
			}
		}
		return false;
	}

	public void logout() {
		if (Player.log != -1) {
			Player.log = -1;
			System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
		} else
			System.out.println("�α��� �� �̿� �ٶ��ϴ�.");
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
