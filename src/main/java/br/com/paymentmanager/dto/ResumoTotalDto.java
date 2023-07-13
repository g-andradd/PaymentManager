package br.com.paymentmanager.dto;

import br.com.paymentmanager.projection.ResumoTotalCobrancas;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ResumoTotalDto {

    private final int cobrancasEnviadas;
    private final int totalCobrancas;
    private final int pagamentosrecebidos;
    private final BigDecimal valorRecebido;

    public ResumoTotalDto(ResumoTotalCobrancas rt) {
        this.cobrancasEnviadas = rt.getCobrancasEnviadas();
        this.totalCobrancas = rt.getTotalCobrancas();
        this.pagamentosrecebidos = rt.getPagamentosRecebidos();
        this.valorRecebido = rt.getValorRecebido();
    }

    public static ResumoTotalDto converter(ResumoTotalCobrancas rt) {
        return new ResumoTotalDto(rt);
    }
}
