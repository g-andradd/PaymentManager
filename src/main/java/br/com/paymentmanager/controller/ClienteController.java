package br.com.paymentmanager.controller;

import br.com.paymentmanager.dto.ClienteDto;
import br.com.paymentmanager.dto.ClienteWebDto;
import br.com.paymentmanager.form.AtualizaClienteForm;
import br.com.paymentmanager.form.ClienteForm;
import br.com.paymentmanager.projection.RelatorioClientesProjecao;
import br.com.paymentmanager.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<Page<ClienteWebDto>> listarPaginado(@PageableDefault(size = 5,
            sort = {"dadosPessoais.nome", "status"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(clienteService.listar(pageable));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
        ClienteDto clienteDto = clienteService.cadastrar(form);

        URI uri = uriBuilder.path("/api/clientes/{id}").buildAndExpand(clienteDto.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteDto);
    }

    @GetMapping("/report")
    public ResponseEntity<List<RelatorioClientesProjecao>> listarReport() {
        return ResponseEntity.ok().body(clienteService.listarReport());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.detalhar(id));
    }

    @PutMapping
    public ResponseEntity<ClienteDto> atualizar(@RequestBody @Valid AtualizaClienteForm form) {
        return ResponseEntity.ok(clienteService.atualizar(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        clienteService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ClienteDto> atualizarStatus(@PathVariable Long id) {
        clienteService.atualizarStatus(id);
        return ResponseEntity.ok().build();
    }

}
