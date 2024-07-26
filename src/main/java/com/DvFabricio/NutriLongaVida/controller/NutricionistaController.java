package com.DvFabricio.NutriLongaVida.controller;

import com.DvFabricio.NutriLongaVida.dominio.entities.nutricionista.*;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nutricionistas")
public class NutricionistaController {

    @Autowired
    private NutricionistaRepository repository;


    @GetMapping
    public Page<DadosListagemNutricionista> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemNutricionista::new);
    }

    @GetMapping("/por-nome")
    public Page<DadosListagemNutricionista> buscarPorNome(
            @RequestParam String nome,
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findByNomeIgnoreCaseContainingAndAtivoTrue(nome, paginacao)
                .map(DadosListagemNutricionista::new);
    }

    @GetMapping("/por-especialidade/{especialidade}")
    public Page<DadosListagemNutricionista> buscarPorEspecialidade(
            @PathVariable Especialidade especialidade,
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findByEspecialidadeAndAtivoTrue(especialidade, paginacao)
                .map(DadosListagemNutricionista::new);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroNutricionista dados) {
        repository.save(new Nutricionista(dados));
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoNutricionista dados) {
        var nutricionista = repository.getReferenceById(dados.id());
        nutricionista.atualizarInformacoesNutricionista(dados);
    }

    @PutMapping("/{id}/ativar")
    @Transactional
    public ResponseEntity<Void> ativarNutricionista(@PathVariable Long id) {
        Nutricionista nutricionista = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista não encontrado com id " + id));
        nutricionista.setAtivo(true);
        repository.save(nutricionista);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/desativar")
    @Transactional
    public ResponseEntity<Void> desativarNutricionista(@PathVariable Long id) {
        Nutricionista nutricionista = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista não encontrado com id " + id));
        nutricionista.setAtivo(false);
        repository.save(nutricionista);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var nutricionista = repository.getReferenceById(id);
        nutricionista.excluir();
    }

}
