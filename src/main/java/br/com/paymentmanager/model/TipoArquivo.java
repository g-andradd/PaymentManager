package br.com.paymentmanager.model;

public enum TipoArquivo {

    CSV("C"),
    XLSX("XX"),
    TXT("T");

    private String valor;

    private TipoArquivo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
