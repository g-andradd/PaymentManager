package br.com.paymentmanager.dto;

import br.com.paymentmanager.model.Cliente;
import br.com.paymentmanager.model.StatusCliente;

public class ClienteDto {

    private final Long id;

    private final String nome;

    private final String cpf;

    private final String celular;

    private final String email;

    private final String rua;

    private final int numero;

    private final String complemento;

    private final String bairro;

    private final String cidade;

    private final String estado;

    private final StatusCliente status;

    public ClienteDto(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getDadosPessoais().getNome();
        this.cpf = cliente.getDadosPessoais().getCpf();
        this.celular = cliente.getDadosPessoais().getCelular();
        this.email = cliente.getDadosPessoais().getEmail();
        this.rua = cliente.getEndereco().getCep();
        this.numero = cliente.getEndereco().getNumero();
        this.complemento = cliente.getEndereco().getComplemento();
        this.bairro = cliente.getEndereco().getBairro();
        this.cidade = cliente.getEndereco().getCidade();
        this.estado = cliente.getEndereco().getEstado();
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

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getRua() {
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public StatusCliente getStatus() {
        return status;
    }
}
