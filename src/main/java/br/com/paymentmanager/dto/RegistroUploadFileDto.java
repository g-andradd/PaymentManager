package br.com.paymentmanager.dto;

import br.com.paymentmanager.model.Empresa;
import br.com.paymentmanager.model.RegistrosUploadFile;
import br.com.paymentmanager.model.SituacaoArquivo;
import br.com.paymentmanager.model.TipoArquivo;
import lombok.Getter;

import java.util.Date;

@Getter
public class RegistroUploadFileDto {

    private final long id;
    private final String nome;
    private final String local;
    private final TipoArquivo tipoArquivo;
    private final SituacaoArquivo situacaoArquivo;
    private final Date dataHoraIncusao;
    private final Date dataHoraAlteracao;
    private final Date dataHoraProcessamento;
    private final String usuarioWeb;
    private final String usuarioInclusao;
    private final String usuarioAlteracao;
    private final String[] informacoesFile;
    private final Empresa codigoEmpresa;

    public RegistroUploadFileDto(RegistrosUploadFile ruf) {
        this.id = ruf.getId();
        this.nome = ruf.getNome();
        this.local = ruf.getLocal();
        this.tipoArquivo = ruf.getTipoArquivo();
        this.situacaoArquivo = ruf.getSituacaoArquivo();
        this.dataHoraIncusao = ruf.getDataHoraIncusao();
        this.dataHoraAlteracao = ruf.getDataHoraAlteracao();
        this.dataHoraProcessamento = ruf.getDataHoraProcessamento();
        this.usuarioWeb = ruf.getUsuarioWeb();
        this.usuarioInclusao = ruf.getUsuarioInclusao();
        this.usuarioAlteracao = ruf.getUsuarioAlteracao();
        this.informacoesFile = ruf.getInformacoesFile();
        this.codigoEmpresa = ruf.getCodigoEmpresa();
    }
}
