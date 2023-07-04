package br.com.paymentmanager.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaBoleto {

    private Empresa empresa;
    private Boleto boleto;

}
