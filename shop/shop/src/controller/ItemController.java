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

	//아이템 추가
	public void addItem() {
		System.out.print("[아이템추가]아이템이름: ");
		String name = uc.sc.next();
		
		int chk = findNameIdx(name);
		
		if(chk == -1) {
			System.out.print("[아이템추가]아이템가격: ");
			String strPrice = uc.sc.next();
			
			try {
				int price = Integer.parseInt(strPrice);
				int idx = selectCategory();
				
				if(idx != -1) {
					items.add(new Item(name, price, category.get(idx)));
					System.out.println("[성공]아이템을 추가했습니다.");				
				}
				else System.out.println("[실패]해당 카테고리가 존재하지 않습니다.");
			} catch (Exception e) {
				System.out.println("[실패]잘못 입력하셨습니다.");
			}			
		}
		else System.out.println("[실패] 해당 아이템이 이미 존재합니다.");
	}

	
	//아이템 존재유무 인덱스찾기
	private int findNameIdx(String name) {
		int chk = -1;
		for(int i = 0; i < items.size(); i++) {
			if(this.items.get(i).getItemName().equals(name)) chk = i;
		}
		
		return chk;
	}
	
	//카테고리 선택
	private int selectCategory() {
		int sel = 0;

		if(showCategoryList()) {
			System.out.print("선택> ");
			String select = uc.sc.next();
			
			try {
				sel = Integer.parseInt(select) - 1;
			} catch (Exception e) {
				System.out.println("[실패]잘못 입력하셨습니다.");
				sel = -1;
			}			
		}
		else System.out.println("[실패]카테고리부터 먼저 추가해주세요.");
		
		return sel;
	}

	//아이템삭제
	public void removeItem() {
		if(getItemSize() > 0) {
			showItemList();
			System.out.print("삭제할 아이템 선택> ");
			String select = uc.sc.next();
			
			try {
				int sel = Integer.parseInt(select) - 1;
				
				if(sel >= 0 && sel < getItemSize()) {
					removeJang(getItem(sel));
					removeUserJang(getItem(sel));
					this.items.remove(sel);
					System.out.println("[성공]해당 아이템을 삭제했습니다.");
				}
				else System.out.println("[실패]아이템을 잘못 선택하셨습니다.");
			} catch (Exception e) {
				System.out.println("[실패]잘못 선택하셨습니다.");
			}			
		}
		else System.out.println("[실패]아이템 목록이 비어 있습니다.");
	}
	
	//전체 아이템 리스트 불러오기
	public void showItemList() {
		System.out.println("<아이템 목록>");
		for(int i = 0; i < items.size(); i++) {
			System.out.print((i+1) + ".");
			items.get(i).showItem();
		}
	}

	//특정 아이템 리스트 불러오기
	public ArrayList<Item> showItemList(int sel) {
		int numbering = 1;
		ArrayList<Item> list = new ArrayList<>();
		
		System.out.printf("--- %s 코너 ---\n", this.category.get(sel));
		for(int i = 0; i < getItemSize(); i++) {
			if(getItem(i).getCategory().equals(getCategory(sel))) {
				System.out.print(numbering++ + ".");
				getItem(i).showItem();
				list.add(getItem(i));
			}
		}
		
		return list;
	}
	
	//카테고리 리스트 불러오기
	public boolean showCategoryList() {
		System.out.println("<카테고리 목록>");
		
		if(category.size() > 0) {
			System.out.println("0.종료");
			for(int i = 0; i < category.size(); i++) {
				System.out.printf("%d.%s\n", (i+1), category.get(i));
			}	
			return true;
		}
		else System.out.println("목록이 존재하지 않습니다.");
		return false;
	}

	//카테고리 추가
	public void addCategory() {
		System.out.print("[카테고리추가]카테고리 입력: ");
		String name = uc.sc.next();
		
		boolean chk = true;
		for(int i = 0; i < category.size(); i++) {
			if(category.get(i).equals(name)) chk = false;
		}
		
		if(chk) {
			category.add(name);
			System.out.println("[성공]카테고리를 추가했습니다.");
		}
		else System.out.println("[실패]해당 카테고리가 이미 존재합니다.");
	}

	//카테고리 삭제
	public void removeCategory() {
		if(getCategorySize() > 0) {
			showCategoryList();
			System.out.print("삭제할 카테고리 선택> ");
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
					System.out.println("[성공]해당 카테고리 및 카테고리에 해당하는 모든 아이템이 삭제됩니다.");
				}
				else System.out.println("[실패]카테고리를 잘못 선택하셨습니다.");
			} catch (Exception e) {
				System.out.println("[실패]잘못 선택하셨습니다.");
			}			
		}
		else System.out.println("[실패]카테고리 목록이 비어 있습니다.");
	}
	
	//장바구니 전체출력
	public void showJangList() {
		System.out.println("--- 장바구니 전체목록 ---");
		for(int i = 0; i < jangs.size(); i++) {
			jangs.get(i).showCart();
		}
	}
	
	//아이템 삭제 시 중앙 장바구니 목록 삭제
	public void removeJang(Item item) {
		ArrayList<Cart> list = this.jangs;
		
		for(Iterator<Cart> iter = list.iterator(); iter.hasNext();) {
			Cart cart = iter.next();
            if(cart.getItemName().equals(item.getItemName())) {
                iter.remove();
            }    
		}
	}
	
	//아이템 삭제 시 유저 장바구니 목록 삭제
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

	//아이템 선택
	public void pickItem(ArrayList<Item> list) {
		System.out.print("선택> ");
		String select = uc.sc.next();
		
		try {
			int sel = Integer.parseInt(select) - 1;
			
			if(sel >= 0 && sel < list.size()) {
				String item = list.get(sel).getItemName();
				Cart newCart = new Cart(uc.getUser(User.log).getCode(),item);
				uc.getUser(User.log).addJang(newCart);
				jangs.add(newCart);
				System.out.println(item + "을 장바구니에 담았습니다.");
			}
			else System.out.println("[실패]잘못 선택하셨습니다.");
		} catch (Exception e) {
			System.out.println("[실패]잘못 선택하셨습니다.");
		}
	}

	//내 장바구니 조회
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
		
		System.out.printf("--- %s님 장바구니 목록 ---\n", me.getName());
		for(int i = 0; i < list.size(); i++) {
			String itemName = list.get(i);
			int price = 0;
			
			for(Item item : this.items) {
				if(item.getItemName().equals(itemName)) {
					price = item.getPrice();
				}
			}

			System.out.print(numbering++ + ".");
			System.out.printf("아이템:%s 가격:%d원[%d개]\n", itemName, price, cnt.get(i));	
		}
		System.out.println("----------------------");
		
		return list;
	}

	//장바구니 아이템 중복제거
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
	
	//각 아이템별로 개수 구하기
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

	//장바구니 삭제
	public void removeJang() {
		ArrayList<Cart> myCart = uc.getUser(User.log).getJang();
		
		if(myCart.size() == 0) {
			System.out.println("[실패]장바구니 목록이 존재하지 않습니다.");
			return;
		}
		
		ArrayList<String> list = myJang();
		System.out.print("선택> ");
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
				System.out.println("[성공]삭제되었습니다.");
			}
			else System.out.println("[실패]잘못 선택하셨습니다.");
			
		} catch (Exception e) {}
	}

	//장바구니 목록 구입
	public void buyJang() {
		ArrayList<Cart> myJangList = uc.getUser(User.log).getJang();
		
		if(myJangList.size() == 0) {
			System.out.println("[실패]장바구니 목록이 존재하지 않습니다.");
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
		
		System.out.println("최종 결제금액: " + money + "원");
		System.out.println("결제하시겠습니까? YES:1 / No:0");
		String choice = uc.sc.next();
		
		try {
			int sel = Integer.parseInt(choice);
			
			if(sel == 1) {
				System.out.print("[결제]현금입력: ");
				int cash = uc.sc.nextInt();
				
				if(cash < money) System.out.println("[결제취소]잔액이 부족합니다.");
				else {
					System.out.println("거스름돈: " + (cash - money) + "원");
					System.out.println("[결제성공]결제를 완료하였습니다.");
					deleteJangProcess(uc.getUser(User.log).getCode());
					uc.getUser(User.log).clearJang();
				}
			}
			else if(sel == 0) {
				System.out.println("[결제취소]메인으로 돌아갑니다.");
				return;
			}
			else System.out.println("[실패]잘못 입력하셨습니다.");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//유저 장바구니에서 삭제된 리스트 중앙데이터에서도 처리해주기
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
			
			System.out.println("[장바구니목록]");
			for(Cart myjang : uc.getUser(i).getJang()) {
				String itemName = myjang.getItemName();
				int price = 0;
				
				for(Item item : this.items) {
					if(item.getItemName().equals(itemName)) {
						price = item.getPrice();
					}
				}
				System.out.printf("{아이템:%s : 금액:%d원}\n", itemName, price);
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
