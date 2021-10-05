package models;

public class Bank {
	public static Bank instance = new Bank();
	public static int log = -1;
	private String brand;
	
	public String getBrand() {
		return this.brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
}
