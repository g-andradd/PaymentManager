package br.com.paymentmanager.service;

import br.com.paymentmanager.dto.DividaComCpfDto;
import br.com.paymentmanager.dto.DividaDto;
import br.com.paymentmanager.form.DividaForm;
import br.com.paymentmanager.mapper.DividaMapper;
import br.com.paymentmanager.model.Cliente;
import br.com.paymentmanager.model.Divida;
import br.com.paymentmanager.repository.ClienteRepository;
import br.com.paymentmanager.repository.DividaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DividaService {

    private final ClienteRepository clienteRepository;

    private final DividaRepository dividaRepository;

    public DividaService(ClienteRepository clienteRepository, DividaRepository dividaRepository) {
        this.clienteRepository = clienteRepository;
        this.dividaRepository = dividaRepository;
    }

    public List<DividaDto> listar() {
        List<Divida> dividas = dividaRepository.findAll();
        return dividas.stream().map(DividaDto::new).collect(Collectors.toList());
    }

    public Page<DividaComCpfDto> ListarComCpf(Pageable pageable) {
        Page<Divida> dividas = dividaRepository.findAll(pageable);
        return dividas.map(DividaComCpfDto::new);
    }

    @Transactional
    public DividaDto cadastrar(DividaForm form) {
        Cliente cliente = buscarCliente(form.getIdCliente());
        Divida divida = new DividaMapper().cadastrar(form, cliente);
        this.dividaRepository.save(divida);
        return new DividaDto(divida);
    }

    @Transactional
    public void remover(Long id){
        Divida divida = buscarDivida(id);
        dividaRepository.deleteById(divida.getId());
    }

    protected Divida buscarDivida(Long id) {
        return this.dividaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "id da divida não encontrado"));
    }

    private Cliente buscarCliente(Long id) {
        return this.clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "id do cliente não encontrado"));
    }
}
