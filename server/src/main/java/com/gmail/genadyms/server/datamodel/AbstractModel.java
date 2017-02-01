package com.gmail.genadyms.server.datamodel;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		boolean retVal = false;
		if (obj instanceof AbstractModel) {
			AbstractModel ptr = (AbstractModel) obj;
			retVal = ptr.id.longValue() == this.id;
		}
		return retVal;
	}

}
