package br.com.paymentmanager.projection;

import java.math.BigDecimal;

public interface RelatorioClientesProjecao {

        String getNome();
        BigDecimal getDividaValor();
        Integer getCobrancaContagem();

}
