package br.com.paymentmanager.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.StringUtils;

import java.util.Date;

@Entity
@Table(name = "registros_dos_arquivos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrosUploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nome;
    private String local;
    @Enumerated(EnumType.STRING)
    private TipoArquivo tipoArquivo;
    @Enumerated(EnumType.STRING)
    private SituacaoArquivo situacaoArquivo;
    private Date dataHoraIncusao;
    private Date dataHoraAlteracao;
    private Date dataHoraProcessamento;
    private String usuarioWeb;
    private String usuarioInclusao;
    private String usuarioAlteracao;
    private String informacoesFile;
    @OneToOne
    private Empresa codigoEmpresa;

    public String[] getInformacoesFile() {
        return StringUtils.split(informacoesFile,("|"));
    }

    public RegistrosUploadFile informacoesFile(String[] informacoesFile) {
        this.informacoesFile = StringUtils.arrayToDelimitedString(informacoesFile, "|");
        return this;
    }

}
