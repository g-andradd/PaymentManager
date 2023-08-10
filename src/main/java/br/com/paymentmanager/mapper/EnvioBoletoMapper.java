package br.com.paymentmanager.mapper;

import br.com.paymentmanager.model.Envio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EnvioBoletoMapper {

    public Envio transformar(UploadFile uf) {
        try {
            return criarEnvioBoleto(uf);
        } catch (Exception e) {
            throw new RuntimeException("Erro no mapper: transformar()");
        }

    }

    public List<Envio> transformarLista(List<UploadFile> ufList) {
        List<Envio> envioBoletos = new ArrayList<>();
        for(UploadFile uf : ufList) {
            envioBoletos.add(criarEnvioBoleto(uf));
        }
        return envioBoletos;
    }

    private static Envio criarEnvioBoleto(UploadFile uf) {
        return Envio.builder()
                .codigo(uf.getId())
                .dataHoraCriacao(uf.getDataHoraIncusao())
                .status(uf.getSituacaoArquivo())
                .quantidadeRegistros(Integer.parseInt(uf.getInformacoesFile()[0]))
                .valorTotal(new BigDecimal(uf.getInformacoesFile()[1]))
                .dataHoraProcessamento(uf.getDataHoraProcessamento())
                .nomeArquivo(uf.getNome())
                .build();
    }

}
