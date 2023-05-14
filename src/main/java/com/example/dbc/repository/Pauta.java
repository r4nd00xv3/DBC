package com.example.dbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pauta extends JpaRepository<com.example.dbc.model.Pauta, Long> {

    @Query(value = "select l from Pauta l where upper(trim(l.pautaDesc)) like %?1%")
    List<com.example.dbc.model.Pauta> buscPauta(String name);

    @Query(value = "SELECT pauta_id, COUNT(pauta_id) AS maisvotado " +
            "FROM pauta_associados " +
            "GROUP BY  pauta_id " +
            "HAVING COUNT(pauta_id) >= 1; ", nativeQuery = true)
    List<Object>Search();
}

