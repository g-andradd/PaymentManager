package br.com.paymentmanager.mapper;

import br.com.paymentmanager.form.DividaForm;
import br.com.paymentmanager.model.Cliente;
import br.com.paymentmanager.model.Divida;
import br.com.paymentmanager.model.StatusDivida;

public class DividaMapper {

    public Divida cadastrar(DividaForm form, Cliente cliente) {

        Divida divida = new Divida(form.getValor(), form.getDataDeAbertura(), form.getStatus(), cliente);
        if (form.getDataDeQuitacao() != null) {
            divida.setDataDeQuitacao(form.getDataDeQuitacao());
        }
        if (form.getDescricaoDeQuitacao() != null) {
            divida.setDescricaoDeQuitacao(form.getDescricaoDeQuitacao());
        }
        if (form.getStatus().equals("QUITADA")) {
            divida.setStatus(StatusDivida.QUITADA);
        }
        return divida;
    }

}
