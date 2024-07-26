package com.DvFabricio.NutriLongaVida.controller;

import com.DvFabricio.NutriLongaVida.dominio.consultaAgendamento.*;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ResourceNotFoundException;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @Autowired
    private ConsultaRepository repository;

    @GetMapping("/paciente/{id}")
    public ResponseEntity<Page<DadosDescricaoConsulta>> findConsultasByPaciente(@PathVariable Long id, Pageable pageable) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado com id " + id);
        }

        Page<Consulta> consultas = repository.findByPacienteId(id, pageable);
        Page<DadosDescricaoConsulta> resultado = consultas.map(DadosDescricaoConsulta::new);

        return ResponseEntity.ok().body(resultado);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDescricaoConsulta>> listarConsultas(Pageable pageable) {
        Page<Consulta> consultas = repository.findAll(pageable);
        Page<DadosDescricaoConsulta> resultado = consultas.map(DadosDescricaoConsulta::new);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/por-data")
    public ResponseEntity<Page<DadosDescricaoConsulta>> buscarPorData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data,
            Pageable pageable) {
        Page<Consulta> consultas = repository.findByDataConsulta(data, pageable);
        Page<DadosDescricaoConsulta> resultado = consultas.map(DadosDescricaoConsulta::new);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        try{
        var dto = agenda.agendar(dados);
            return ResponseEntity.ok(dto);
        } catch (ValidacaoException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> cancelar(@RequestBody @Valid DadosCancelarConsulta dados) {
        try {
            agenda.cancelar(dados);
            return ResponseEntity.noContent().build();
        } catch (ValidacaoException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }
}