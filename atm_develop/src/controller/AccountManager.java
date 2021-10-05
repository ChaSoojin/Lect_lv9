package controller;

import java.util.ArrayList;
import java.util.Random;
import models.Account;
import models.Bank;
import models.User;

public class AccountManager {
	public static AccountManager instance = new AccountManager();
	private UserManager um = UserManager.instance;
	private ArrayList<Account> accs = null; //�߾ӵ�����(��� account ������)
	
	private AccountManager() {
		this.accs = new ArrayList<Account>();
	}
	
	//���»���
	public void createAcc() {
		System.out.print("���»����Ͻðڽ��ϱ�? \n1)YES or 2)NO : ");
		String input = BankManager.sc.next();
		
		try {
			int sel = Integer.parseInt(input);
			
			if(sel == 1) {
				int accSize = um.getAccsSize(Bank.log);
				System.out.print("���� ��й�ȣ 4�ڸ� ����: ");
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
							
							accs.add(newAcc); //�߾ӵ����Ϳ� �־��ֱ�
							um.addAcc(Bank.log, newAcc); //�� user�� �ش��ϴ� acc�����Ϳ� �־��ֱ�
							System.out.println("[����]���»����Ϸ�");
							
						}
						else System.out.println("[����]���´� 3������ ��������");
					}
					else System.out.println("[����]��й�ȣ�� ��ȿ���� �ʽ��ϴ�.");
					
				} catch (Exception e) {
					System.out.println("[����]��й�ȣ�� ��ȿ���� �ʽ��ϴ�.");
				}
			}
		} catch (Exception e) {}
	}

	//���·�������
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
	
	//����öȸ
	public void closeAcc() {
		int repIdx = findRepAccIdx();
		Account repAcc = um.getAcc(Bank.log, repIdx);
		
		inquery(Bank.log);
		System.out.printf("���¼���[1~%d]: ", um.getAccsSize(Bank.log));
		String select = BankManager.sc.next();
		
		try {
			int delIdx = Integer.parseInt(select) - 1;
			int money = um.getAcc(Bank.log, delIdx).getMoney();
			
			if(um.getAccsSize(Bank.log) > 1) {
				if(money > 0) {
					if(delIdx != repIdx) {
						System.out.printf("��ǥ���� %d�� �ܾ� %d���� �ڵ���ü�մϴ�.\n", repAcc.getAccNum(), money);
					}
					else {
						int idx = -1;
						while(true) {
							idx = setRepAcc();
							if(idx != repIdx) break;
							else System.out.println(">>>>> �ٸ� ���¸� �ٽ� �������ּ���. <<<<<\n");
						}
						repIdx = idx;
						repAcc = um.getAcc(Bank.log, repIdx);
						System.out.printf("���� %d�� ��ǥ���·� �����Ǿ����ϴ�. �ش� ���·� �ܾ� %d�� ��ü�մϴ�.\n", repAcc.getAccNum(), money);
					}
					repAcc.setMoney(repAcc.getMoney() + money);
				}				
				um.removeAcc(Bank.log, delIdx);
			}
			
			else um.clearAccs(Bank.log);
				
			this.accs.remove(delIdx);
			System.out.println("[����]�ش� ���¸� öȸ�߽��ϴ�.");
		} catch (Exception e) {
			System.out.println("[����]���� öȸ����.");
		}
	}
	
	//��ǥ����ã��
	private int findRepAccIdx() {
		int idx = -1;
		for(int i = 0; i < this.um.getAccsSize(Bank.log); i++) {
			if(this.um.getAcc(Bank.log, i).getRep()) idx = i;
		}
		
		return idx;
	}

	//��ǥ���¼���
	public int setRepAcc() {
		inquery(Bank.log);
		
		int sel = -1;
		int size = um.getAccsSize(Bank.log);
		System.out.printf("��ǥ���¼���[1~%d]: ", size);
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
	
	//�Ա�
	public void deposit() {
		System.out.print("�Աݱݾ�: ");
		String input = BankManager.sc.next();
		
		try {
			int money = Integer.parseInt(input);
			int idx = findRepAccIdx();
			System.out.printf("��ǥ���� %d�� �Ա��Ͻðڽ��ϱ�?\n1)YES or 2)NO(�ٸ�����) : \n", this.um.getAcc(Bank.log, idx).getAccNum());
			String select = BankManager.sc.next();
			
			try {
				int sel = Integer.parseInt(select);
				if(sel == 2) idx = setRepAcc();
				
				if(idx != -1 && sel >= 1 && sel <= 2) {
					System.out.print("���º�й�ȣ�Է�: ");
					int pw = BankManager.sc.nextInt();
					
					if(um.getAcc(Bank.log, idx).getPw() == pw) {
						money += um.getAcc(Bank.log, idx).getMoney();
						um.getAcc(Bank.log, idx).setMoney(money);
						System.out.println("[�Աݼ���] �ԱݵǾ����ϴ�.");
					}
					else System.out.println("[�Աݽ���] ��й�ȣ�� �ٽ� Ȯ�����ּ���.");					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}

	//���
	public void withdraw() {
		System.out.print("��ݱݾ�: ");
		String input = BankManager.sc.next();
		
		try {
			int money = Integer.parseInt(input);
			int idx = findRepAccIdx();
			System.out.printf("��ǥ���� %d���� ����Ͻðڽ��ϱ�?\n1)YES or 2)NO(�ٸ�����) : \n", this.um.getAcc(Bank.log, idx).getAccNum());
			String select = BankManager.sc.next();
			
			try {
				int sel = Integer.parseInt(select);
				if(sel == 2) idx = setRepAcc();
				
				if(idx != -1 && sel >= 1 && sel <= 2) {
					int myMoney = um.getAcc(Bank.log, idx).getMoney();
					
					if(myMoney >= money) {
						System.out.print("���º�й�ȣ�Է�: ");
						int pw = BankManager.sc.nextInt();
						
						if(um.getAcc(Bank.log, idx).getPw() == pw) {
							myMoney -= money;
							um.getAcc(Bank.log, idx).setMoney(myMoney);
							System.out.println("[��ݼ���] ��ݵǾ����ϴ�.");
						}
						else System.out.println("[��ݽ���] ��й�ȣ�� �ٽ� Ȯ�����ּ���.");					
					}
					else System.out.println("[��ݽ���] �ܾ��� �����մϴ�."); 
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}

	//��ü
	public void transfer() {
		int repIdx = findRepAccIdx();
		int repAccNum = um.getAcc(Bank.log, repIdx).getAccNum();
		
		System.out.print("�����Է�: ");
		String input = BankManager.sc.next();
		System.out.print("��ü�ݾ�: ");
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
					System.out.printf("[��ü����]��ǥ���� %d���� �ܾ� %d���� %d���·� ��ü�մϴ�.\n", repAccNum, money, accNum);
				}
				else {
					System.out.println("[��ü����]�ܾ��� �����մϴ�.");
				}
			}	
			else System.out.println("[��ü����]���¸� �ٽ� Ȯ�����ּ���.");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	//������ȸ
	public void inquery(int log) {
		int numbering = 1;
		System.out.printf("[%s�� �������¸��]\n", um.getUser(log).getName());
		for(Account acc : accs) {
			if(acc.getUserCode() == um.getUser(log).getCode()) {
				int accNum = acc.getAccNum();
				int accpw = acc.getPw();
				int money = acc.getMoney();
			
				String accNumber = um.accToString(accNum);
				System.out.print(numbering++ + ". ");
				System.out.printf("����:%s ��й�ȣ:%d �ܾ�:%d��", accNumber, accpw, money);
				
				if(acc.getRep()) System.out.println("(v)");
				else System.out.println();
			}
		}
	}
	
	//��ü���»�����
	public int getAccsSize() {
		return this.accs.size();
	}
	
	//���°�ü
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
