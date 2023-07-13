package br.com.paymentmanager.projection;

import java.math.BigDecimal;

public interface ResumoTotalCobrancas {

    int getCobrancasEnviadas();
    int getTotalCobrancas();
    int getPagamentosRecebidos();
    BigDecimal getValorRecebido();

}
