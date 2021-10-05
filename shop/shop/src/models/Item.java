package models;

public class Item {
	private String itemName;
	private int price;
	private String category;
	
	public Item(String itemName, int price, String category) {
		this.itemName = itemName;
		this.price = price;
		this.category = category;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void showItem() {
		System.out.printf("%s : %d¿ø[%s]\n", this.itemName, this.price, this.category);
	}
}
