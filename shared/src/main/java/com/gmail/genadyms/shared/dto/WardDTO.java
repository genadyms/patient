package com.gmail.genadyms.shared.dto;

import java.io.Serializable;

public class WardDTO implements Serializable {
    private static final long serialVersionUID = 1L;

     private Integer numberWard;

        public void setNumberWard(Integer numberWard) {
        this.numberWard = numberWard;
    }

    public Integer getNumberWard() {
        return numberWard;
    }

    @Override
    public String toString() {
        return "WardDTO{" + ", numberWard=" + numberWard + '}';
    }
}
