package br.com.paymentmanager.service;

import br.com.paymentmanager.dto.ClienteDto;
import br.com.paymentmanager.dto.ClienteWebDto;
import br.com.paymentmanager.form.ClienteForm;
import br.com.paymentmanager.form.DadosPessoaisForm;
import br.com.paymentmanager.form.EnderecoForm;
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

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Page<ClienteWebDto> listar(Pageable pageable) {
        Page<Cliente> clientes = clienteRepository.findAll(pageable);

        return clientes.map(ClienteWebDto::new);
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

    @Transactional
    public ClienteDto atualizarDadosPessoais(Long id, DadosPessoaisForm form) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        Cliente atualizado = new ClienteMapper().atualizarDadosPessoais(cliente, form);

        return new ClienteDto(atualizado);
    }

    @Transactional
    public ClienteDto atualizarEndereco(Long id, EnderecoForm form) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        Cliente atualizado = new ClienteMapper().atualizarEndereco(cliente, form);

        return new ClienteDto(atualizado);
    }
}
