package br.com.paymentmanager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHoraProcessamento;
    private LocalDateTime dataHoraVencimento;
    private BigDecimal valor;
    private SituacaoPagamento situacaoPagamento;
    private FormaDePagamento forma;

}
