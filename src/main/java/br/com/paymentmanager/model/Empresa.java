package br.com.paymentmanager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "empresas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String token;
    private String nome;
    private String cnpj;
    @Embedded
    private Endereco endereco;

}
