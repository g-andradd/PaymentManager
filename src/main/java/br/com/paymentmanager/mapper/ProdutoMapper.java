package br.com.paymentmanager.mapper;

import br.com.paymentmanager.form.ProdutoForm;
import br.com.paymentmanager.model.Produto;

public class ProdutoMapper {

    public Produto criar(ProdutoForm form) {
        return new Produto(form.getNome(), form.getDescricao(), form.getValor());
    }

    public Produto atualizar(Produto produto, ProdutoForm form) {
        produto.setNome(form.getNome());
        produto.setDescricao(form.getDescricao());
        produto.setValor(form.getValor());

        return produto;
    }
}
