package models;

public class Unit {
	private String name;
	private int level;
	private int hp; //체력
	private int maxHp;
	private int def; //방어력
	private int off; //공격력
	private int exp; //경험치
	boolean party;
	private Item weapon;
	private Item armor;
	private Item ring;
	
	public Unit(String name, int level, int hp, int maxHp, int def, int off, int exp) {
		this.name = name;
		this.level = level;
		this.hp = hp;
		this.maxHp = maxHp;
		this.def = def;
		this.off = off;
		this.exp = exp;
	}
	
	public void showUnit() {
		System.out.print(" [이름 : " + this.name + "]");
		System.out.print(" [레벨 : " + this.level + "]");
		System.out.print(" [체력 : " + this.hp);
		System.out.println(" / " + this.maxHp + "]");
		System.out.print("[공격력 : " + this.off + "]");
		System.out.print(" [방어력 : " + this.def + "]");
		System.out.println(" [파티중 : " + this.party + "]");
	}
}
