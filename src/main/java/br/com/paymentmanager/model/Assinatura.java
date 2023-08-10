package br.com.paymentmanager.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "assinaturas")
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataTermino;

    @Column(nullable = false)
    private PeriodoDeCobranca periodoDeCobranca;

    @Column(nullable = false)
    @OneToMany
    private List<Produto> produtos;

    @OneToOne(mappedBy = "assinatura")
    private Pagamento pagamento;

    public Assinatura() {
    }

    public Assinatura(LocalDate dataInicio, LocalDate dataTermino, PeriodoDeCobranca periodoDeCobranca, List<Produto> produtos, Pagamento pagamento) {
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.periodoDeCobranca = periodoDeCobranca;
        this.produtos = produtos;
        this.pagamento = pagamento;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public PeriodoDeCobranca getPeriodoDeCobranca() {
        return periodoDeCobranca;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public void setPeriodoDeCobranca(PeriodoDeCobranca periodoDeCobranca) {
        this.periodoDeCobranca = periodoDeCobranca;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}
