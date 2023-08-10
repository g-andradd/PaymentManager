package br.com.paymentmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length=10)
  private Long id;

  @Embedded
  private DadosPessoais dadosPessoais;

  @Embedded
  private Endereco endereco;

  @Enumerated(EnumType.STRING)
  @JoinColumn(nullable = false)
  private StatusCliente status = StatusCliente.ATIVO;

  @ManyToOne
  private Assinatura assinatura;

  public Cliente() {
  }

  public Cliente(DadosPessoais dadosPessoais, Endereco endereco, StatusCliente status, Assinatura assinatura) {
    this.dadosPessoais = dadosPessoais;
    this.endereco = endereco;
    this.status = status;
    this.assinatura = assinatura;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public DadosPessoais getDadosPessoais() {
    return dadosPessoais;
  }

  public void setDadosPessoais(DadosPessoais dadosPessoais) {
    this.dadosPessoais = dadosPessoais;
  }

  public Endereco getEndereco() {
    return endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  public StatusCliente getStatus() {
    return status;
  }

  public void setStatus(StatusCliente status) {
    this.status = status;
  }

  public Assinatura getAssinatura() {
    return assinatura;
  }

  public void setAssinatura(Assinatura assinatura) {
    this.assinatura = assinatura;
  }
}
