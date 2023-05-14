package com.example.dbc.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "pauta")
@SequenceGenerator(name = "seq_pauta", sequenceName = "seq_pauta", allocationSize = 1, initialValue = 1)
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pauta")
    private Long id;

    @Column(nullable = false)
    private String pautaDesc;

    private Boolean voto= Boolean.FALSE;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPautaDesc() {
        return pautaDesc;
    }

    public void setPautaDesc(String pautaDesc) {
        this.pautaDesc = pautaDesc;
    }

    public Boolean getVoto() {
        return voto;
    }

    public void setVoto(Boolean voto) {
        this.voto = voto;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pauta pauta = (Pauta) o;
        return Objects.equals(id, pauta.id) && Objects.equals(pautaDesc, pauta.pautaDesc) && Objects.equals(voto, pauta.voto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pautaDesc, voto);
    }
}
