package controller;

import java.util.ArrayList;
import java.util.Iterator;
import models.Cart;
import models.Item;
import models.User;

public class ItemController {
	public static ItemController instance = new ItemController();
	private UserController uc = UserController.instance;
	private ArrayList<Item> items = null;
	private ArrayList<Cart> jangs = null;
	private ArrayList<String> category = null;
	
	private ItemController() {
		this.items = new ArrayList<Item>();
		this.jangs = new ArrayList<Cart>();
		this.category = new ArrayList<>();
	}

	//������ �߰�
	public void addItem() {
		System.out.print("[�������߰�]�������̸�: ");
		String name = uc.sc.next();
		
		int chk = findNameIdx(name);
		
		if(chk == -1) {
			System.out.print("[�������߰�]�����۰���: ");
			String strPrice = uc.sc.next();
			
			try {
				int price = Integer.parseInt(strPrice);
				int idx = selectCategory();
				
				if(idx != -1) {
					items.add(new Item(name, price, category.get(idx)));
					System.out.println("[����]�������� �߰��߽��ϴ�.");				
				}
				else System.out.println("[����]�ش� ī�װ��� �������� �ʽ��ϴ�.");
			} catch (Exception e) {
				System.out.println("[����]�߸� �Է��ϼ̽��ϴ�.");
			}			
		}
		else System.out.println("[����] �ش� �������� �̹� �����մϴ�.");
	}

	
	//������ �������� �ε���ã��
	private int findNameIdx(String name) {
		int chk = -1;
		for(int i = 0; i < items.size(); i++) {
			if(this.items.get(i).getItemName().equals(name)) chk = i;
		}
		
		return chk;
	}
	
	//ī�װ� ����
	private int selectCategory() {
		int sel = 0;

		if(showCategoryList()) {
			System.out.print("����> ");
			String select = uc.sc.next();
			
			try {
				sel = Integer.parseInt(select) - 1;
			} catch (Exception e) {
				System.out.println("[����]�߸� �Է��ϼ̽��ϴ�.");
				sel = -1;
			}			
		}
		else System.out.println("[����]ī�װ����� ���� �߰����ּ���.");
		
		return sel;
	}

	//�����ۻ���
	public void removeItem() {
		if(getItemSize() > 0) {
			showItemList();
			System.out.print("������ ������ ����> ");
			String select = uc.sc.next();
			
			try {
				int sel = Integer.parseInt(select) - 1;
				
				if(sel >= 0 && sel < getItemSize()) {
					removeJang(getItem(sel));
					removeUserJang(getItem(sel));
					this.items.remove(sel);
					System.out.println("[����]�ش� �������� �����߽��ϴ�.");
				}
				else System.out.println("[����]�������� �߸� �����ϼ̽��ϴ�.");
			} catch (Exception e) {
				System.out.println("[����]�߸� �����ϼ̽��ϴ�.");
			}			
		}
		else System.out.println("[����]������ ����� ��� �ֽ��ϴ�.");
	}
	
	//��ü ������ ����Ʈ �ҷ�����
	public void showItemList() {
		System.out.println("<������ ���>");
		for(int i = 0; i < items.size(); i++) {
			System.out.print((i+1) + ".");
			items.get(i).showItem();
		}
	}

	//Ư�� ������ ����Ʈ �ҷ�����
	public ArrayList<Item> showItemList(int sel) {
		int numbering = 1;
		ArrayList<Item> list = new ArrayList<>();
		
		System.out.printf("--- %s �ڳ� ---\n", this.category.get(sel));
		for(int i = 0; i < getItemSize(); i++) {
			if(getItem(i).getCategory().equals(getCategory(sel))) {
				System.out.print(numbering++ + ".");
				getItem(i).showItem();
				list.add(getItem(i));
			}
		}
		
		return list;
	}
	
	//ī�װ� ����Ʈ �ҷ�����
	public boolean showCategoryList() {
		System.out.println("<ī�װ� ���>");
		
		if(category.size() > 0) {
			System.out.println("0.����");
			for(int i = 0; i < category.size(); i++) {
				System.out.printf("%d.%s\n", (i+1), category.get(i));
			}	
			return true;
		}
		else System.out.println("����� �������� �ʽ��ϴ�.");
		return false;
	}

	//ī�װ� �߰�
	public void addCategory() {
		System.out.print("[ī�װ��߰�]ī�װ� �Է�: ");
		String name = uc.sc.next();
		
		boolean chk = true;
		for(int i = 0; i < category.size(); i++) {
			if(category.get(i).equals(name)) chk = false;
		}
		
		if(chk) {
			category.add(name);
			System.out.println("[����]ī�װ��� �߰��߽��ϴ�.");
		}
		else System.out.println("[����]�ش� ī�װ��� �̹� �����մϴ�.");
	}

