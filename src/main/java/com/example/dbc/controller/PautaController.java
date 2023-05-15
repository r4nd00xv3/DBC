package com.example.dbc.controller;

import com.example.dbc.model.dto.CpfDTO;
import com.example.dbc.model.dto.VotosDTO;
import com.example.dbc.repository.Pauta;
import com.example.dbc.service.CpfService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
public class PautaController {
    @Autowired
    private Pauta associadorep;

   @Autowired
   private CpfService cpfService;

    @PostMapping(value = "salvarPautas")
    @ResponseBody
    public ResponseEntity<com.example.dbc.model.Pauta> salvar(@RequestBody com.example.dbc.model.Pauta associado) { /* Recebe os dados para salvar */

        com.example.dbc.model.Pauta associado1 = associadorep.save(associado);

        return new ResponseEntity<com.example.dbc.model.Pauta>(associado1, HttpStatus.CREATED);

    }

    @GetMapping(value = "listaPautas")
    @ResponseBody
    public ResponseEntity<List<com.example.dbc.model.Pauta>> listlic() {

        List<com.example.dbc.model.Pauta> associados = (List<com.example.dbc.model.Pauta>) associadorep.findAll();/* executa a consulta no banco de dados */

        return new ResponseEntity<List<com.example.dbc.model.Pauta>>(associados, HttpStatus.OK);/* Retorna a lista em JSON */

    }

    @PostMapping(value = "buscaruseridPautas/{id}")
    @ResponseBody
    public ResponseEntity<com.example.dbc.model.Pauta> buscarprodid(@PathVariable(name = "id") Long id) { /* Recebe os dados para consultar */

        com.example.dbc.model.Pauta associado = associadorep.findById(id).get();

        return new ResponseEntity<com.example.dbc.model.Pauta>(associado, HttpStatus.OK);

    }

    @PutMapping(value = "atualizarPautas")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody com.example.dbc.model.Pauta associado) { /* Recebe os dados para salvar */

        if (associado.getId() == null) {
            return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.UNAUTHORIZED);
        }

        com.example.dbc.model.Pauta associado1 = associadorep.saveAndFlush(associado);

        return new ResponseEntity<com.example.dbc.model.Pauta>(associado1, HttpStatus.CREATED);
    }


    @ResponseBody
    @DeleteMapping(value = "/deletaPautas/{id}")
    public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) {

        associadorep.deleteById(id);

        return new ResponseEntity("Associado Removida com sucesso!",HttpStatus.OK);
    }

   @PostMapping(value = "buscarPorPautas")
    @ResponseBody
    public ResponseEntity<List<com.example.dbc.model.Pauta>> buscarPorNomeParceiro(@RequestParam(name = "nomepauta") String name){

        List<com.example.dbc.model.Pauta> associados = associadorep.buscPauta(name.trim().toUpperCase());

        return new ResponseEntity<List<com.example.dbc.model.Pauta>>(associados, HttpStatus.OK);

    }
    @GetMapping("/listaVotos")

    public String getObj() throws JsonProcessingException{
         List<Object> votosDTOList = associadorep.Search() ;

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(votosDTOList);


        return json;
    }

    @ResponseBody
    @GetMapping(value = "/consultaCpf/{cpf}")
    public ResponseEntity<CpfDTO> consultaCep(@Valid  @PathVariable("cpf") String cpf){

   return new ResponseEntity<CpfDTO>((CpfDTO) cpfService.consultaCpf(cpf), HttpStatus.OK);

    }}