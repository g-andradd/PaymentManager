package br.com.paymentmanager.service;

import br.com.paymentmanager.dto.ClienteDto;
import br.com.paymentmanager.dto.ClienteWebDto;
import br.com.paymentmanager.form.AtualizacaoClienteForm;
import br.com.paymentmanager.form.ClienteForm;
import br.com.paymentmanager.mapper.ClienteMapper;
import br.com.paymentmanager.model.Cliente;
import br.com.paymentmanager.model.StatusCliente;
import br.com.paymentmanager.projection.RelatorioClientesProjecao;
import br.com.paymentmanager.repository.ClienteRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Page<ClienteWebDto> listarPaginado(Pageable pageable) {
        Page<Cliente> clientes = clienteRepository.findAll(pageable);

        return clientes.map(ClienteWebDto::new);
    }

    public List<ClienteWebDto> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(ClienteWebDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ClienteDto cadastrar(ClienteForm form) {
        Cliente cliente = new ClienteMapper().cadastrar(form);
        clienteRepository.save(cliente);

        return new ClienteDto(cliente);
    }

    @Cacheable(value = "listaDeClientes")
    public List<RelatorioClientesProjecao> listarReport() {
        return clienteRepository.findNomeValorDasDividasCobrancas();
    }

    public ClienteDto detalhar(Long id) {
        return new ClienteDto(clienteRepository.getReferenceById(id));
    }

    @Transactional
    public ClienteDto atualizar(Long id, AtualizacaoClienteForm form) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        Cliente atualizado = new ClienteMapper().atualizar(cliente, form);

        return new ClienteDto(atualizado);
    }

    //todo implementar validações e tratamento de erros
    @Transactional
    public void remover(Long id) {
        clienteRepository.deleteById(id);
    }

    @Transactional
    public void atualizarStatus(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "id do cliente não encontrado ao remover o cliente"));

        if(cliente.getStatus().equals(StatusCliente.SUSPENSO)){
            cliente.setStatus(StatusCliente.ATIVO);
        }else{
            cliente.setStatus(StatusCliente.SUSPENSO);
        }
    }

}
