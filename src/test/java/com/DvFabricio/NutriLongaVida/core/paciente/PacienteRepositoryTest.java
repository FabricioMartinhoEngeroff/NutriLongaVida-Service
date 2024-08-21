package com.DvFabricio.NutriLongaVida.core.paciente;

import com.DvFabricio.NutriLongaVida.core.paciente.domain.Paciente;
import com.DvFabricio.NutriLongaVida.core.paciente.repository.PacienteRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Should get active patients from DB")
    void testFindAllByAtivoTrue() {

        this.createPaciente(true);

        Page<Paciente> pacientesAtivos = pacienteRepository.findAllByAtivoTrue(Pageable.unpaged());

        assertThat(pacientesAtivos).isNotEmpty();
    }

    @Test
    @DisplayName("Should get patients by name containing and active status")
    void testFindByNomeIgnoreCaseContainingAndAtivoTrue() {

        this.createPaciente(true, "João Silva");

        Page<Paciente> pacientesComNome = pacienteRepository.findByNomeIgnoreCaseContainingAndAtivoTrue("João", Pageable.unpaged());

        assertThat(pacientesComNome).isNotEmpty();
    }

    @Test
    @DisplayName("Should get active status by patient ID")
    void testFindAtivoById() {

        Paciente paciente = this.createPaciente(true);

        Boolean ativo = pacienteRepository.findAtivoById(paciente.getId());

        assertThat(ativo).isTrue();
    }

    private Paciente createPaciente(Boolean ativo, String nome) {
        Paciente paciente = new Paciente();
        paciente.setAtivo(ativo);
        paciente.setNome(nome);
        this.entityManager.persist(paciente);
        return paciente;
    }

    private Paciente createPaciente(Boolean ativo) {
        return createPaciente(ativo, "Paciente Teste");
    }
}