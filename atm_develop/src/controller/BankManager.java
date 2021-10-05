package controller;

import java.util.Scanner;
import models.Bank;

public class BankManager {
	public static BankManager instance = new BankManager();
	public static Scanner sc = new Scanner(System.in);
	private UserManager um = UserManager.instance;
	private AccountManager am = AccountManager.instance;
	private FileManager fm = FileManager.instance;
	
	private BankManager() {
		Bank.instance.setBrand("Hana");
	}
	
	public void run() {
		fm.load();
		boolean isRun = true;
		while(isRun) {
			//printAllData();
			showMenu();
			isRun = selectMenu();
		}
	}
	
	private boolean selectMenu() {
		System.out.print("선택: ");
		String select = sc.next();
		int sel = 0;
		
		try {
			sel = Integer.parseInt(select);
		} catch (Exception e) {}
		
		
		if(Bank.log == -1) {
			if(sel >= 1 && sel <= 3) {
				if(sel == 1) um.join();
				else if(sel == 2) um.login();
				else if(sel == 3) {
					System.out.println("ATM 종료");
					
					if(am.getAccsSize() > 0 || um.getUserSize() > 0) fm.save();
					return false;
				}				
			}
		}
		else {
			if(sel >= 1 && sel <= 10) {
				if(um.getAccsSize(Bank.log) > 0) {
					if(sel == 1) am.deposit();
					else if(sel == 2) am.withdraw();
					else if(sel == 3) am.transfer();
					else if(sel == 6) am.closeAcc();
					else if(sel == 7) am.inquery(Bank.log);
					else if(sel == 8) am.setRepAcc();
				}				
				if(sel == 4) um.mypage();
				else if(sel == 5) am.createAcc();
				else if(sel == 9) um.logout();
				else if(sel == 10) um.withdrawal();
			}
		}
		return true;
	}

	private void showMenu() {
		if(Bank.log == -1) {
			System.out.printf("======== %s ATM =======\n",Bank.instance.getBrand());
			System.out.println("1.회원가입 2.로그인 3.종료");
			System.out.println("======================");
		}
		else {
			System.out.printf("======== %s님 ATM MENU ========\n", um.getUser(Bank.log).getName());
			System.out.println("1.입금 2.출금 3.이체 4.회원정보조회\n5.계좌생성 6.계좌철회 7.계좌조회\n8.대표계좌설정 9.로그아웃 10.회원탈퇴");
			System.out.println("==============================");
		}
	}
	
	private void printAllData() {
		String data = "";
		for(int i = 0; i < um.getUserSize(); i++) {
			data += um.getUser(i).getCode() + "/";
			data += um.getUser(i).getId() + "/";
			data += um.getUser(i).getPw() + "/";
			data += um.getUser(i).getName() + "\n";
		}
		System.out.println(data);
	}
}
