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
		System.out.printf("�����ڵ�:%d  ������:%s\n", this.userCode, this.itemName);
	}
}
