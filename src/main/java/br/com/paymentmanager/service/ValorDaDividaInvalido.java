package br.com.paymentmanager.service;

import br.com.paymentmanager.model.Cliente;
import br.com.paymentmanager.repository.DividaRepository;

import java.math.BigDecimal;

public class ValorDaDividaInvalido {
    public static boolean validar(BigDecimal valor, Cliente cliente, DividaRepository dividaRepository) {
        BigDecimal valorTotal = dividaRepository.buscaSomaDoValorDaDivida(cliente.getId());
        if(valorTotal == null){
            valorTotal = BigDecimal.ZERO;
        }
        return valorTotal.add(valor).doubleValue() > new BigDecimal(12).multiply(cliente.getRenda()).doubleValue();
    }
}
