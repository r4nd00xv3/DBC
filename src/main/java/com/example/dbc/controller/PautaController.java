package com.example.dbc.controller;

import com.example.dbc.repository.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class PautaController {
    @Autowired
    private Pauta associadorep;

    @PostMapping(value = "salvarPautas")
    @ResponseBody
    public ResponseEntity<com.example.dbc.model.Pauta> salvar(@RequestBody com.example.dbc.model.Pauta associado) { /* Recebe os dados para salvar */

        com.example.dbc.model.Pauta associado1 = associadorep.save(associado);

        return new ResponseEntity<com.example.dbc.model.Pauta>(associado1, HttpStatus.CREATED);

    }

    @PostMapping(value = "listaTodosAssociadosp")
    @ResponseBody
    public ResponseEntity<List<com.example.dbc.model.Pauta>> listlic() {

        List<com.example.dbc.model.Pauta> associados = (List<com.example.dbc.model.Pauta>) associadorep.findAll();/* executa a consulta no banco de dados */

        return new ResponseEntity<List<com.example.dbc.model.Pauta>>(associados, HttpStatus.OK);/* Retorna a lista em JSON */

    }

    @PostMapping(value = "buscaruseridAssociadop/{id}")
    @ResponseBody
    public ResponseEntity<com.example.dbc.model.Pauta> buscarprodid(@PathVariable(name = "id") Long id) { /* Recebe os dados para consultar */

        com.example.dbc.model.Pauta associado = associadorep.findById(id).get();

        return new ResponseEntity<com.example.dbc.model.Pauta>(associado, HttpStatus.OK);

    }

    @PostMapping(value = "atualizarAssociadop")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody com.example.dbc.model.Pauta associado) { /* Recebe os dados para salvar */

        if (associado.getId() == null) {
            return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.UNAUTHORIZED);
        }

        com.example.dbc.model.Pauta associado1 = associadorep.saveAndFlush(associado);

        return new ResponseEntity<com.example.dbc.model.Pauta>(associado1, HttpStatus.CREATED);
    }


    @ResponseBody
    @PostMapping(value = "/deleteAssociadoIdp/{id}")
    public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) {

        associadorep.deleteById(id);

        return new ResponseEntity("Associado Removida com sucesso!",HttpStatus.OK);
    }

   @PostMapping(value = "buscarPorNomeAssociadop")
    @ResponseBody
    public ResponseEntity<List<com.example.dbc.model.Pauta>> buscarPorNomeParceiro(@RequestParam(name = "nomeassociado") String name){

        List<com.example.dbc.model.Pauta> associados = associadorep.buscPauta(name.trim().toUpperCase());

        return new ResponseEntity<List<com.example.dbc.model.Pauta>>(associados, HttpStatus.OK);

    }
    @GetMapping("/consulta")
    public List<Object> fazerConsultaComAlias() {
        return associadorep.Search();
    }

        }

