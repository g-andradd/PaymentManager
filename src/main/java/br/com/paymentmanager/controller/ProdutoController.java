package br.com.paymentmanager.controller;

import br.com.paymentmanager.dto.ProdutoDto;
import br.com.paymentmanager.form.ProdutoForm;
import br.com.paymentmanager.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> listar () {
        return ResponseEntity.ok(produtoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> detalhar (@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.detalhar(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrar (@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {
        ProdutoDto produtoDto = produtoService.cadastrar(form);

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoDto.getId()).toUri();
        return ResponseEntity.created(uri).body(produtoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoForm form) {
        return ResponseEntity.ok(produtoService.atualizar(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        produtoService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
