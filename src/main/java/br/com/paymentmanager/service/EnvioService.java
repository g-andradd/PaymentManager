package br.com.paymentmanager.service;

import br.com.paymentmanager.dto.CodigosDosEnviosDto;
import br.com.paymentmanager.dto.EnvioBoletoDto;
import br.com.paymentmanager.dto.ResumoTotalDto;
import br.com.paymentmanager.model.*;
import br.com.paymentmanager.projection.CodigosDosEnvios;
import br.com.paymentmanager.projection.ResumoTotalCobrancas;
import br.com.paymentmanager.repository.EmpresaRepository;
import br.com.paymentmanager.repository.EnvioRepository;
import br.com.paymentmanager.util.DataUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvioService {

    private final EnvioRepository envioBoletoRepository;
    private final EmpresaRepository empresaRepository;

    private final RegistrosUploadFileService registrosUploadFileService;

    public EnvioService(EnvioRepository envioBoletoRepository, EmpresaRepository empresaRepository, RegistrosUploadFileService registrosUploadFileService) {
        this.envioBoletoRepository = envioBoletoRepository;
        this.empresaRepository = empresaRepository;
        this.registrosUploadFileService = registrosUploadFileService;
    }

    public List<EnvioBoletoDto> listarTodos(Long idEmpresa, String data) {
        List<UploadFile> ufList = this.envioBoletoRepository
                .findAllByData(validarEmpresa(idEmpresa).getId(), DataUtil.inserirAnoMes(data))
                .orElseThrow(() -> new RuntimeException("Nenhuma cobraça encontrada"));

        List<Envio> envioBoletos = new EnvioBoletoMapper().transformarLista(ufList);

        return EnvioBoletoDto.converter(envioBoletos);
    }

    public ResumoTotalDto buscarResumoTotal(Long idEmpresa, String data) {
        ResumoTotalCobrancas resumoTotal = this.envioBoletoRepository
                .findResumoTotal(validarEmpresa(idEmpresa).getId(), DataUtil.inserirAnoMes(data))
                .orElseThrow(() -> new RuntimeException("Não foi possível buscar o resumo total"));

        return ResumoTotalDto.converter(resumoTotal);

    }

    public List<CodigosDosEnviosDto> buscarCodigosDosEnvios(Long idEmpresa, String data) {
        List<CodigosDosEnvios> codigosDosEnvios = this.envioBoletoRepository.findAllCodigosByData(idEmpresa, data)
                .orElseThrow(() -> new RuntimeException("Não foi possível buscar os codigos das cobrancas"));

        return codigosDosEnvios.stream().map(CodigosDosEnviosDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void cancelarEnvioBoleto(String nomeUsuario, Long id) {
        UploadFile uf = this.envioBoletoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UploadFile não encontrado"));
        uf.setSituacaoArquivo(SituacaoPagamento.CANCELADO);
        uf.setDataHoraAlteracao(Date.from(Instant.now()));
        uf.setUsuarioAlteracao(nomeUsuario);
        this.envioBoletoRepository.save(uf);
        RegistrosUploadFile ruf = new RegistrosUploadFileMapper().transformar(uf);
        this.registrosUploadFileService.inserir(ruf);
        this.envioBoletoRepository.delete(uf);
    }

    private Empresa validarEmpresa(Long id) {
        return this.empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada ao buscar o resumo total"));
    }

}
