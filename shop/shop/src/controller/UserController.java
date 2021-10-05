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
			System.out.print("[회원가입]ID: ");
			String id = sc.next();
			System.out.print("[회원가입]PW: ");
			String pw = sc.next();
			
			int chk = check(id);
			
			if(chk == -1) {
				System.out.print("[회원가입]NAME: ");
				String name = sc.next();
				this.users.add(new User(ranCode(),id, pw, name));
				System.out.println("[성공]회원가입이 완료되었습니다.");
			}
			else System.out.println("[실패]ID 중복오류");			
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
			System.out.print("[회원탈퇴]PW: ");
			String pw = sc.next();
			
			if(users.get(User.log).getPw().equals(pw)) {
				userCode = users.get(User.log).getCode(); 
				users.remove(User.log);
				User.log = -1;
				System.out.println("회원탈퇴완료");
			}
			else System.out.println("[실패]회원정보를 다시 확인해주세요.");				
		}
		else System.out.println("로그인 후 이용 바랍니다.");
		
		return userCode;
	}

	public boolean login() {
		if(User.log == -1) {
			System.out.print("[로그인]ID: ");
			String id = sc.next();
			System.out.print("[로그인]PW: ");
			String pw = sc.next();
			
			int chk = check(id, pw);
			
			if(chk == -1) System.out.println("[로그인실패]회원정보를 다시 확인해주세요.");
			else {
				User.log = chk;
				System.out.printf("[로그인성공]%s님 로그인되었습니다.\n", this.users.get(User.log).getName());
				return true;
			}			
		}
		return false;
	}

	public void logout() {
		if(User.log != -1) {
			User.log = -1;
			System.out.println("로그아웃 되었습니다.");
		}
		else System.out.println("로그인 후 이용 바랍니다.");
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
		System.out.println("--- 사용자 추가 ---");
		System.out.print("ID 입력: ");
		String id = sc.next();
		
		int chk = check(id);
		
		if(chk == -1) {
			System.out.println("임시 패스워드:1111 / 이름:사용자");
			String pw = "1111";
			String name = "사용자";
			
			this.users.add(new User(ranCode(),id, pw, name));
			System.out.println("[성공]사용자를 추가했습니다.");
		}
		else System.out.println("[실패]ID 중복오류");	
	}

	public long removeUser() {
		long userCode = -1;
		int numbering = 1;
		
		System.out.println("--- 사용자 삭제 ---");
		for(User user : this.users) {
			System.out.print(numbering++ + ". ");
			user.printUserData();
		}
		System.out.print("선택> ");
		String select = sc.next();
		
		try {
			int sel = Integer.parseInt(select) - 1;
			
			if(sel >= 0 && sel < this.users.size()) {
				userCode = users.get(sel).getCode();
				this.users.remove(sel);
				System.out.println("해당 사용자가 삭제되었습니다.");
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
