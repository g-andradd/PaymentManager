package br.com.paymentmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "boletos")
@PrimaryKeyJoinColumn(name = "pagamento_id")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Boleto extends Pagamento{

    private String agenciaBeneficiario;
    private String digitoAgenciaBeneficiario;
    private String codigoBeneficiario;
    private String digitoCodigoBeneficiario;
    private String numeroConvenioBeneficiario;
    private String carteiraBeneficiario;
    private String nossoNumeroBeneficiario;
    private String digitoNossoNumeroBeneficiario;
    private String numeroDocumento;
    private List<String> instrucoes;
    private List<String> locaisDePagamento;

}
