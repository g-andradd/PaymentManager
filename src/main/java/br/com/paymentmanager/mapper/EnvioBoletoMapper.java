package br.com.paymentmanager.mapper;

import br.com.paymentmanager.model.EnvioBoleto;
import br.com.paymentmanager.model.UploadFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EnvioBoletoMapper {

    public EnvioBoleto transformar(UploadFile uf) {
        try {
            return criarEnvioBoleto(uf);
        } catch (Exception e) {
            throw new RuntimeException("Erro no mapper: transformar()");
        }

    }

    public List<EnvioBoleto> transformarLista(List<UploadFile> ufList) {
        List<EnvioBoleto> envioBoletos = new ArrayList<>();
        for(UploadFile uf : ufList) {
            envioBoletos.add(criarEnvioBoleto(uf));
        }
        return envioBoletos;
    }

    private static EnvioBoleto criarEnvioBoleto(UploadFile uf) {
        return EnvioBoleto.builder()
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
