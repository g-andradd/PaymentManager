package br.com.paymentmanager.dto;

import br.com.paymentmanager.model.Cobranca;
import br.com.paymentmanager.model.MeioDeContato;

import java.time.LocalDate;

public class CobrancaDto {

    private Long id;

    private LocalDate dataDeRealizacao;

    private MeioDeContato meioDeContato;

    private String agente;

    private String comentarioDoAgente;

    private String acordo;

    private LocalDate dataDePromessaDePagamento;

    private Integer numeroDeParcelas;

    private Long idDivida;

    public CobrancaDto() {
    }

    public CobrancaDto(Cobranca cobranca) {
        this.id = cobranca.getId();
        this.dataDeRealizacao = cobranca.getDataDeRealizacao();
        this.meioDeContato = cobranca.getMeioDeContato();
        this.agente = cobranca.getAgente();
        this.comentarioDoAgente = cobranca.getComentarioDoAgente();
        this.acordo = cobranca.getAcordo();
        this.dataDePromessaDePagamento = cobranca.getDataDePromessaDePagamento();
        this.numeroDeParcelas = cobranca.getNumeroDeParcelas();
        this.idDivida = cobranca.getDivida().getId();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataDeRealizacao() {
        return dataDeRealizacao;
    }

    public MeioDeContato getMeioDeContato() {
        return meioDeContato;
    }

    public String getAgente() {
        return agente;
    }

    public String getComentarioDoAgente() {
        return comentarioDoAgente;
    }

    public String getAcordo() {
        return acordo;
    }

    public LocalDate getDataDePromessaDePagamento() {
        return dataDePromessaDePagamento;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public Long getIdDivida() {
        return idDivida;
    }

}
