package br.com.paymentmanager.mapper;


import br.com.paymentmanager.form.AtualizaCobrancaForm;
import br.com.paymentmanager.form.CobrancaForm;
import br.com.paymentmanager.model.*;
import br.com.paymentmanager.repository.CobrancaRepository;

public class CobrancaMapper {

    public Cobranca cadastrar(CobrancaForm form, CobrancaRepository cobrancaRepository, Divida divida) {
        Cobranca cobranca = new Cobranca(
                form.getDataDeRealizacao(),
                form.getMeioDeContato(),
                form.getAgente(),
                form.getTipoDeAgente(),
                form.getComentarioDoAgente(),
                divida
        );

        cobranca.setAcordo(form.getAcordo());
        cobranca.setDataDePromessaDePagamento(form.getDataDePromessaDePagamento());
        cobranca.setNumeroDeParcelas(form.getNumeroDeParcelas());

        TipoAcordo tipoDeAcordo = form.getTipoDeAcordo();
        if (tipoDeAcordo != null) {
            cobranca.setTipoDeAcordo(tipoDeAcordo);
        }

        if (cobrancaRepository.somaDeCobrancasDaDivida(form.getIdDivida()) == 3) {
            divida.setStatus(StatusDivida.RECUPERACAO_JUDICIAL);
        }

        return cobranca;
    }


    public Cobranca atualizar(Cobranca cobranca, AtualizaCobrancaForm form) {
        cobranca.setDataDeRealizacao(form.getDataDeRealizacao());
        cobranca.setMeioDeContato(form.getMeioDeContato());
        cobranca.setAgente(form.getAgente());
        cobranca.setTipoDeAgente(form.getTipoDeAgente());
        cobranca.setComentarioDoAgente(form.getComentarioDoAgente());
        cobranca.setAcordo(form.getAcordo());
        cobranca.setTipoDeAcordo(form.getTipoDeAcordo());
        cobranca.setDataDePromessaDePagamento(form.getDataDePromessaDePagamento());
        cobranca.setNumeroDeParcelas(form.getNumeroDeParcelas());

        return cobranca;
    }
}