	//ī�װ� ����
	public void removeCategory() {
		if(getCategorySize() > 0) {
			showCategoryList();
			System.out.print("������ ī�װ� ����> ");
			String select = uc.sc.next();
			
			try {
				int sel = Integer.parseInt(select) - 1;
				
				if(sel >= 0 && sel < getCategorySize()) {
					for(Iterator<Item> iter = this.items.iterator(); iter.hasNext();) {
						Item item = iter.next();
			            if(item.getCategory().equals(getCategory(sel))) {
			            	removeJang(item);
			            	removeUserJang(item);
			                iter.remove();
			            }    
					}

					this.category.remove(sel);
					System.out.println("[����]�ش� ī�װ� �� ī�װ��� �ش��ϴ� ��� �������� �����˴ϴ�.");
				}
				else System.out.println("[����]ī�װ��� �߸� �����ϼ̽��ϴ�.");
			} catch (Exception e) {
				System.out.println("[����]�߸� �����ϼ̽��ϴ�.");
			}			
		}
		else System.out.println("[����]ī�װ� ����� ��� �ֽ��ϴ�.");
	}
	
	//��ٱ��� ��ü���
	public void showJangList() {
		System.out.println("--- ��ٱ��� ��ü��� ---");
		for(int i = 0; i < jangs.size(); i++) {
			jangs.get(i).showCart();
		}
	}
	
	//������ ���� �� �߾� ��ٱ��� ��� ����
	public void removeJang(Item item) {
		ArrayList<Cart> list = this.jangs;
		
		for(Iterator<Cart> iter = list.iterator(); iter.hasNext();) {
			Cart cart = iter.next();
            if(cart.getItemName().equals(item.getItemName())) {
                iter.remove();
            }    
		}
	}
	
	//������ ���� �� ���� ��ٱ��� ��� ����
	public void removeUserJang(Item item) {
		for(int i = 0; i < uc.getUserSize(); i++) {
			ArrayList<Cart> list = uc.getUser(i).getJang();
			
			for(Iterator<Cart> iter = list.iterator(); iter.hasNext();) {
				Cart cart = iter.next();
				if(cart.getItemName().equals(item.getItemName())) {
					iter.remove();
				}    
			}
		}		
	}

	//������ ����
	public void pickItem(ArrayList<Item> list) {
		System.out.print("����> ");
		String select = uc.sc.next();
		
		try {
			int sel = Integer.parseInt(select) - 1;
			
			if(sel >= 0 && sel < list.size()) {
				String item = list.get(sel).getItemName();
				Cart newCart = new Cart(uc.getUser(User.log).getCode(),item);
				uc.getUser(User.log).addJang(newCart);
				jangs.add(newCart);
				System.out.println(item + "�� ��ٱ��Ͽ� ��ҽ��ϴ�.");
			}
			else System.out.println("[����]�߸� �����ϼ̽��ϴ�.");
		} catch (Exception e) {
			System.out.println("[����]�߸� �����ϼ̽��ϴ�.");
		}
	}

	//�� ��ٱ��� ��ȸ
	public ArrayList<String> myJang() {
		int numbering = 1;
		User me = uc.getUser(User.log);
		ArrayList<String> myItem = new ArrayList<>();
		
		for(Cart cart : uc.getUser(User.log).getJang()) {
			String itemName = cart.getItemName();
			myItem.add(itemName);
		}
		
		ArrayList<String> list = nonDuplicateList(myItem);
		ArrayList<Integer> cnt = countItem(myItem, list);
		
		System.out.printf("--- %s�� ��ٱ��� ��� ---\n", me.getName());
		for(int i = 0; i < list.size(); i++) {
			String itemName = list.get(i);
			int price = 0;
			
			for(Item item : this.items) {
				if(item.getItemName().equals(itemName)) {
					price = item.getPrice();
				}
			}

			System.out.print(numbering++ + ".");
			System.out.printf("������:%s ����:%d��[%d��]\n", itemName, price, cnt.get(i));	
		}
		System.out.println("----------------------");
		
		return list;
	}

	//��ٱ��� ������ �ߺ�����
	private ArrayList<String> nonDuplicateList(ArrayList<String> myItem){
		ArrayList<String> list = new ArrayList<>();
		
		for(int i = 0; i < myItem.size(); i++) {
			String itemName = myItem.get(i);
						
			if(list.size() == 0) list.add(itemName);
			else {
				boolean chk = true;
				for(int j = 0; j < list.size(); j++) {
					if(list.get(j).equals(itemName)) chk = false;					
				}										
				if(chk) list.add(itemName);
			}
		}
		
		return list;
	}
	
	//�� �����ۺ��� ���� ���ϱ�
	private ArrayList<Integer> countItem(ArrayList<String> myItem, ArrayList<String> list){
		ArrayList<Integer> cntList = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			String itemName = list.get(i);
			int cnt = 0;
			
			for(int j = 0; j < myItem.size(); j++) {
				if(myItem.get(j).equals(itemName)) cnt++;
			}
			
			cntList.add(cnt);
		}
		
