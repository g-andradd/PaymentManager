package br.com.paymentmanager.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.StringUtils;

import java.util.Date;

@Entity
@Table(name = "SORPAG_UPLOAD_FILE")

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String local;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private TipoArquivo tipoArquivo;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private SituacaoArquivo situacaoArquivo;
    @Getter
    @Setter
    private Date dataHoraIncusao;
    @Getter
    @Setter
    private Date dataHoraAlteracao;
    @Getter
    @Setter
    private Date dataHoraProcessamento;
    @Getter
    @Setter
    private String usuarioWeb;
    @Getter
    @Setter
    private String usuarioInclusao;
    @Getter
    @Setter
    private String usuarioAlteracao;
    private String informacoesFile;
    @Getter
    @Setter
    @OneToOne
    private Empresa codigoEmpresa;

    public String[] getInformacoesFile() {
        return StringUtils.split(informacoesFile,("|"));
    }

    public UploadFile informacoesFile(String[] informacoesFile) {
        this.informacoesFile = StringUtils.arrayToDelimitedString(informacoesFile, "|");
        return this;
    }
}
