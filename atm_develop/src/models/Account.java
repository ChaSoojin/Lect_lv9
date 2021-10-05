package models;

public class Account {
	public static final int MAX = 3;
	private boolean rep; //��ǥ����
	private long userCode; //�̰��¸� ���� �������ִ���
	private int accNum;
	private int pw;
	private int money;

	public Account(boolean rep, long userCode, int accNum, int pw) { //ù ���»��� ��
		this.rep = rep;
		this.userCode = userCode;
		this.accNum = accNum;
		this.pw = pw;
		this.money = 1000;
	}
	
	public Account(long userCode, int accNum, int pw) { //�ι�° ���»��� ����~
		this.userCode = userCode;
		this.accNum = accNum;
		this.pw = pw;
		this.money = 1000;
	}
	
	public Account(boolean rep, long userCode, int accNum, int pw, int money) { //�ε� �� ������ ���� ��
		this.rep = rep;
		this.userCode = userCode;
		this.accNum = accNum;
		this.pw = pw;
		this.money = money;
	}
	
	public boolean getRep() {
		return this.rep;
	}
	
	public long getUserCode() {
		return this.userCode;
	}
	
	public int getAccNum() {
		return this.accNum;
	}
	
	public int getPw() {
		return this.pw;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setRep(boolean rep) {
		this.rep = rep;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
}
