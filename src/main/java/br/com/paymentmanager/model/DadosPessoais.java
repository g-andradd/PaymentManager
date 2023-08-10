package br.com.paymentmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DadosPessoais {

    @Column(nullable=false, unique=true, length=15)
    private String cpf;

    @Column(nullable=false, length=100)
    private String nome;

    @Column(nullable=false, length=18)
    private String celular;

    @Column(nullable=false, length=100)
    private String email;


    public DadosPessoais() {
    }

    public DadosPessoais(String cpf, String nome, String celular, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.celular = celular;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String telefone) {
        this.celular = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
