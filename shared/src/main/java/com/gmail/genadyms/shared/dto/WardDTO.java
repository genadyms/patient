package com.gmail.genadyms.shared.dto;

import java.io.Serializable;

public class WardDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer numberWard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumberWard(Integer numberWard) {
        this.numberWard = numberWard;
    }

    public Integer getNumberWard() {
        return numberWard;
    }

    @Override
    public String toString() {
        return "WardDTO{" +
                "id=" + id +
                ", numberWard=" + numberWard +
                '}';
    }
}
