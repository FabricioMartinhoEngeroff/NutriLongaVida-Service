package com.DvFabricio.NutriLongaVida.Controller;

import com.DvFabricio.NutriLongaVida.dominio.nutricionista.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nutricionistas")
public class NutricionistaController {

    @Autowired
    private NutricionistaRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroNutricionista dados) {
        repository.save(new Nutricionista(dados));
    }


    @GetMapping
    public Page<DadosListagemNutricionista> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemNutricionista::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoNutricionista dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoesNutricionista(dados);


    }


}
