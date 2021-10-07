package models;

public class Unit {
	private String name;
	private int level;
	private int hp; //ü��
	private int maxHp;
	private int def; //����
	private int off; //���ݷ�
	private int exp; //����ġ
	boolean party;
	private Item weapon;
	private Item armor;
	private Item ring;
	
	public Unit(String name, int level, int maxHp, int def, int off, int exp) {
		this.name = name;
		this.level = level;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.def = def;
		this.off = off;
		this.exp = exp;
	}
	
	public void showUnit() {
		System.out.print("[�̸� : " + this.name + "]");
		System.out.print(" [���� : " + this.level + "]");
		System.out.print(" [ü�� : " + this.hp);
		System.out.println(" / " + this.maxHp + "]");
		System.out.print("[���ݷ� : " + this.off + "]");
		System.out.print(" [���� : " + this.def + "]");
		System.out.println(" [��Ƽ�� : " + this.party + "]");
	}

	public boolean getParty() {
		return this.party;
	}
	
	public void setParty(boolean party) {
		this.party = party;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public int getOff() {
		return this.off;
	}
	
	public int getDef() {
		return this.def;
	}
}
