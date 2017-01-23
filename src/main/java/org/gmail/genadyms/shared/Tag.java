package org.gmail.genadyms.shared;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Tag extends AbstractModel {
	@Override
	public String toString() {
		return "Tag [name=" + name + ", getId()=" + getId() + "]";
	}

	private static final long serialVersionUID = 1L;

	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}