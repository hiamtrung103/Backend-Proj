package fi.projecttest.testProject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product_info")
public class ProductInfo {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long pID;
	
	private String pName;
	private String pBrand;
	
	public ProductInfo() {}

	public ProductInfo(String pName, String pBrand) {
		super();
		this.pName = pName;
		this.pBrand = pBrand;
	}

	public Long getpID() {
		return pID;
	}

	public void setpID(Long pID) {
		this.pID = pID;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpBrand() {
		return pBrand;
	}

	public void setpBrand(String pBrand) {
		this.pBrand = pBrand;
	}

	@Override
	public String toString() {
		return "ProductInfo [pID=" + pID + ", pName=" + pName + ", pBrand=" + pBrand + "]";
	}
	
	

}
