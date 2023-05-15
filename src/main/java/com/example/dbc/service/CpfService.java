package com.example.dbc.service;

import com.example.dbc.model.dto.CpfDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CpfService {

    public Object consultaCpf(String cpf) {
        return new RestTemplate().getForEntity("https://api.cpfcnpj.com.br/5ae973d7a997af13f0aaf2bf60e65803/9/" + cpf , CpfDTO.class).getBody();
    }
}