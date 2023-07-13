package br.com.paymentmanager.mapper;

import br.com.paymentmanager.form.AtualizacaoClienteForm;
import br.com.paymentmanager.form.ClienteForm;
import br.com.paymentmanager.model.Cliente;
import br.com.paymentmanager.model.DadosPessoais;
import br.com.paymentmanager.model.Endereco;
import br.com.paymentmanager.model.StatusCliente;

public class ClienteMapper {

    public Cliente cadastrar(ClienteForm form) {
        DadosPessoais dadosPessoais;
        Endereco endereco;
        StatusCliente statusCliente = StatusCliente.ATIVO;
        dadosPessoais = new DadosPessoais(form.getCpf(), form.getNome(), form.getProfissao(), form.getTelefone(), form.getEmail());
        endereco = new Endereco(form.getRua(), form.getNumero(), form.getBairro(), form.getCidade(), form.getEstado());
        if (form.getComplemento() != null) {
            endereco.setComplemento(form.getComplemento());
        }
        if (form.getStatus().equals("SUSPENSO")) {
            statusCliente = StatusCliente.SUSPENSO;
        }
        return new Cliente(form.getRenda(), dadosPessoais, endereco, statusCliente);
    }

    public Cliente atualizar(Cliente cliente, AtualizacaoClienteForm form) {
        criarCliente(cliente, form);
        return cliente;
    }

    private void criarCliente(Cliente cliente, AtualizacaoClienteForm form) {
        cliente.setNome(form.getNome());
        cliente.setTelefone(form.getTelefone());
        cliente.setEmail(form.getEmail());
        cliente.setRua(form.getRua());
        cliente.setNumero(form.getNumero());
        cliente.setBairro(form.getBairro());
        cliente.setCidade(form.getCidade());
        cliente.setEstado(form.getEstado());
        cliente.setProfissao(form.getProfissao());
        cliente.setRenda(form.getRenda());

        if (form.getComplemento() != null) {
            cliente.setComplemento(form.getComplemento());
        }
        if (form.getStatus().equals("ATIVO")) {
            cliente.setStatus(StatusCliente.ATIVO);
        } else {
            cliente.setStatus(StatusCliente.SUSPENSO);
        }
    }
}
