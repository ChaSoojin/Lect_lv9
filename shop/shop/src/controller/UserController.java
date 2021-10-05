package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import models.User;

public class UserController {
	public static UserController instance = new UserController();
	public static Scanner sc = new Scanner(System.in);
	private ArrayList<User> users = null;
	
	private UserController() {
		this.users = new ArrayList<User>();
	}
	
	public void join() {
		if(User.log == -1) {
			System.out.print("[ȸ������]ID: ");
			String id = sc.next();
			System.out.print("[ȸ������]PW: ");
			String pw = sc.next();
			
			int chk = check(id);
			
			if(chk == -1) {
				System.out.print("[ȸ������]NAME: ");
				String name = sc.next();
				this.users.add(new User(ranCode(),id, pw, name));
				System.out.println("[����]ȸ�������� �Ϸ�Ǿ����ϴ�.");
			}
			else System.out.println("[����]ID �ߺ�����");			
		}
	}

	private long ranCode() {
		Random r = new Random();
		
		while(true) {
			int rCode = r.nextInt(9000) + 1000;
			
			boolean chk = true;
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).getCode() == rCode) chk = false;
			}
			
			if(chk) return rCode;
		}
	}

	public long deleteUser() {
		long userCode = -1;
		
		if(User.log != -1) {
			System.out.print("[ȸ��Ż��]PW: ");
			String pw = sc.next();
			
			if(users.get(User.log).getPw().equals(pw)) {
				userCode = users.get(User.log).getCode(); 
				users.remove(User.log);
				User.log = -1;
				System.out.println("ȸ��Ż��Ϸ�");
			}
			else System.out.println("[����]ȸ�������� �ٽ� Ȯ�����ּ���.");				
		}
		else System.out.println("�α��� �� �̿� �ٶ��ϴ�.");
		
		return userCode;
	}

	public boolean login() {
		if(User.log == -1) {
			System.out.print("[�α���]ID: ");
			String id = sc.next();
			System.out.print("[�α���]PW: ");
			String pw = sc.next();
			
			int chk = check(id, pw);
			
			if(chk == -1) System.out.println("[�α��ν���]ȸ�������� �ٽ� Ȯ�����ּ���.");
			else {
				User.log = chk;
				System.out.printf("[�α��μ���]%s�� �α��εǾ����ϴ�.\n", this.users.get(User.log).getName());
				return true;
			}			
		}
		return false;
	}

	public void logout() {
		if(User.log != -1) {
			User.log = -1;
			System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
		}
		else System.out.println("�α��� �� �̿� �ٶ��ϴ�.");
	}
	
	private int check(String id) {
		int chk = -1;
		
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getId().equals(id)) chk = i;
		}
		
		return chk;
	}
	
	private int check(String id, String pw) {
		int chk = -1;
		
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getId().equals(id) && users.get(i).getPw().equals(pw)) chk = i;
		}
		
		return chk;
	}

	public void addUser() {
		System.out.println("--- ����� �߰� ---");
		System.out.print("ID �Է�: ");
		String id = sc.next();
		
		int chk = check(id);
		
		if(chk == -1) {
			System.out.println("�ӽ� �н�����:1111 / �̸�:�����");
			String pw = "1111";
			String name = "�����";
			
			this.users.add(new User(ranCode(),id, pw, name));
			System.out.println("[����]����ڸ� �߰��߽��ϴ�.");
		}
		else System.out.println("[����]ID �ߺ�����");	
	}

	public long removeUser() {
		long userCode = -1;
		int numbering = 1;
		
		System.out.println("--- ����� ���� ---");
		for(User user : this.users) {
			System.out.print(numbering++ + ". ");
			user.printUserData();
		}
		System.out.print("����> ");
		String select = sc.next();
		
		try {
			int sel = Integer.parseInt(select) - 1;
			
			if(sel >= 0 && sel < this.users.size()) {
				userCode = users.get(sel).getCode();
				this.users.remove(sel);
				System.out.println("�ش� ����ڰ� �����Ǿ����ϴ�.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return userCode;
	}
	
	public User getUser(int log) {
		return this.users.get(log);
	}
	
	public ArrayList<User> getUserList(){
		return this.users;
	}
	
	public int getUserSize() {
		return this.users.size();
	}
	
	public void userSetting(User user) {
		this.users.add(user);
	}
}
