package br.com.paymentmanager.controller;

import br.com.paymentmanager.dto.CodigosDosEnviosDto;
import br.com.paymentmanager.dto.EnvioBoletoDto;
import br.com.paymentmanager.dto.ResumoTotalDto;
import br.com.paymentmanager.service.EnvioBoletoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/envios")
public class EnvioBoletoController {

    private final EnvioBoletoService envioBoletoService;

    public EnvioBoletoController(EnvioBoletoService envioBoletoService) {
        this.envioBoletoService = envioBoletoService;
    }

    //todo Fazer o token

    @GetMapping
    public List<EnvioBoletoDto> listar(@RequestParam String data) {
        return envioBoletoService.listarTodos(1L, data);
    }

    @GetMapping("/resumo/total")
    public ResumoTotalDto buscarResumoTotal(@RequestParam String data) {
        return envioBoletoService.buscarResumoTotal(1L, data);
    }

    @GetMapping("/codigo")
    public List<CodigosDosEnviosDto> listarCodigos(@RequestParam String data) {
        return envioBoletoService.buscarCodigosDosEnvios(1L, data);
    }

    @PutMapping("/cancelar/{codigo}")
    public ResponseEntity<Void> cancelar(@PathVariable Long codigo) {
        envioBoletoService.cancelarEnvioBoleto("Teste", codigo);
        return ResponseEntity.ok().build();
    }

}
