package br.com.paymentmanager.controller;

import br.com.paymentmanager.dto.ClienteDto;
import br.com.paymentmanager.dto.ClienteWebDto;
import br.com.paymentmanager.form.AtualizacaoClienteForm;
import br.com.paymentmanager.form.ClienteForm;
import br.com.paymentmanager.mapper.ClienteMapper;
import br.com.paymentmanager.model.Cliente;
import br.com.paymentmanager.projection.RelatorioClientesProjecao;
import br.com.paymentmanager.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class ClientesController {

    private final ClienteRepository clienteRepository;

    public ClientesController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/api/clientes")
    public ResponseEntity<Page<ClienteWebDto>> listarPaginado(@PageableDefault(size = 5,sort = {"dadosPessoais.nome", "status"},
            direction = Direction.ASC) Pageable pageable) {
        Page<Cliente> clientes = clienteRepository.findAll(pageable);
        return ResponseEntity.ok().body(ClienteWebDto.converter(clientes));
    }

    @GetMapping("/api/listaClientes")
    public ResponseEntity<List<ClienteWebDto>> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok().body(ClienteWebDto.converterLista(clientes));
    }

    @PostMapping("/api/clientes")
    @Transactional
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
        Cliente cliente = new ClienteMapper().cadastrar(form);
        clienteRepository.save(cliente);

        URI uri = uriBuilder.path("/api/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDto(cliente));
    }

    @GetMapping("/api/clientes/report")
    @Cacheable(value = "listaDeClientes")
    public ResponseEntity<List<RelatorioClientesProjecao>> listarReport() {
        return ResponseEntity.ok().body(clienteRepository.findNomeValorDasDividasCobrancas());
    }

    @GetMapping("/api/clientes/{id}")
    public ResponseEntity<ClienteDto> detalhar(@PathVariable Long id) {
        Cliente cliente = clienteRepository.getById(id);
        return ResponseEntity.ok().body(new ClienteDto(cliente));
    }

    @PutMapping("/api/clientes/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoClienteForm form) {
        Cliente cliente = clienteRepository.getById(id);
        Cliente atualizado = new ClienteMapper().atualizarApi(cliente, form);

        return ResponseEntity.ok(new ClienteDto(atualizado));
    }

    @DeleteMapping("/api/clientes/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> remover(@PathVariable Long id){
        clienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/clientes/status/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> atualizarStatus(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "id do cliente não encontrado"));

        cliente.alteraStatus();
        return ResponseEntity.ok().build();
    }

}
