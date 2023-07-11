package br.com.paymentmanager.dto;

import br.com.paymentmanager.projection.CodigosDosEnvios;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CodigosDosEnviosDto {

    private final long codigo;
    private final String nomeArquivo;

    public CodigosDosEnviosDto(CodigosDosEnvios codigos) {
        this.codigo = codigos.getCodigo();
        this.nomeArquivo = codigos.getNomeArquivo();
    }

    public static List<CodigosDosEnviosDto> converter(List<CodigosDosEnvios> codigosDasCobrancas) {
        return codigosDasCobrancas.stream().map(CodigosDosEnviosDto::new).collect(Collectors.toList());
    }
}
