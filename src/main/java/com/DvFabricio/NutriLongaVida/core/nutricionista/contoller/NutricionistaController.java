package com.DvFabricio.NutriLongaVida.core.nutricionista.contoller;


import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Especialidade;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.NutricionistaDTO;
import com.DvFabricio.NutriLongaVida.core.nutricionista.service.NutricionistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutricionistas")
public class NutricionistaController {

    @Autowired
    private NutricionistaService service;

    // Listar nutricionistas ativos
    @GetMapping("/ativos")
    public ResponseEntity<List<NutricionistaDTO>> listarAtivos() {
        List<NutricionistaDTO> nutricionistas = service.listarAtivos();
        return ResponseEntity.ok(nutricionistas);
    }

    // Buscar nutricionista por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<NutricionistaDTO> buscarPorNome(@PathVariable String nome) {
        Optional<NutricionistaDTO> nutricionista = service.buscarPorNome(nome);
        return nutricionista.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar nutricionista por especialidade
    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<List<NutricionistaDTO>> buscarPorEspecialidade(@PathVariable Especialidade especialidade) {
        List<NutricionistaDTO> nutricionistas = service.buscarPorEspecialidade(especialidade);
        return ResponseEntity.ok(nutricionistas);
    }

    // Cadastrar nutricionista
    @PostMapping
    public ResponseEntity<NutricionistaDTO> cadastrar(@RequestBody @Valid NutricionistaDTO dto) {
        try {
            NutricionistaDTO nutricionista = service.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nutricionista);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    // Ativar nutricionista
    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Void> ativarNutricionista(@PathVariable Long id) {
        try {
            service.ativarNutricionista(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Desativar nutricionista
    @PatchMapping("/desativar/{id}")
    public ResponseEntity<Void> desativarNutricionista(@PathVariable Long id) {
        try {
            service.desativarNutricionista(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
