package com.cm.dto;

import com.cm.modelo.Tema;

public class SelectTemasDTO {
    private Long id;
    private String description;

    public SelectTemasDTO() {
    }

    public SelectTemasDTO(Tema t) {
        this.id = t.getId();
        this.description = t.getTema();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
