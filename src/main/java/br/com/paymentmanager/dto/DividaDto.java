package br.com.paymentmanager.dto;

import br.com.paymentmanager.model.Divida;
import br.com.paymentmanager.model.StatusDivida;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DividaDto {

    private Long id;

    private BigDecimal valor;

    private LocalDate dataDeAbertura;

    private LocalDate dataDeQuitacao;

    private StatusDivida status;

    private String descricaoDeQuitacao;

    private Long idCliente;

    public DividaDto() {
    }

    public DividaDto(Divida divida){
        this.id = divida.getId();
        this.valor = divida.getValor();
        this.dataDeAbertura = divida.getDataDeAbertura();
        this.dataDeQuitacao = divida.getDataDeQuitacao();
        this.status = divida.getStatus();
        this.descricaoDeQuitacao = divida.getDescricaoDeQuitacao();
        this.idCliente = divida.getCliente().getId();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataDeAbertura() {
        return dataDeAbertura;
    }

    public LocalDate getDataDeQuitacao() {
        return dataDeQuitacao;
    }

    public StatusDivida getStatus() {
        return status;
    }

    public String getDescricaoDeQuitacao() {
        return descricaoDeQuitacao;
    }

    public Long getIdCliente() {
        return idCliente;
    }

}
