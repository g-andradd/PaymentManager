package br.com.paymentmanager.repository;

import br.com.paymentmanager.model.Cobranca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CobrancaRepository extends JpaRepository<Cobranca, Long> {

    @Query("SELECT COUNT(c) FROM Cobranca c " +
            "WHERE c.divida.id = :id ")
    int somaDeCobrancasDaDivida(@Param("id") Long idDivida);

    List<Cobranca> findByDividaId(Long dividaId);

}
