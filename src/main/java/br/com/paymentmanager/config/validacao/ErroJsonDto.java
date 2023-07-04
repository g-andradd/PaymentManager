package br.com.paymentmanager.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErroJsonDto {

    private long codigo;
    private String campo;
    private String erro;

}
