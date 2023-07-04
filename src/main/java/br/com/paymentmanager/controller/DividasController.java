package br.com.paymentmanager.controller;


import br.com.paymentmanager.dto.DividaComCpfDto;
import br.com.paymentmanager.dto.DividaDto;
import br.com.paymentmanager.form.DividaForm;
import br.com.paymentmanager.mapper.DividaMapper;
import br.com.paymentmanager.model.Cliente;
import br.com.paymentmanager.model.Divida;
import br.com.paymentmanager.repository.ClienteRepository;
import br.com.paymentmanager.repository.DividaRepository;
import br.com.paymentmanager.service.ValorDaDividaInvalido;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/api/dividas")
@CrossOrigin
public class DividasController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DividaRepository dividaRepository;

    @GetMapping
    public List<DividaDto> listar(){
        List<Divida> dividas = dividaRepository.findAll();
        return DividaDto.converter(dividas);
    }

    @GetMapping("/cpf")
    public Page<DividaComCpfDto> listarComCpf(@PageableDefault(size = 5,sort = {"cliente.dadosPessoais.cpf", "dataDeAbertura"},
            direction = Sort.Direction.ASC) Pageable pageable){
        Page<Divida> dividas = dividaRepository.findAll(pageable);
        return DividaComCpfDto.converter(dividas);
    }

    @PostMapping
    public ResponseEntity<DividaDto> cadastrar(@RequestBody @Valid DividaForm form, UriComponentsBuilder uriBuilder){

        Divida divida = verificarCliente(form, clienteRepository, dividaRepository);

        URI uri = uriBuilder.path("/api/dividas/{id}").buildAndExpand(divida.getId()).toUri();
        return ResponseEntity.created(uri).body(new DividaDto(divida));
    }

    public static Divida verificarCliente(@RequestBody @Valid DividaForm form, ClienteRepository clienteRepository, DividaRepository dividaRepository) {
        Cliente cliente = clienteRepository.findById(form.getIdCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "id do cliente não encontrado"));
        if (ValorDaDividaInvalido.validar(form.getValor(), cliente, dividaRepository)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor das dívidas supera 12x a renda do cliente");
        }
        Divida divida = new DividaMapper().cadastrar(form, cliente);
        dividaRepository.save(divida);
        return divida;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DividaComCpfDto> remover(@PathVariable Long id){
        Divida divida = dividaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "id da divida não encontrado"));
        dividaRepository.deleteById(divida.getId());
        return ResponseEntity.ok().build();
    }

}
