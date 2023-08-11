package br.com.paymentmanager.dto;

import br.com.paymentmanager.model.Cliente;
import br.com.paymentmanager.model.StatusCliente;

public class ClienteWebDto {

    private final Long id;
    private final String nome;
    private final String cpf;
    private final String telefone;
    private final String local;
    private final StatusCliente status;

    public ClienteWebDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getDadosPessoais().getNome();
        this.cpf = cliente.getDadosPessoais().getCpf();
        this.telefone = cliente.getDadosPessoais().getCelular();
        this.local = cliente.getEndereco().getCidade() + "/" + cliente.getEndereco().getEstado();
        this.status = cliente.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getLocal() {
        return local;
    }

    public StatusCliente getStatus() {
        return status;
    }

}
