package br.com.paymentmanager.form;

import br.com.paymentmanager.model.MeioDeContato;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public class AtualizaCobrancaForm {

    @NotNull
    private Long id;

    @NotNull
    @PastOrPresent
    private LocalDate dataDeRealizacao;

    @NotNull
    private MeioDeContato meioDeContato;

    @NotBlank
    private String agente;

    @NotNull
    private TipoAgente tipoDeAgente;

    @NotNull @Length(max = 500)
    private String comentarioDoAgente;

    @Length(max = 500)
    private String acordo;

    private TipoAcordo tipoDeAcordo;

    @Future
    private LocalDate dataDePromessaDePagamento;

    @Min(1)@Max(12)
    private Integer numeroDeParcelas;

    @NotNull
    private Long idDivida;

    public Long getId() {
        return id;
    }

    public LocalDate getDataDeRealizacao() {
        return dataDeRealizacao;
    }

    public MeioDeContato getMeioDeContato() {
        return meioDeContato;
    }

    public String getAgente() {
        return agente;
    }

    public TipoAgente getTipoDeAgente() {
        return tipoDeAgente;
    }

    public String getComentarioDoAgente() {
        return comentarioDoAgente;
    }

    public String getAcordo() {
        return acordo;
    }

    public TipoAcordo getTipoDeAcordo() {
        return tipoDeAcordo;
    }

    public LocalDate getDataDePromessaDePagamento() {
        return dataDePromessaDePagamento;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public Long getIdDivida() {
        return idDivida;
    }

    public void setDataDeRealizacao(LocalDate dataDeRealizacao) {
        this.dataDeRealizacao = dataDeRealizacao;
    }

    public void setMeioDeContato(MeioDeContato meioDeContato) {
        this.meioDeContato = meioDeContato;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public void setTipoDeAgente(TipoAgente tipoDeAgente) {
        this.tipoDeAgente = tipoDeAgente;
    }

    public void setComentarioDoAgente(String comentarioDoAgente) {
        this.comentarioDoAgente = comentarioDoAgente;
    }

    public void setAcordo(String acordo) {
        this.acordo = acordo;
    }

    public void setTipoDeAcordo(TipoAcordo tipoDeAcordo) {
        this.tipoDeAcordo = tipoDeAcordo;
    }

    public void setDataDePromessaDePagamento(LocalDate dataDePromessaDePagamento) {
        this.dataDePromessaDePagamento = dataDePromessaDePagamento;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public void setIdDivida(Long idDivida) {
        this.idDivida = idDivida;
    }
}
