package br.com.paymentmanager.service;

import br.com.paymentmanager.dto.CobrancaDto;
import br.com.paymentmanager.exception.ResourceNotFoundException;
import br.com.paymentmanager.form.CobrancaForm;
import br.com.paymentmanager.mapper.CobrancaMapper;
import br.com.paymentmanager.model.Cobranca;
import br.com.paymentmanager.model.Divida;
import br.com.paymentmanager.repository.CobrancaRepository;
import br.com.paymentmanager.repository.DividaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CobrancaService {

    private final CobrancaRepository cobrancaRepository;

    private final DividaService dividaService;

    public CobrancaService(CobrancaRepository cobrancaRepository, DividaRepository dividaRepository, DividaService dividaService) {
        this.cobrancaRepository = cobrancaRepository;
        this.dividaService = dividaService;
    }

    public List<CobrancaDto> listarTodas() {
        List<Cobranca> cobrancas = cobrancaRepository.findAll();
        return cobrancas.stream().map(CobrancaDto::new).collect(Collectors.toList());
    }

    public List<CobrancaDto> listarPorDivida(Long dividaId) {
        List<Cobranca> cobrancas = cobrancaRepository.findByDividaId(dividaId);

        return cobrancas.stream().map(CobrancaDto::new).collect(Collectors.toList());
    }

    @Transactional
    public CobrancaDto cadastrar(CobrancaForm form) {
        Divida divida = dividaService.buscarDivida(form.getIdDivida());
        Cobranca cobranca = new CobrancaMapper().cadastrar(form, this.cobrancaRepository, divida);

        cobrancaRepository.save(cobranca);

        return new CobrancaDto(cobranca);
    }


    public CobrancaDto buscarPorId(Long id) {
        Cobranca cobranca = cobrancaRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cobrança não encontrada"));

        return new CobrancaDto(cobranca);
    }
}
