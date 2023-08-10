package br.com.paymentmanager.mapper;

import br.com.paymentmanager.form.ClienteForm;
import br.com.paymentmanager.form.DadosPessoaisForm;
import br.com.paymentmanager.form.EnderecoForm;
import br.com.paymentmanager.model.Cliente;
import br.com.paymentmanager.model.DadosPessoais;
import br.com.paymentmanager.model.Endereco;

public class ClienteMapper {

    public Cliente cadastrar(ClienteForm form) {
        DadosPessoais dadosPessoais = new DadosPessoais(form.getCpf(), form.getNome(), form.getCelular(), form.getEmail());
        Endereco endereco = new Endereco(form.getCep(), form.getNumero(), form.getBairro(), form.getCidade(), form.getEstado());
        if (form.getComplemento() != null) {
            endereco.setComplemento(form.getComplemento());
        }
        return new Cliente(dadosPessoais, endereco);
    }

    public Cliente atualizarDadosPessoais(Cliente cliente, DadosPessoaisForm form) {
        DadosPessoais dadosPessoais = new DadosPessoais(form.getCpf(), form.getNome(), form.getCelular(), form.getEmail());
        cliente.setDadosPessoais(dadosPessoais);

        return cliente;
    }

    public Cliente atualizarEndereco(Cliente cliente, EnderecoForm form) {
        Endereco endereco = new Endereco(form.getCep(), form.getNumero(), form.getBairro(), form.getCidade(), form.getEstado());
        cliente.setEndereco(endereco);
        if (form.getComplemento() != null) {
            endereco.setComplemento(form.getComplemento());
        }

        return cliente;
    }
}
