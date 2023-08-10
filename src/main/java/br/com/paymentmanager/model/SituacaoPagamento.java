package br.com.paymentmanager.model;

public enum SituacaoPagamento {

    AGUARDANDO_PAGAMENTO("Aguardando pagamento"),
    EM_ANALISE("Em analise"),
    PAGO("Pago"),
    CONFIRMADO("Confirmado"),
    RECUSADO("Recusado"),
    CANCELADO("Cancelado"),
    REEMBOLSADO("Reembolsado"),
    EXPIRADO("Expirado"),
    ERRO_DE_PROCESSAMENTO("Erro de processamento"),
    AGUARDANDO_CONFIRMACAO("Aguardando confirmacao"),
    AGUARDANDO_COMPENSACAO("Aguardando compensacao"),
    AGUARDANDO_AUTORIZACAO("Aguardando autorizacao");

    private final String valor;

    private SituacaoPagamento(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
