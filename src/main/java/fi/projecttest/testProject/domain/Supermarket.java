package fi.projecttest.testProject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="supermarket")
public class Supermarket {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "shopid")
    private Long sID;
    
    private String sName;
    private String supGroup;
    private String address;
    private String contactPerson;
    private String sPhone;
    
    public Supermarket() {}

	public Supermarket(String name, String group, String address, String person, String phone) {
		super();
		this.sName = name;
		this.supGroup = group;
		this.address = address;
		this.contactPerson = person;
		this.sPhone = phone;
	}

	public Long getsID() {
		return sID;
	}

	public void setsID(Long sID) {
		this.sID = sID;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getSupGroup() {
		return supGroup;
	}

	public void setSupGroup(String supGroup) {
		this.supGroup = supGroup;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getsPhone() {
		return sPhone;
	}

	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}

	@Override
	public String toString() {
		return "Supermarket [sID=" + sID + ", sName=" + sName + ", supGroup=" + supGroup + ", address=" + address
				+ ", contactPerson=" + contactPerson + ", sPhone=" + sPhone + "]";
	}

}