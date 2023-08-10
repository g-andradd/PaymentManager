package br.com.paymentmanager.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ClienteForm {

    @NotBlank(message = "O campo nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "O campo CPF não pode estar em branco")
//    @CPF
    private String cpf;

    @NotBlank(message = "O campo telefone não pode estar em branco")
    private String celular;

    @NotBlank(message = "O campo e-mail não pode estar em branco")
    @Email
    private String email;

    @NotBlank(message = "O campo rua não pode estar em branco")
    private String cep;

    @NotNull(message = "O campo número não pode estar nulo")
    @Positive(message = "O campo número deve ser positivo")
    private int numero;

    private String complemento;

    @NotBlank(message = "O campo bairro não pode estar em branco")
    private String bairro;

    @NotBlank(message = "O campo cidade não pode estar em branco")
    private String cidade;

    @NotBlank(message = "O campo estado não pode estar em branco")
    private String estado;

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

    public String getCep() {
        return cep;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
