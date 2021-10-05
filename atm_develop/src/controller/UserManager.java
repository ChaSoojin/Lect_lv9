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
	
	//가입
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
			System.out.println("[가입완료]회원가입 되었습니다.");
		}
		else System.out.println("[가입실패]아이디 중복");
	}
	
	//코드중복없이
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

	//아이디중복여부 및 로그찾기
	private int chkLog(String id, String pw) {
		int log = 0;
		for(User user : this.users) {
			if(user.getId().equals(id) && user.getPw().equals(pw))
				return log;
			log++;
		}
		return -1;
	}

	//탈퇴
	public void withdrawal() {
		System.out.print("PW: ");
		String pw = BankManager.sc.next();
		
		if(this.users.get(Bank.log).getPw().equals(pw)) {
			this.users.remove(Bank.log);
			Bank.log = -1;
			System.out.println("탈퇴완료");
		}
		else {
			System.out.println("[실패]회원정보를 확인해주세요.");
		}
	}
	
	//로그인
	public void login() {
		System.out.print("ID: ");
		String id = BankManager.sc.next();
		System.out.print("PW: ");
		String pw = BankManager.sc.next();
		
		int log = chkLog(id, pw);
		
		if(log == -1) {
			System.out.println("[로그인실패]회원정보를 다시 확인해주세요.");
		}
		else {
			Bank.log = log;
			System.out.printf("[로그인성공]%s님 로그인 되었습니다.\n", this.users.get(Bank.log).getName());
		}
	}

	//로그아웃
	public void logout() {
		if(Bank.log != -1) {
			Bank.log = -1;
			System.out.println("로그아웃 되었습니다.");
		}
	}

	//회원정보조회
	public void mypage() {		
		int log = Bank.log;
		long code = this.users.get(log).getCode();
		String id = this.users.get(log).getId();
		String pw = this.users.get(log).getPw();
		String name = this.users.get(log).getName();
		
		System.out.printf("------ %s님 마이페이지 ------\n", name);
		System.out.printf("코드:%s ID:%s PW:%s 이름:%s\n", code, id, pw, name);
		showUserAccs(log);
		System.out.println("-------------------------");
	}
	
	//보유계좌정보출력
	private void showUserAccs(int log) {
		System.out.println("[보유계좌목록]");
		for(int i = 0; i < this.users.get(log).getAccsSize(); i++) {
			int accNum = this.users.get(log).getAcc(i).getAccNum();
			int accpw = this.users.get(log).getAcc(i).getPw();
			int money = this.users.get(log).getAcc(i).getMoney();
			
			String accNumber = accToString(accNum);
			System.out.print((i+1) + ". ");
			System.out.printf("계좌:%s 비밀번호:%d 잔액:%d원", accNumber, accpw, money);
			
			if(this.users.get(log).getAcc(i).getRep()) System.out.println("(v)");
			else System.out.println();
		}	
	}
	
	//계좌번호 string형태로 바꾸기
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
	
	//계좌보유개수
	public int getAccsSize(int log) {
		return this.users.get(log).getAccsSize();
	}
	
	//사용자수
	public int getUserSize() {
		return this.users.size();
	}
	
	//유저 객체
	public User getUser(int log) {
		return this.users.get(log);
	}
	
	//계좌 객체
	public Account getAcc(int log, int idx) {
		return this.users.get(log).getAcc(idx);
	}
	
	//계좌추가
	public void addAcc(int log, Account acc) {
		this.users.get(log).getAccs().add(acc);
	}
	
	//계좌삭제
	public void removeAcc(int log, int delIdx) {
		this.users.get(log).getAccs().remove(delIdx);
	}
	
	//계좌배열clear(size를 0으로, null아님 주의 <-> null)
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
