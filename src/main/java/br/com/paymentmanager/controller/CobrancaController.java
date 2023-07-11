package br.com.paymentmanager.controller;


import br.com.paymentmanager.dto.CobrancaDto;
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
@RequestMapping("/api/cobrancas")
public class CobrancaController {

    @Autowired
    private CobrancaService cobrancaService;

    @GetMapping
    public ResponseEntity<List<CobrancaDto>> listar() {
        return ResponseEntity.ok().body(cobrancaService.listar());
    }

    @PostMapping
    public ResponseEntity<CobrancaDto> cadastrar(@RequestBody @Valid CobrancaForm form, UriComponentsBuilder uriBuilder) {
        CobrancaDto cobrancaDto = cobrancaService.cadastrar(form);

        URI uri = uriBuilder.path("/api/cobrancas/{id}").buildAndExpand(cobrancaDto.getId()).toUri();
        return ResponseEntity.created(uri).body(cobrancaDto);
    }

}
