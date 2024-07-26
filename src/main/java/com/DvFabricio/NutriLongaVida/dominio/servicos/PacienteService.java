package com.DvFabricio.NutriLongaVida.dominio.servicos;

import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import com.DvFabricio.NutriLongaVida.dominio.entities.paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public void reativarPaciente(Long pacienteId) {
        var paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ValidacaoException("Paciente não encontrado"));
        paciente.reativar();
        pacienteRepository.save(paciente);
    }
}
