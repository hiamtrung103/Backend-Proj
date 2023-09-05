package fi.projecttest.testProject.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductPriceID implements Serializable {
	
	private static final long   serialVersionUID    = -8173857210615808268L;
	
	private Long sID;
	private Long pID;
	
	public ProductPriceID() {}

	public ProductPriceID(Long sID, Long pID) {
		super();
		this.sID = sID;
		this.pID = pID;
	}

	public Long getsID() {
		return sID;
	}

	public void setsID(Long sID) {
		this.sID = sID;
	}

	public Long getpID() {
		return pID;
	}

	public void setpID(Long pID) {
		this.pID = pID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pID == null) ? 0 : pID.hashCode());
		result = prime * result + ((sID == null) ? 0 : sID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductPriceID other = (ProductPriceID) obj;
		if (pID == null) {
			if (other.pID != null)
				return false;
		} else if (!pID.equals(other.pID))
			return false;
		if (sID == null) {
			if (other.sID != null)
				return false;
		} else if (!sID.equals(other.sID))
			return false;
		return true;
	}
	
}
