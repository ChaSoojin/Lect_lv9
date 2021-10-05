package controller;

import java.util.ArrayList;
import java.util.Random;
import models.Account;
import models.Bank;
import models.User;

public class UserManager {
	public static UserManager instance = new UserManager();
	private ArrayList<User> users = null;
	
	private UserManager() {
		this.users = new ArrayList<User>();
	}
	
	//����
	public void join() {
		System.out.print("ID: ");
		String id = BankManager.sc.next();
		System.out.print("PW: ");
		String pw = BankManager.sc.next();
		
		int chk = chkLog(id, pw);
		
		if(chk == -1) {
			System.out.print("name: ");
			String name = BankManager.sc.next();
			this.users.add(new User(ranCode(), id, pw, name));
			System.out.println("[���ԿϷ�]ȸ������ �Ǿ����ϴ�.");
		}
		else System.out.println("[���Խ���]���̵� �ߺ�");
	}
	
	//�ڵ��ߺ�����
	private long ranCode() {
		Random r = new Random();
		
		while(true) {
			long code = r.nextInt(9000) + 1000; //1000 ~ 9999
			
			boolean chk = true;
			for(int i = 0; i < this.users.size(); i++) {
				if(this.users.get(i).getCode() == code) chk = false; 
			}
			
			if(chk) return code;
		}		
	}

	//���̵��ߺ����� �� �α�ã��
	private int chkLog(String id, String pw) {
		int log = 0;
		for(User user : this.users) {
			if(user.getId().equals(id) && user.getPw().equals(pw))
				return log;
			log++;
		}
		return -1;
	}

	//Ż��
	public void withdrawal() {
		System.out.print("PW: ");
		String pw = BankManager.sc.next();
		
		if(this.users.get(Bank.log).getPw().equals(pw)) {
			this.users.remove(Bank.log);
			Bank.log = -1;
			System.out.println("Ż��Ϸ�");
		}
		else {
			System.out.println("[����]ȸ�������� Ȯ�����ּ���.");
		}
	}
	
	//�α���
	public void login() {
		System.out.print("ID: ");
		String id = BankManager.sc.next();
		System.out.print("PW: ");
		String pw = BankManager.sc.next();
		
		int log = chkLog(id, pw);
		
		if(log == -1) {
			System.out.println("[�α��ν���]ȸ�������� �ٽ� Ȯ�����ּ���.");
		}
		else {
			Bank.log = log;
			System.out.printf("[�α��μ���]%s�� �α��� �Ǿ����ϴ�.\n", this.users.get(Bank.log).getName());
		}
	}

	//�α׾ƿ�
	public void logout() {
		if(Bank.log != -1) {
			Bank.log = -1;
			System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
		}
	}

	//ȸ��������ȸ
	public void mypage() {		
		int log = Bank.log;
		long code = this.users.get(log).getCode();
		String id = this.users.get(log).getId();
		String pw = this.users.get(log).getPw();
		String name = this.users.get(log).getName();
		
		System.out.printf("------ %s�� ���������� ------\n", name);
		System.out.printf("�ڵ�:%s ID:%s PW:%s �̸�:%s\n", code, id, pw, name);
		showUserAccs(log);
		System.out.println("-------------------------");
	}
	
	//���������������
	private void showUserAccs(int log) {
		System.out.println("[�������¸��]");
		for(int i = 0; i < this.users.get(log).getAccsSize(); i++) {
			int accNum = this.users.get(log).getAcc(i).getAccNum();
			int accpw = this.users.get(log).getAcc(i).getPw();
			int money = this.users.get(log).getAcc(i).getMoney();
			
			String accNumber = accToString(accNum);
			System.out.print((i+1) + ". ");
			System.out.printf("����:%s ��й�ȣ:%d �ܾ�:%d��", accNumber, accpw, money);
			
			if(this.users.get(log).getAcc(i).getRep()) System.out.println("(v)");
			else System.out.println();
		}	
	}
	
	//���¹�ȣ string���·� �ٲٱ�
	public String accToString(int accNum) {
		String accNumber = accNum + "";
		String[] str = accNumber.split("");
		accNumber = "";
		for(int j = 0; j < str.length; j++) {
			if(j == 1 || j == 5) str[j] += "-";
			accNumber += str[j];
		}
		
		return accNumber;
	}
	
	//���º�������
	public int getAccsSize(int log) {
		return this.users.get(log).getAccsSize();
	}
	
	//����ڼ�
	public int getUserSize() {
		return this.users.size();
	}
	
	//���� ��ü
	public User getUser(int log) {
		return this.users.get(log);
	}
	
	//���� ��ü
	public Account getAcc(int log, int idx) {
		return this.users.get(log).getAcc(idx);
	}
	
	//�����߰�
	public void addAcc(int log, Account acc) {
		this.users.get(log).getAccs().add(acc);
	}
	
	//���»���
	public void removeAcc(int log, int delIdx) {
		this.users.get(log).getAccs().remove(delIdx);
	}
	
	//���¹迭clear(size�� 0����, null�ƴ� ���� <-> null)
	public void clearAccs(int log) {
		this.users.get(log).clearAccs();
	}
	
	public void clearUser() {
		this.users.clear();
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
}
