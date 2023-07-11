package br.com.paymentmanager.service;

import br.com.paymentmanager.dto.CodigosDosEnviosDto;
import br.com.paymentmanager.dto.EnvioBoletoDto;
import br.com.paymentmanager.dto.ResumoTotalDto;
import br.com.paymentmanager.mapper.EnvioBoletoMapper;
import br.com.paymentmanager.mapper.RegistrosUploadFileMapper;
import br.com.paymentmanager.model.*;
import br.com.paymentmanager.projection.CodigosDosEnvios;
import br.com.paymentmanager.projection.ResumoTotalCobrancas;
import br.com.paymentmanager.repository.EmpresaRepository;
import br.com.paymentmanager.repository.EnvioBoletoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class EnvioBoletoService {

    private final EnvioBoletoRepository envioBoletoRepository;
    private final EmpresaRepository empresaRepository;

    public EnvioBoletoService(EnvioBoletoRepository envioBoletoRepository, EmpresaRepository empresaRepository) {
        this.envioBoletoRepository = envioBoletoRepository;
        this.empresaRepository = empresaRepository;
    }

    public List<EnvioBoletoDto> listarTodos(Long idEmpresa, String data) {
        List<UploadFile> ufList = envioBoletoRepository
                .findAllByData(validarEmpresa(idEmpresa).getId(), Utils.inserirAnoMes(data))
                .orElseThrow(() -> new RuntimeException("Nenhuma cobraça encontrada"));

        List<EnvioBoleto> envioBoletos = new EnvioBoletoMapper().transformarLista(ufList);

        return EnvioBoletoDto.converter(envioBoletos);
    }

    public ResumoTotalDto buscarResumoTotal(Long idEmpresa, String data) {
        ResumoTotalCobrancas resumoTotal = envioBoletoRepository
                .findResumoTotal(validarEmpresa(idEmpresa).getId(), Utils.inserirAnoMes(data))
                .orElseThrow(() -> new RuntimeException("Não foi possível buscar o resumo total"));

        return ResumoTotalDto.converter(resumoTotal);

    }

    public List<CodigosDosEnviosDto> buscarCodigosDosEnvios(Long idEmpresa, String data) {
        List<CodigosDosEnvios> codigosDosEnvios = envioBoletoRepository.findAllCodigosByData(idEmpresa, data)
                .orElseThrow(() -> new RuntimeException("Não foi possível buscar os codigos das cobrancas"));

        return CodigosDosEnviosDto.converter(codigosDosEnvios);
    }

    @Transactional
    public void cancelarEnvioBoleto(String nomeUsuario, Long id) {
        UploadFile uf = envioBoletoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UploadFile não encontrado"));
        uf.setSituacaoArquivo(SituacaoArquivo.CANCELADO);
        uf.setDataHoraAlteracao(Date.from(Instant.now()));
        uf.setUsuarioAlteracao(nomeUsuario);
        envioBoletoRepository.save(uf);
        RegistrosUploadFile ruf = new RegistrosUploadFileMapper().transformar(uf);
        new RegistrosUploadFileService().inserir(ruf);
        envioBoletoRepository.delete(uf);
    }

    private Empresa validarEmpresa(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada ao buscar o resumo total"));
    }

}
