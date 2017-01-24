package org.gmail.genadyms.server.domain.datamodel;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag implements Serializable {// extends AbstractModel {
//	@Override
//	public String toString() {
//		return "Tag [name=" + name + ", getId()=" + getId() + "]";
//	}

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
//	@Basic
	@Column(name="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}