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
		String name = "나무검";
		int power = 3;
		int price = 1000;
		this.items.add(new Item(kind, name, power, price));
		
		kind = Item.WEAPON;
		name = "철검";
		power = 5;
		price = 2000;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.WEAPON;
		name = "레이피어";
		power = 7;
		price = 2500;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.ARMOR;
		name = "티셔츠";
		power = 1;
		price = 300;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.ARMOR;
		name = "가죽갑옷";
		power = 4;
		price = 800;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.ARMOR;
		name = "강철갑옷";
		power = 7;
		price = 1500;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.RING;
		name = "은반지";
		power = 7;
		price = 3000;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.RING;
		name = "금반지";
		power = 17;
		price = 6000;
		this.items.add(new Item(kind, name, power, price));

		kind = Item.RING;
		name = "다이아반지";
		power = 35;
		price = 20000;
		this.items.add(new Item(kind, name, power, price));
	}
	
	public void buy(int kind) {
		System.out.println("골드: " + pc.getPlayer(Player.log).getGold());
		System.out.print("구입할 아이템 번호[0.뒤로가기]: ");
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
		System.out.println("----- 무기 리스트 -----");
		for(Item item : this.items) {
			System.out.println(number++ + ". ");
			if(item.getKind() == Item.WEAPON) item.showItem();
		}
		buy(Item.WEAPON);
	}

	public void buyArmor() {
		int number = 1;
		System.out.println("----- 방어구 리스트 -----");
		for(Item item : this.items) {
			System.out.println(number++ + ". ");
			if(item.getKind() == Item.ARMOR) item.showItem();
		}
		buy(Item.WEAPON);
	}

	public void buyRing() {
		int number = 1;
		System.out.println("----- 반지 리스트 -----");
		for(Item item : this.items) {
			System.out.println(number++ + ". ");
			if(item.getKind() == Item.RING) item.showItem();
		}
		buy(Item.WEAPON);
	}
}
