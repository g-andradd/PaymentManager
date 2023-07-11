package br.com.paymentmanager.dto;

import br.com.paymentmanager.projection.CodigosDosEnvios;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodigosDosEnviosDto {

    private final long codigo;
    private final String nomeArquivo;

    public CodigosDosEnviosDto(CodigosDosEnvios codigos) {
        this.codigo = codigos.getCodigo();
        this.nomeArquivo = codigos.getNomeArquivo();
    }

}
