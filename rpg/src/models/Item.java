package models;

public class Item {
	public static final int WEAPON = 1;
	public static final int ARMOR = 2;
	public static final int RING = 3;
	private int kind;
	private String name;
	private int power;
	private int price;
	
	public Item(int kind, String name, int power, int price) {
		this.kind = kind;
		this.name = name;
		this.power = power;
		this.price = price;
	}
	
	public int getKind() {
		return this.kind;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPower() {
		return this.power;
	}
	
	public int getPrice() {
		return this.price;
	}
}
