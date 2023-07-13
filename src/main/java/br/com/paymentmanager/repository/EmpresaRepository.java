package br.com.paymentmanager.repository;


import br.com.paymentmanager.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
