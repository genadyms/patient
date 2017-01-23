package org.gmail.genadyms.shared;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient extends AbstractModel {

	@Basic
	@Column(name = "first_name")//, nullable = false, updatable = false, length = 150)
	private String firstName;

	@Basic
	@Column(name = "second_name")//, nullable = false, updatable = false, length = 300)
	private String secondName;
	
	@Basic
	@Column(name = "address")//, nullable = false, length = 300)
	private String address;

	@Basic
    @Column(name = "created")
	private Date created;

	
	
//	@Id
//	@Column(name = "id")
////	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	@Override
	public String toString() {
		return "Patient [firstName=" + firstName + ", secondName=" + secondName + ", address=" + address + ", created="
				+ created + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
