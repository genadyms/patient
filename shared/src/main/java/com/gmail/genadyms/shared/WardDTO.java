package com.gmail.genadyms.shared;

import java.io.Serializable;

public class WardDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer number;
	private Integer countBeds;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getCountBeds() {
		return countBeds;
	}

	public void setCountBeds(Integer countBeds) {
		this.countBeds = countBeds;
	}

	@Override
	public String toString() {
		return "WardDTO [id=" + id + ", number=" + number + ", countBeds=" + countBeds + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
