package Controller;

import java.util.ArrayList;

import models.Item;

public class Shop {
	public static Shop instance = new Shop();
	private ArrayList<Item> items = new ArrayList<>();
	
	private Shop() {
		int kind = Item.WEAPON;
		String name = "³ª¹«°Ë";
		int power = 3;
		int price = 1000;
		this.items.add(new Item(kind, name, power, price));
		
		kind = Item.WEAPON;
		name = "Ã¶°Ë";
		power = 5;
		price = 2000;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.WEAPON;
		name = "·¹ÀÌÇÇ¾î";
		power = 7;
		price = 2500;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.ARMOR;
		name = "Æ¼¼ÅÃ÷";
		power = 1;
		price = 300;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.ARMOR;
		name = "°¡Á×°©¿Ê";
		power = 4;
		price = 800;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.ARMOR;
		name = "°­Ã¶°©¿Ê";
		power = 7;
		price = 1500;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.RING;
		name = "Àº¹ÝÁö";
		power = 7;
		price = 3000;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.RING;
		name = "±Ý¹ÝÁö";
		power = 17;
		price = 6000;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.RING;
		name = "´ÙÀÌ¾Æ¹ÝÁö";
		power = 35;
		price = 20000;
		this.items.add(new Item(kind, name, power, price));
	}
	
	public void buyWeapon() {
		
	}

	public void buyArmor() {
		
	}

	public void buyRing() {
		
	}
}
