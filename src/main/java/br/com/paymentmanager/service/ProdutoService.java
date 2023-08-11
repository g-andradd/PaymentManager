package br.com.paymentmanager.service;

import br.com.paymentmanager.dto.ProdutoDto;
import br.com.paymentmanager.exception.ResourceNotFoundException;
import br.com.paymentmanager.form.ProdutoForm;
import br.com.paymentmanager.mapper.ProdutoMapper;
import br.com.paymentmanager.model.Produto;
import br.com.paymentmanager.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    public List<ProdutoDto> listar() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }

    public ProdutoDto detalhar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        return new ProdutoDto(produto);
    }

    @Transactional
    public ProdutoDto cadastrar(ProdutoForm form) {
        Produto produto = new ProdutoMapper().criar(form);

        return new ProdutoDto(produto);
    }

    @Transactional
    public ProdutoDto atualizar(Long id, ProdutoForm form) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        Produto atualizado = new ProdutoMapper().atualizar(produto, form);

        return new ProdutoDto(atualizado);
    }

    public void remover(Long id) {
        produtoRepository.deleteById(id);
    }
}
