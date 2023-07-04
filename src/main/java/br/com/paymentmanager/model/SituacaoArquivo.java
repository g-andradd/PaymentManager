package br.com.paymentmanager.model;

public enum SituacaoArquivo {

    AGENDADO("A"),
    CANCELADO("C"),
    EM_PROCESSAMENTO("EP"),
    PROCESSADO_COM_ERRO("PE"),
    PROCESSADO("P");

    private String valor;

    private SituacaoArquivo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
