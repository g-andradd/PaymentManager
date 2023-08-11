package br.com.paymentmanager.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ProdutoForm {

    @NotBlank(message = "O campo nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "O campo descrição não pode estar em branco")
    private String descricao;

    @NotNull(message = "O campo valor não pode estar nulo")
    @Positive(message = "O campo valor tem que ser positivo")
    private BigDecimal valor;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
