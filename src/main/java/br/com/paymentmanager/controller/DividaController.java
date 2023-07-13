package br.com.paymentmanager.controller;

import br.com.paymentmanager.dto.DividaComCpfDto;
import br.com.paymentmanager.dto.DividaDto;
import br.com.paymentmanager.form.DividaForm;
import br.com.paymentmanager.service.DividaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/dividas")
@CrossOrigin
public class DividaController {

    @Autowired
    private DividaService dividaService;

    @GetMapping
    public ResponseEntity<List<DividaDto>> listar(){
        return ResponseEntity.ok().body(dividaService.listar());
    }

    @GetMapping("/cpf")
    public ResponseEntity<Page<DividaComCpfDto>> listarComCpf(@PageableDefault(size = 5,sort = {"cliente.dadosPessoais.cpf", "dataDeAbertura"},
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok().body(dividaService.ListarComCpf(pageable));
    }

    @PostMapping
    public ResponseEntity<DividaDto> cadastrar(@RequestBody @Valid DividaForm form, UriComponentsBuilder uriBuilder){

        DividaDto dividaDto = dividaService.cadastrar(form);

        URI uri = uriBuilder.path("/api/dividas/{id}").buildAndExpand(dividaDto.getId()).toUri();
        return ResponseEntity.created(uri).body(dividaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DividaComCpfDto> remover(@PathVariable Long id){
        dividaService.remover(id);
        return ResponseEntity.ok().build();
    }

}