		return cntList;
	}

	//��ٱ��� ����
	public void removeJang() {
		ArrayList<Cart> myCart = uc.getUser(User.log).getJang();
		
		if(myCart.size() == 0) {
			System.out.println("[����]��ٱ��� ����� �������� �ʽ��ϴ�.");
			return;
		}
		
		ArrayList<String> list = myJang();
		System.out.print("����> ");
		String select = uc.sc.next();
		
		try {
			int sel = Integer.parseInt(select) - 1;
			
			if(sel >= 0 && sel < list.size()) {
				int chk = -1;
				for(int i = 0; i < myCart.size(); i++) {
					if(list.get(sel).equals(myCart.get(i).getItemName())) chk = i;
				}
				
				uc.getUser(User.log).removeJang(chk);
				this.jangs.remove(chk);
				System.out.println("[����]�����Ǿ����ϴ�.");
			}
			else System.out.println("[����]�߸� �����ϼ̽��ϴ�.");
			
		} catch (Exception e) {}
	}

	//��ٱ��� ��� ����
	public void buyJang() {
		ArrayList<Cart> myJangList = uc.getUser(User.log).getJang();
		
		if(myJangList.size() == 0) {
			System.out.println("[����]��ٱ��� ����� �������� �ʽ��ϴ�.");
			return;
		}
		
		myJang();
		int money = 0;
		for(Cart cart : myJangList) {
			for(Item item : this.items) {
				if(item.getItemName().equals(cart.getItemName())) {
					money += item.getPrice();
				}
			}
		}
		
		System.out.println("���� �����ݾ�: " + money + "��");
		System.out.println("�����Ͻðڽ��ϱ�? YES:1 / No:0");
		String choice = uc.sc.next();
		
		try {
			int sel = Integer.parseInt(choice);
			
			if(sel == 1) {
				System.out.print("[����]�����Է�: ");
				int cash = uc.sc.nextInt();
				
				if(cash < money) System.out.println("[�������]�ܾ��� �����մϴ�.");
				else {
					System.out.println("�Ž�����: " + (cash - money) + "��");
					System.out.println("[��������]������ �Ϸ��Ͽ����ϴ�.");
					deleteJangProcess(uc.getUser(User.log).getCode());
					uc.getUser(User.log).clearJang();
				}
			}
			else if(sel == 0) {
				System.out.println("[�������]�������� ���ư��ϴ�.");
				return;
			}
			else System.out.println("[����]�߸� �Է��ϼ̽��ϴ�.");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//���� ��ٱ��Ͽ��� ������ ����Ʈ �߾ӵ����Ϳ����� ó�����ֱ�
	public void deleteJangProcess(long code) {
		ArrayList<Cart> list = this.jangs;
		
		for(Iterator<Cart> iter = list.iterator(); iter.hasNext();) {
			Cart cart = iter.next();
            if(cart.getUserCode() == code) {
                iter.remove();
            }    
		}
	}
	
	public int getItemSize() {
		return this.items.size();
	}
	
	public int getCategorySize() {
		return this.category.size();
	}
	
	public Item getItem(int idx) {
		return this.items.get(idx);
	}
	
	public String getCategory(int idx) {
		return this.category.get(idx);
	}
	
	public int getUserJangSize() {
		return uc.getUser(User.log).getJang().size();
	}

	public ArrayList<Item> getItemList(){
		return this.items;
	}
	
	public ArrayList<String> getCategoryList(){
		return this.category;
	}
	
	public ArrayList<Cart> getJangList(){
		return this.jangs;
	}
	
	public void clearCategory() {
		this.category.clear();
	}

	public void clearItem() {
		this.items.clear();
	}

	public void clearJang() {
		this.jangs.clear();
	}
	
	public void showAllUserData() {
		for(int i = 0; i < uc.getUserSize(); i++) {
			System.out.print((i+1) + ". ");
			uc.getUserList().get(i).printUserData();
			
			System.out.println("[��ٱ��ϸ��]");
			for(Cart myjang : uc.getUser(i).getJang()) {
				String itemName = myjang.getItemName();
				int price = 0;
				
				for(Item item : this.items) {
					if(item.getItemName().equals(itemName)) {
						price = item.getPrice();
					}
				}
				System.out.printf("{������:%s : �ݾ�:%d��}\n", itemName, price);
			}
		}
	}
	
	public void itemSetting(Item item) {
		this.items.add(item);
	}
	
	public void categorySetting(String category) {
		this.category.add(category);
	}
	
	public void jangSetting(Cart cart) {
		this.jangs.add(cart);
	}
	
	public void userJangSetting(int idx, int idx2) {
		uc.getUser(idx).getJang().add(this.jangs.get(idx2));
	}
}
