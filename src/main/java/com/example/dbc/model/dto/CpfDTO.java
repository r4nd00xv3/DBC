package com.example.dbc.model.dto;

import org.hibernate.validator.constraints.br.CPF;

public class CpfDTO {


   public String nome;
    public String erro;
    public String pacoteUsado;
    public String erroCodigo;
    private String status;

   @CPF(message = "ABLE_TO_VOTE")
   private String cpf;
    private String nascimento;
    private String mae;
    private String genero;
    private String situacao;
    private String aldo;
    private String consultaID;
    private String delay;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getPacoteUsado() {
        return pacoteUsado;
    }

    public void setPacoteUsado(String pacoteUsado) {
        this.pacoteUsado = pacoteUsado;
    }

    public String getAldo() {
        return aldo;
    }

    public void setAldo(String aldo) {
        this.aldo = aldo;
    }

    public String getConsultaID() {
        return consultaID;
    }

    public void setConsultaID(String consultaID) {
        this.consultaID = consultaID;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getErroCodigo() {
        return erroCodigo;
    }

    public void setErroCodigo(String erroCodigo) {
        this.erroCodigo = erroCodigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
