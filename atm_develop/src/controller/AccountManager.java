package controller;

import java.util.ArrayList;
import java.util.Random;
import models.Account;
import models.Bank;
import models.User;

public class AccountManager {
	public static AccountManager instance = new AccountManager();
	private UserManager um = UserManager.instance;
	private ArrayList<Account> accs = null; //중앙데이터(모든 account 총집합)
	
	private AccountManager() {
		this.accs = new ArrayList<Account>();
	}
	
	//계좌생성
	public void createAcc() {
		System.out.print("계좌생성하시겠습니까? \n1)YES or 2)NO : ");
		String input = BankManager.sc.next();
		
		try {
			int sel = Integer.parseInt(input);
			
			if(sel == 1) {
				int accSize = um.getAccsSize(Bank.log);
				System.out.print("계좌 비밀번호 4자리 설정: ");
				String pwInput = BankManager.sc.next();
				int pw = 0;
				
				try {
					pw = Integer.parseInt(pwInput);
					if(pw >= 1000 && pw <10000) {
						if(accSize < Account.MAX) {
							Account newAcc = null;
							if(accSize == 0) {
								newAcc = new Account(true, um.getUser(Bank.log).getCode(), ranAccNum(), pw);
							}
							else newAcc = new Account(um.getUser(Bank.log).getCode(), ranAccNum(), pw);
							
							accs.add(newAcc); //중앙데이터에 넣어주기
							um.addAcc(Bank.log, newAcc); //각 user에 해당하는 acc데이터에 넣어주기
							System.out.println("[성공]계좌생성완료");
							
						}
						else System.out.println("[실패]계좌는 3개까지 생성가능");
					}
					else System.out.println("[실패]비밀번호가 유효하지 않습니다.");
					
				} catch (Exception e) {
					System.out.println("[실패]비밀번호가 유효하지 않습니다.");
				}
			}
		} catch (Exception e) {}
	}

	//계좌랜덤생성
	private int ranAccNum() {
		Random r = new Random();
		
		while(true) {
			int accNum = r.nextInt(88888889) + 11111111;
			
			boolean chk = true;
			for(Account acc : accs) {
				if(acc.getAccNum() == accNum) chk = false;
			}
			
			if(chk) return accNum;
		}
	}
	
	//계좌철회
	public void closeAcc() {
		int repIdx = findRepAccIdx();
		Account repAcc = um.getAcc(Bank.log, repIdx);
		
		inquery(Bank.log);
		System.out.printf("계좌선택[1~%d]: ", um.getAccsSize(Bank.log));
		String select = BankManager.sc.next();
		
		try {
			int delIdx = Integer.parseInt(select) - 1;
			int money = um.getAcc(Bank.log, delIdx).getMoney();
			
			if(um.getAccsSize(Bank.log) > 1) {
				if(money > 0) {
					if(delIdx != repIdx) {
						System.out.printf("대표계좌 %d로 잔액 %d원을 자동이체합니다.\n", repAcc.getAccNum(), money);
					}
					else {
						int idx = -1;
						while(true) {
							idx = setRepAcc();
							if(idx != repIdx) break;
							else System.out.println(">>>>> 다른 계좌를 다시 선택해주세요. <<<<<\n");
						}
						repIdx = idx;
						repAcc = um.getAcc(Bank.log, repIdx);
						System.out.printf("계좌 %d가 대표계좌로 설정되었습니다. 해당 계좌로 잔액 %d원 이체합니다.\n", repAcc.getAccNum(), money);
					}
					repAcc.setMoney(repAcc.getMoney() + money);
				}				
				um.removeAcc(Bank.log, delIdx);
			}
			
			else um.clearAccs(Bank.log);
				
			this.accs.remove(delIdx);
			System.out.println("[성공]해당 계좌를 철회했습니다.");
		} catch (Exception e) {
			System.out.println("[실패]계좌 철회실패.");
		}
	}
	
	//대표계좌찾기
	private int findRepAccIdx() {
		int idx = -1;
		for(int i = 0; i < this.um.getAccsSize(Bank.log); i++) {
			if(this.um.getAcc(Bank.log, i).getRep()) idx = i;
		}
		
		return idx;
	}

	//대표계좌설정
	public int setRepAcc() {
		inquery(Bank.log);
		
		int sel = -1;
		int size = um.getAccsSize(Bank.log);
		System.out.printf("대표계좌선택[1~%d]: ", size);
		String select = BankManager.sc.next();
		
		try {
			sel = Integer.parseInt(select) - 1;
			
			if(sel >= 0 && sel < size) {
				um.getAcc(Bank.log, sel).setRep(true);
				
				for(int i = 0; i < size; i++) {
					if(i != sel && um.getAcc(Bank.log, i).getRep()) {
						um.getAcc(Bank.log, i).setRep(false);
					}
				}
			}
			else sel = -1;
		} catch (Exception e) {}
		
		return sel;
	}
	
