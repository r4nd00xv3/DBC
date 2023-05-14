package com.example.dbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Associado extends JpaRepository<com.example.dbc.model.Associado, Long> {

    @Query(value = "select l from Associado l where upper(trim(l.nome)) like %?1%")
    List<com.example.dbc.model.Associado> buscAssociado(String name);

}
