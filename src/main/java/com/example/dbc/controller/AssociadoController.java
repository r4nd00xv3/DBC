package com.example.dbc.controller;

import com.example.dbc.repository.Associado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class AssociadoController {
    @Autowired
    private Associado associadorep;

    @PostMapping(value = "salvarAssociado")
    @ResponseBody
    public ResponseEntity<com.example.dbc.model.Associado> salvar(@RequestBody com.example.dbc.model.Associado associado) { /* Recebe os dados para salvar */

        com.example.dbc.model.Associado associado1 = associadorep.save(associado);

        return new ResponseEntity<com.example.dbc.model.Associado>(associado1, HttpStatus.CREATED);

    }

    @PostMapping(value = "listaTodosAssociados")
    @ResponseBody
    public ResponseEntity<List<com.example.dbc.model.Associado>> listlic() {

        List<com.example.dbc.model.Associado> associados = (List<com.example.dbc.model.Associado>) associadorep.findAll();/* executa a consulta no banco de dados */

        return new ResponseEntity<List<com.example.dbc.model.Associado>>(associados, HttpStatus.OK);/* Retorna a lista em JSON */

    }

    @PostMapping(value = "buscaruseridAssociado/{id}")
    @ResponseBody
    public ResponseEntity<com.example.dbc.model.Associado> buscarprodid(@PathVariable(name = "id") Long id) { /* Recebe os dados para consultar */

        com.example.dbc.model.Associado associado = associadorep.findById(id).get();

        return new ResponseEntity<com.example.dbc.model.Associado>(associado, HttpStatus.OK);

    }

    @PostMapping(value = "atualizarAssociado")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody com.example.dbc.model.Associado associado) { /* Recebe os dados para salvar */

        if (associado.getId() == null) {
            return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.UNAUTHORIZED);
        }

        com.example.dbc.model.Associado associado1 = associadorep.saveAndFlush(associado);

        return new ResponseEntity<com.example.dbc.model.Associado>(associado1, HttpStatus.CREATED);
    }


    @ResponseBody
    @PostMapping(value = "/deleteAssociadoId/{id}")
    public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) {

        associadorep.deleteById(id);

        return new ResponseEntity("Associado Removida com sucesso!",HttpStatus.OK);
    }

   @PostMapping(value = "buscarPorNomeAssociado")
    @ResponseBody
    public ResponseEntity<List<com.example.dbc.model.Associado>> buscarPorNomeParceiro(@RequestParam(name = "nomeassociado") String name){

        List<com.example.dbc.model.Associado> associados = associadorep.buscAssociado(name.trim().toUpperCase());

        return new ResponseEntity<List<com.example.dbc.model.Associado>>(associados, HttpStatus.OK);

    }



        }

