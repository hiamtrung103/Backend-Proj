package fi.projecttest.testProject.domain;

/*
 This class is needed to save both product and its price in one form. 
 I could not find info on how to save several different objects in thymeleaf form, 
 so I decided to create a new class, where the data for those 2 entities will be stored,
 and which later will be used to create those entities' data
 */

public class ProductAndPrice {
	
	//elements for ProductInfo
	private String name;
	private String brand;
	private Long id;
	
	//elements for ProductPrice
	private double price;
	
	public ProductAndPrice() {}

	public ProductAndPrice(String name, String brand, double price, Long id) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.id = id;
	}
	
	public ProductAndPrice(String name, String brand, double price) {
		super();
		this.name = name;
		this.brand = brand;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
