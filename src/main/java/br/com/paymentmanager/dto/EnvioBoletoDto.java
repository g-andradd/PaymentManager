package br.com.paymentmanager.dto;

import br.com.paymentmanager.model.Envio;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EnvioBoletoDto {

    private final long codigo;
    private final Date dataHoraCriacao;
    private final String status;
    private final Date dataHoraProcessamento;
    private final String nomeArquivo;
    private final int quantidadeRegistros;
    private final BigDecimal valorTotal;

    public EnvioBoletoDto(Envio envioBoleto) {
        this.codigo = envioBoleto.getCodigo();
        this.dataHoraCriacao = envioBoleto.getDataHoraCriacao();
        this.status = envioBoleto.getStatus().getValor();
        this.dataHoraProcessamento = envioBoleto.getDataHoraProcessamento();
        this.nomeArquivo = envioBoleto.getNomeArquivo();
        this.quantidadeRegistros = envioBoleto.getQuantidadeRegistros();
        this.valorTotal = envioBoleto.getValorTotal();
    }

    public static List<EnvioBoletoDto> converter(List<Envio> envioBoletos) {
        return envioBoletos.stream().map(EnvioBoletoDto::new).collect(Collectors.toList());
    }
}
