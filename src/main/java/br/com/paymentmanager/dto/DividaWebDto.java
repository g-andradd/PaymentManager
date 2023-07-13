package br.com.paymentmanager.dto;

import br.com.paymentmanager.model.Divida;
import br.com.paymentmanager.model.StatusDivida;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DividaWebDto {

    private Long id;

    private BigDecimal valor;

    private LocalDate dataDeAbertura;

    private StatusDivida status;

    private Long idCliente;

    public DividaWebDto(Divida divida){
        this.id = divida.getId();
        this.valor = divida.getValor();
        this.dataDeAbertura = divida.getDataDeAbertura();
        this.status = divida.getStatus();
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

    public StatusDivida getStatus() {
        return status;
    }

    public Long getIdCliente() {
        return idCliente;
    }

}
