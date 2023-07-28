package br.com.paymentmanager.controller;


import br.com.paymentmanager.dto.CobrancaDto;
import br.com.paymentmanager.form.AtualizaCobrancaForm;
import br.com.paymentmanager.form.CobrancaForm;
import br.com.paymentmanager.service.CobrancaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cobrancas")
public class CobrancaController {

    @Autowired
    private CobrancaService cobrancaService;

    @GetMapping
    public ResponseEntity<List<CobrancaDto>> listarTodas() {
        return ResponseEntity.ok(cobrancaService.listarTodas());
    }

    @GetMapping("divida/{dividaId}")
    public ResponseEntity<List<CobrancaDto>> listarPorDivida(@PathVariable Long dividaId) {
        return ResponseEntity.ok(cobrancaService.listarPorDivida(dividaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CobrancaDto> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(cobrancaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CobrancaDto> cadastrar(@RequestBody @Valid CobrancaForm form, UriComponentsBuilder uriBuilder) {
        CobrancaDto cobrancaDto = cobrancaService.cadastrar(form);

        URI uri = uriBuilder.path("/cobrancas/{id}").buildAndExpand(cobrancaDto.getId()).toUri();
        return ResponseEntity.created(uri).body(cobrancaDto);
    }

    @PutMapping
    public ResponseEntity<CobrancaDto> atualizar(@RequestBody @Valid AtualizaCobrancaForm form) {
        return ResponseEntity.ok(cobrancaService.atualizar(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cobrancaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
