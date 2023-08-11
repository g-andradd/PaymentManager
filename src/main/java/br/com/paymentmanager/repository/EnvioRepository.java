package br.com.paymentmanager.repository;

import br.com.paymentmanager.projection.CodigosDosEnvios;
import br.com.paymentmanager.projection.ResumoTotalCobrancas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnvioRepository extends JpaRepository<UploadFile, Long> {

    @Query(value = "select * from sorpag_upload_file " +
            "where codigo_empresa = ?1 "+
            "and tipo_arquivo = 'T' "+
            "and to_char(data_hora_inclusao, 'yyyy-mm') = ?2 ",
            nativeQuery = true
    )
    Optional<List<UploadFile>> findAllByData(Long codigoEmpresa, String data);

    @Query(value = "select sum(count(boleto.id_upload_file)) as cobrancasEnviadas, sum(cobrancas.soma) as totalCobrancas \n" +
            "from sorpag_boleto_crcce boleto \n" +
            "left join \n" +
            "    (select upload.id as upload_id, \n" +
            "        to_number(substr(informacoes_file, 1, instr(informacoes_file, '|')-1)) as soma \n" +
            "    from sorpag_upload_file upload \n" +
            "    where  \n" +
            "        tipo_arquivo = 'T' \n" +
            "        and upload.codigo_empresa = ?1 \n" +
            "        and to_char(data_hora_inclusao, 'yyyy-mm') = ?2 \n" +
            "    ) cobrancas \n" +
            "on cobrancas.upload_id = boleto.id_upload_file \n" +
            "where to_char(boleto.data_hora_create, 'yyyy-mm') = ?2 \n" +
            "group by cobrancas.soma",
            nativeQuery = true)
    Optional<ResumoTotalCobrancas> findResumoTotal(long idEmpresa, String data);

    @Query(value = "select id as codigo, nome_arquivo as nomeArquivo "+
            "from sorpag_upload_file "+
            "where codigo_empresa = ?1 "+
            "and tipo_arquivo = 'T' "+
            "and situacao_arquivo in ('P','PE') "+
            "and to_char(data_hora_inclusao, 'yyyy-mm') = ?2",
            nativeQuery = true)
    Optional<List<CodigosDosEnvios>> findAllCodigosByData(long idEmpresa, String data);

}
