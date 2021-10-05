package models;

public class Cart {
	private long userCode;
	private String itemName;
	
	public Cart(long userCode, String itemName) {
		this.userCode = userCode;
		this.itemName = itemName;
	}
	
	public long getUserCode() {
		return this.userCode;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public void showCart() {
		System.out.printf("유저코드:%d  아이템:%s\n", this.userCode, this.itemName);
	}
}
