package com.DvFabricio.NutriLongaVida.core.paciente.controller;

import com.DvFabricio.NutriLongaVida.core.paciente.domain.PacienteDTO;
import com.DvFabricio.NutriLongaVida.core.paciente.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // Listar todos os pacientes ativos com paginação
    @GetMapping("/ativos")
    public ResponseEntity<Page<PacienteDTO>> listarAtivos(
            @PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao) {
        Page<PacienteDTO> pacientesAtivos = pacienteService.listarAtivos(paginacao);
        return ResponseEntity.ok(pacientesAtivos);
    }

    // Buscar paciente por nome com paginação
    @GetMapping("/buscar")
    public ResponseEntity<Page<PacienteDTO>> buscarPorNome(
            @RequestParam String nome,
            @PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao) {
        Page<PacienteDTO> pacientes = pacienteService.buscarPorNome(nome, paginacao);
        return ResponseEntity.ok(pacientes);
    }

    // Buscar paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) {
        PacienteDTO pacienteDTO = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(pacienteDTO);
    }

    // Cadastrar paciente
    @PostMapping
    @Transactional
    public ResponseEntity<PacienteDTO> cadastrar(@RequestBody @Valid PacienteDTO dto) {
        PacienteDTO pacienteDTO = pacienteService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteDTO);
    }

    // Reativar paciente
    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reativarPaciente(@PathVariable Long id) {
        pacienteService.reativarPaciente(id);
        return ResponseEntity.noContent().build();
    }

    // Desativar paciente
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativarPaciente(@PathVariable Long id) {
        pacienteService.desativarPaciente(id);
        return ResponseEntity.noContent().build();
    }

    // Alterar status do paciente (ativar/desativar)
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> alterarStatusPaciente(@PathVariable Long id, @RequestParam Boolean status) {
        pacienteService.alterarStatusPaciente(id, status);
        return ResponseEntity.noContent().build();
    }

    // Atualizar informações do paciente
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> atualizarInformacoesPaciente(@PathVariable Long id, @RequestBody @Valid PacienteDTO dto) {
        pacienteService.atualizarInformacoesPaciente(id, dto);
        return ResponseEntity.noContent().build();
    }
}

