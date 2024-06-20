package com.DvFabricio.NutriLongaVida.controller;

import com.DvFabricio.NutriLongaVida.dominio.agendamento.AgendaDeConsultas;
import com.DvFabricio.NutriLongaVida.dominio.agendamento.DadosAgendamentoConsulta;
import com.DvFabricio.NutriLongaVida.dominio.agendamento.DadosCancelarConsulta;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

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