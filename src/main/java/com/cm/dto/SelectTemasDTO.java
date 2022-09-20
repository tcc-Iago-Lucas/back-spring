package com.cm.dto;

import com.cm.modelo.Tema;

public class SelectTemasDTO {
    private Long value;
    private String label;

    public SelectTemasDTO() {
    }

    public SelectTemasDTO(Tema t) {
        this.value = t.getId();
        this.label = t.getTema();
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
