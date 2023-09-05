package fi.projecttest.testProject.domain;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "product_price")
public class ProductPrice {
	
	@EmbeddedId
    private ProductPriceID id;
	
	private double price;

	public ProductPrice() {}
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="sid")
    @MapsId("sID")
    private Supermarket sID;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="pid")
    @MapsId("pID")
    private ProductInfo pID;

	public ProductPrice(ProductPriceID id, Supermarket sID, ProductInfo pID, double price) {
		super();
		this.id = id;
		this.sID = sID;
		this.pID = pID;
		this.price = price;
	}
	
	public ProductInfo getpID() {
		return pID;
	}

	public void setpID(ProductInfo pID) {
		this.pID = pID;
	}

	public ProductPriceID getId() {
		return id;
	}

	public void setId(ProductPriceID id) {
		this.id = id;
	}

	public Supermarket getsID() {
		return sID;
	}

	public void setsID(Supermarket sID) {
		this.sID = sID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductPrice [id=" + id + ", price=" + price + ", sID=" + sID + ", pID=" + pID + "]";
	}
	
}
