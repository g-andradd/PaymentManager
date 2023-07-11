package br.com.paymentmanager.dto;

import br.com.paymentmanager.model.Divida;
import br.com.paymentmanager.model.StatusDivida;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DividaComCpfDto {

    private Long id;

    private BigDecimal valor;

    private LocalDate dataDeAbertura;

    private LocalDate dataDeQuitacao;

    private StatusDivida status;

    private String descricaoDeQuitacao;

    private String cpf;

    public DividaComCpfDto(Divida divida) {
        this.id = divida.getId();
        this.valor = divida.getValor();
        this.dataDeAbertura = divida.getDataDeAbertura();
        this.dataDeQuitacao = divida.getDataDeQuitacao();
        this.status = divida.getStatus();
        this.descricaoDeQuitacao = divida.getDescricaoDeQuitacao();
        this.cpf = divida.getCliente().getCpf();
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

    public String getCpf() {
        return cpf;
    }

}