	//입금
	public void deposit() {
		System.out.print("입금금액: ");
		String input = BankManager.sc.next();
		
		try {
			int money = Integer.parseInt(input);
			int idx = findRepAccIdx();
			System.out.printf("대표계좌 %d에 입금하시겠습니까?\n1)YES or 2)NO(다른계좌) : \n", this.um.getAcc(Bank.log, idx).getAccNum());
			String select = BankManager.sc.next();
			
			try {
				int sel = Integer.parseInt(select);
				if(sel == 2) idx = setRepAcc();
				
				if(idx != -1 && sel >= 1 && sel <= 2) {
					System.out.print("계좌비밀번호입력: ");
					int pw = BankManager.sc.nextInt();
					
					if(um.getAcc(Bank.log, idx).getPw() == pw) {
						money += um.getAcc(Bank.log, idx).getMoney();
						um.getAcc(Bank.log, idx).setMoney(money);
						System.out.println("[입금성공] 입금되었습니다.");
					}
					else System.out.println("[입금실패] 비밀번호를 다시 확인해주세요.");					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}

	//출금
	public void withdraw() {
		System.out.print("출금금액: ");
		String input = BankManager.sc.next();
		
		try {
			int money = Integer.parseInt(input);
			int idx = findRepAccIdx();
			System.out.printf("대표계좌 %d에서 출금하시겠습니까?\n1)YES or 2)NO(다른계좌) : \n", this.um.getAcc(Bank.log, idx).getAccNum());
			String select = BankManager.sc.next();
			
			try {
				int sel = Integer.parseInt(select);
				if(sel == 2) idx = setRepAcc();
				
				if(idx != -1 && sel >= 1 && sel <= 2) {
					int myMoney = um.getAcc(Bank.log, idx).getMoney();
					
					if(myMoney >= money) {
						System.out.print("계좌비밀번호입력: ");
						int pw = BankManager.sc.nextInt();
						
						if(um.getAcc(Bank.log, idx).getPw() == pw) {
							myMoney -= money;
							um.getAcc(Bank.log, idx).setMoney(myMoney);
							System.out.println("[출금성공] 출금되었습니다.");
						}
						else System.out.println("[출금실패] 비밀번호를 다시 확인해주세요.");					
					}
					else System.out.println("[출금실패] 잔액이 부족합니다."); 
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}

	//이체
	public void transfer() {
		int repIdx = findRepAccIdx();
		int repAccNum = um.getAcc(Bank.log, repIdx).getAccNum();
		
		System.out.print("계좌입력: ");
		String input = BankManager.sc.next();
		System.out.print("이체금액: ");
		String input2 = BankManager.sc.next();
		
		try {
			int accNum = Integer.parseInt(input);
			int money = Integer.parseInt(input2);
			
			int idx = -1;
			for(int i = 0; i < this.accs.size(); i++) {
				if(this.accs.get(i).getAccNum() == accNum) idx = i;
			}
			
			if(accNum != repAccNum && idx != -1) {
				int myMoney = um.getAcc(Bank.log, repIdx).getMoney();
				int yourMoney = this.accs.get(idx).getMoney();
				
				if(myMoney >= money) {
					myMoney -= money;
					yourMoney += money;
					um.getAcc(Bank.log, repIdx).setMoney(myMoney);
					this.accs.get(idx).setMoney(yourMoney);	
					System.out.printf("[이체성공]대표계좌 %d에서 잔액 %d원을 %d계좌로 이체합니다.\n", repAccNum, money, accNum);
				}
				else {
					System.out.println("[이체실패]잔액이 부족합니다.");
				}
			}	
			else System.out.println("[이체실패]계좌를 다시 확인해주세요.");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	//계좌조회
	public void inquery(int log) {
		int numbering = 1;
		System.out.printf("[%s님 보유계좌목록]\n", um.getUser(log).getName());
		for(Account acc : accs) {
			if(acc.getUserCode() == um.getUser(log).getCode()) {
				int accNum = acc.getAccNum();
				int accpw = acc.getPw();
				int money = acc.getMoney();
			
				String accNumber = um.accToString(accNum);
				System.out.print(numbering++ + ". ");
				System.out.printf("계좌:%s 비밀번호:%d 잔액:%d원", accNumber, accpw, money);
				
				if(acc.getRep()) System.out.println("(v)");
				else System.out.println();
			}
		}
	}
	
	//전체계좌사이즈
	public int getAccsSize() {
		return this.accs.size();
	}
	
	//계좌객체
	public Account getAcc(int idx) {
		return this.accs.get(idx);
	}
	
	public void clearAcc() {
		this.accs.clear();
	}
	
	public void addAcc(Account acc) {
		this.accs.add(acc);
	}
}
