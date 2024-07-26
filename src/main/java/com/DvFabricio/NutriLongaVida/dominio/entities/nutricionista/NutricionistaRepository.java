package com.DvFabricio.NutriLongaVida.dominio.entities.nutricionista;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {
    Page<Nutricionista> findAllByAtivoTrue(Pageable paginacao);

    Page<Nutricionista> findByNomeIgnoreCaseContainingAndAtivoTrue(String nome, Pageable paginacao);

    Page<Nutricionista> findByEspecialidadeAndAtivoTrue(Especialidade especialidade, Pageable paginacao);

    @Query("""
        select n.id from Nutricionista n
        where n.ativo = true
        and n.especialidade = :especialidade
        and n.id not in (
            select c.nutricionista.id from Consulta c
            where c.data = :data
        )
        order by rand()
        limit 1
        """)
    Nutricionista escolherNutricionistaAleatorioDisponivelNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select n.ativo from Nutricionista n where
            n.id = :id
            """)
    Boolean findAtivoById(Long id);

}
