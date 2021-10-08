package Controller;

import java.util.ArrayList;

import models.Item;

public class Shop {
	public static Shop instance = new Shop();
	private ArrayList<Item> items = new ArrayList<>();
	
	private Shop() {
		int kind = Item.WEAPON;
		String name = "������";
		int power = 3;
		int price = 1000;
		this.items.add(new Item(kind, name, power, price));
		
		kind = Item.WEAPON;
		name = "ö��";
		power = 5;
		price = 2000;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.WEAPON;
		name = "�����Ǿ�";
		power = 7;
		price = 2500;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.ARMOR;
		name = "Ƽ����";
		power = 1;
		price = 300;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.ARMOR;
		name = "���װ���";
		power = 4;
		price = 800;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.ARMOR;
		name = "��ö����";
		power = 7;
		price = 1500;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.RING;
		name = "������";
		power = 7;
		price = 3000;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.RING;
		name = "�ݹ���";
		power = 17;
		price = 6000;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.RING;
		name = "���̾ƹ���";
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
