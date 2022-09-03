package com.cm.dto.out;

import com.cm.modelo.Tema;

import java.util.ArrayList;
import java.util.List;

public class TemaDTOut {
    private Long id;
    private String description;
    private List<QuestaoDTOut> questoes = new ArrayList<>();

    public TemaDTOut() {
    }

    public TemaDTOut(Tema tema) {
        this.id = tema.getId();
        this.description = tema.getTema();
        tema.getQuestaos().forEach(q ->{
            this.questoes.add(new QuestaoDTOut(q));
        });
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

    public List<QuestaoDTOut> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<QuestaoDTOut> questoes) {
        this.questoes = questoes;
    }
}
