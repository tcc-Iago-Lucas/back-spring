package com.cm.modelo;

import com.cm.dto.AlternativaDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Alternativa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id  @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private String alternativa;
    private Boolean certo;

    @JsonBackReference
    @ManyToOne
    @JoinColumn( name = "questao_id")
    private Questao questao;

    @Column(nullable = false, length = 50 , unique= true)
    private String codigo;

    public Alternativa(AlternativaDTO a) {
        this.alternativa = a.getAlternativa();
        this.certo = a.getCerto();
    }

    public Alternativa() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    public Boolean getCerto() {
        return certo;
    }

    public void setCerto(Boolean certo) {
        this.certo = certo;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
