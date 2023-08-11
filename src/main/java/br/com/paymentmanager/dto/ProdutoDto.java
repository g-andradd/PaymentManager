package br.com.paymentmanager.dto;

import br.com.paymentmanager.model.Produto;

import java.math.BigDecimal;

public class ProdutoDto {

    private final Long id;
    private final String nome;
    private final String descricao;
    private final BigDecimal valor;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
