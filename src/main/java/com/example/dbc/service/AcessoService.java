package com.example.dbc.service;

import com.example.dbc.model.Acesso;
import com.example.dbc.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {

        @Autowired
        private AcessoRepository acessoRepository;


        public Acesso save(Acesso acesso) {

            /*Qualquer tipo de validação*/

            return acessoRepository.save(acesso);
        }
}
