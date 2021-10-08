package Controller;

import java.util.ArrayList;
import models.Item;
import models.Player;

public class Shop {
	public static Shop instance = new Shop();
	private ArrayList<Item> items = new ArrayList<>();
	private PlayerController pc = PlayerController.instance;
	
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
	
	public void buy(int kind) {
		System.out.println("���: " + pc.getPlayer(Player.log).getGold());
		System.out.print("������ ������ ��ȣ[0.�ڷΰ���]: ");
		String select = Guild.sc.next();
		
		try {
			int sel = Integer.parseInt(select) - 1;
			
			ArrayList<Item> items = new ArrayList<Item>(); 
			for(Item item : this.items) {
				if(item.getKind() == kind) items.add(item);
			}
			
			if(sel >= 0 && sel < items.size()) {
				for(Item wholeItems : this.items) {
					if(wholeItems.getName().equals(items.get(sel).getName())){
						pc.getPlayer(Player.log).getMyItems().add(wholeItems);
					}
				}
			}
			
			else if(sel == -1) return;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void buyWeapon() {
		int number = 1;
		System.out.println("----- ���� ����Ʈ -----");
		for(Item item : this.items) {
			System.out.println(number++ + ". ");
			if(item.getKind() == Item.WEAPON) item.showItem();
		}
		buy(Item.WEAPON);
	}

	public void buyArmor() {
		int number = 1;
		System.out.println("----- �� ����Ʈ -----");
		for(Item item : this.items) {
			System.out.println(number++ + ". ");
			if(item.getKind() == Item.ARMOR) item.showItem();
		}
		buy(Item.WEAPON);
	}

	public void buyRing() {
		int number = 1;
		System.out.println("----- ���� ����Ʈ -----");
		for(Item item : this.items) {
			System.out.println(number++ + ". ");
			if(item.getKind() == Item.RING) item.showItem();
		}
		buy(Item.WEAPON);
	}
}
