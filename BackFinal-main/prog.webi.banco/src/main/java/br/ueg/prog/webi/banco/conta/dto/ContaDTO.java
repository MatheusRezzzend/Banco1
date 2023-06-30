package br.ueg.prog.webi.banco.conta.dto;

import lombok.Data;

import java.math.BigDecimal;

public @Data class ContaDTO {

    private Integer id;
    private String nome;
    private String sobrenome;
    private Integer agencia;
    private Integer numeroConta;
    private String contato;
    private String statusConta;
    private String eMail;
    private BigDecimal saldoConta;
    private BigDecimal valorConta;
}
