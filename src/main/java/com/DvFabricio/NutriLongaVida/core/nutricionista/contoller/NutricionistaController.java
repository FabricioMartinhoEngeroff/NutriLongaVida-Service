package com.DvFabricio.NutriLongaVida.core.nutricionista.contoller;


import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Especialidade;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Nutricionista;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.NutricionistaDTO;
import com.DvFabricio.NutriLongaVida.core.nutricionista.service.NutricionistaService;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/nutricionistas")
public class NutricionistaController {

    @Autowired
    private NutricionistaService service;

    // Listar nutricionistas ativos com paginação
    @GetMapping("/ativos")
    public ResponseEntity<Page<NutricionistaDTO>> listarAtivos(
            @PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao) {
        Page<NutricionistaDTO> nutricionistas = service.listarAtivos(paginacao);
        return ResponseEntity.ok(nutricionistas);
    }

    // Buscar nutricionista por nome com paginação
    @GetMapping("/nome")
    public ResponseEntity<Page<NutricionistaDTO>> buscarPorNome(
            @RequestParam String nome,
            @PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao) {
        Page<NutricionistaDTO> nutricionistas = service.buscarPorNome(nome, paginacao);
        if (nutricionistas.isEmpty()) {
            throw new ResourceNotFoundException("Nutricionista não encontrado com o nome: " + nome);
        }
        return ResponseEntity.ok(nutricionistas);
    }


    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<List<NutricionistaDTO>> buscarPorEspecialidade(@PathVariable Especialidade especialidade) {
        List<NutricionistaDTO> nutricionistas = service.buscarPorEspecialidade(especialidade);
        return ResponseEntity.ok(nutricionistas);
    }

    @PostMapping
    public ResponseEntity<NutricionistaDTO> cadastrar(@RequestBody @Valid NutricionistaDTO dto) {
        NutricionistaDTO nutricionista = service.cadastrar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(nutricionista.id()).toUri();
        return ResponseEntity.created(uri).body(nutricionista);
    }

    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Void> ativarNutricionista(@PathVariable Long id) {
        service.ativarNutricionista(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/desativar/{id}")
    public ResponseEntity<Void> desativarNutricionista(@PathVariable Long id) {
        service.desativarNutricionista(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<Void> alterarStatusNutricionista(@PathVariable Long id, @RequestParam Boolean status) {
        service.alterarStatusNutricionista(id, status);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nutricionista> buscarPorId(@PathVariable Long id) {
        Nutricionista nutricionista = service.buscarPorId(id);
        return ResponseEntity.ok(nutricionista);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarInformacoesNutricionista(
            @PathVariable Long id,
            @RequestBody @Valid NutricionistaDTO dto) {
        service.atualizarInformacoesNutricionista(id, dto);
        return ResponseEntity.noContent().build();
    }
}