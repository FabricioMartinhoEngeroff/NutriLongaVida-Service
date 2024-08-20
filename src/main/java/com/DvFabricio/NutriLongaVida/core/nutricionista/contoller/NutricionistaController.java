package com.DvFabricio.NutriLongaVida.core.nutricionista.contoller;


import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Especialidade;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Nutricionista;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.NutricionistaDTO;
import com.DvFabricio.NutriLongaVida.core.nutricionista.service.NutricionistaService;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/ativos")
    public ResponseEntity<List<NutricionistaDTO>> listarAtivos() {
        List<NutricionistaDTO> nutricionistas = service.listarAtivos();
        return ResponseEntity.ok(nutricionistas);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<NutricionistaDTO> buscarPorNome(@PathVariable String nome) {
        return service.buscarPorNome(nome)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista não encontrado com o nome: " + nome));
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

    @GetMapping("/{id}")
    public ResponseEntity<Nutricionista> buscarPorId(@PathVariable Long id) {
        Nutricionista nutricionista = service.buscarPorId(id);
        return ResponseEntity.ok(nutricionista);
    }
}