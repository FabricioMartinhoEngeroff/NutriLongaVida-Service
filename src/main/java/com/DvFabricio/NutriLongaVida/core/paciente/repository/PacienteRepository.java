package com.DvFabricio.NutriLongaVida.core.paciente.repository;

import com.DvFabricio.NutriLongaVida.core.paciente.domain.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    Page<Paciente> findByNomeIgnoreCaseContainingAndAtivoTrue(String nome, Pageable paginacao);

    Optional<Paciente> findByCpf(String cpf);

    @Query("""
            select p.ativo from Paciente p
            where p.id = :id
            """)
    Boolean findAtivoById(Long id);

}
