package fi.projecttest.testProject.domain;

public class ShoppingList {
	
	private String name;
	private String brand;
	private String supermarket;
	private double price;
	
	public ShoppingList() {}
	
	public ShoppingList(String name, String brand, String supermarket, double price) {
		super();
		this.name = name;
		this.brand = brand;
		this.supermarket = supermarket;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSupermarket() {
		return supermarket;
	}
	public void setSupermarket(String supermarket) {
		this.supermarket = supermarket;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ShoppingList [name=" + name + ", brand=" + brand + ", supermarket=" + supermarket + ", price=" + price
				+ "]";
	}

}
