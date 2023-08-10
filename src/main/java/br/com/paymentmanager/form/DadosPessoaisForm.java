package br.com.paymentmanager.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class DadosPessoaisForm {

    @NotBlank
//    @CPF
    private String cpf;

    @NotBlank
    private String nome;

    @NotBlank
    private String celular;

    @NotBlank
    @Email
    private String email;

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
