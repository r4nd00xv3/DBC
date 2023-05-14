package com.example.dbc.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "associado")
@SequenceGenerator(name = "seq_associado", sequenceName = "seq_associado", allocationSize = 1, initialValue = 1)
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_associado")
    private Long id;

    @Column(nullable = false)
    private String nome;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "pauta_associados",
            uniqueConstraints = @UniqueConstraint (columnNames = {"pauta_id", "associado_id"} , name = "unique_acesso_user"),

            joinColumns = @JoinColumn(name = "associado_id", referencedColumnName = "id",
                    table = "associado", unique = false, foreignKey = @ForeignKey(name = "associado_fk",
                    value = ConstraintMode.CONSTRAINT)),

            inverseJoinColumns = @JoinColumn(name = "pauta_id", unique = false, referencedColumnName = "id",
                    table = "pauta", foreignKey = @ForeignKey(name = "pauta_fk", value = ConstraintMode.CONSTRAINT)))
    private Pauta pauta;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }
}
