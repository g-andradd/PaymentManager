package br.com.paymentmanager.config.validacao;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class ErroPadrao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Instant dataHora;
    private String erro;
    private String mensagem;
    private String caminho;

    public ErroPadrao(Instant dataHora, String erro, String mensagem, String caminho) {
        this.dataHora = dataHora;
        this.erro = erro;
        this.mensagem = mensagem;
        this.caminho = caminho;
    }

    public Instant getDataHora() {
        return dataHora;
    }

    public void setDataHora(Instant dataHora) {
        this.dataHora = dataHora;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

}
