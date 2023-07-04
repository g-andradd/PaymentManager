package br.com.paymentmanager.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Boleto {

    private Calendar dataProcessamento;
    private Calendar dataVencimento;
    private String logradouroBeneficiario;
    private String bairroBeneficiario;
    private String cepBeneficiario;
    private String cidadeBeneficiario;
    private String ufBeneficiario;
    private String nomeBeneficiario;
    private String documentoBeneficiario;
    private String agenciaBeneficiario;
    private String digitoAgenciaBeneficiario;
    private String codigoBeneficiario;
    private String digitoCodigoBeneficiario;
    private String numeroConvenioBeneficiario;
    private String carteiraBeneficiario;
    private String nossoNumeroBeneficiario;
    private String digitoNossoNumeroBeneficiario;
    private String logradouroPagador;
    private String bairroPagador;
    private String cepPagador;
    private String cidadePagador;
    private String ufPagador;
    private String nomePagador;
    private String documentoPagador;
    private BigDecimal valorBoleto;
    private String numeroDocumento;
    private List<String> instrucoes;
    private List<String> locaisDePagamento;

}
