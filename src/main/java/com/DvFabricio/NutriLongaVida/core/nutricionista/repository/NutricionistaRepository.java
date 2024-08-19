package com.DvFabricio.NutriLongaVida.core.nutricionista.repository;

import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Especialidade;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Nutricionista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {

    List<Nutricionista> findByAtivoTrue();

    Optional<Nutricionista> findByNome(String nome);

    List<Nutricionista> findByEspecialidade(Especialidade especialidade);

    Optional<Nutricionista> findByCpf(String cpf);

    Optional<Nutricionista> findByRegistroProfissionalCrm(String crm);

    // Escolher nutricionista aleatório disponível na data
    @Query("SELECT n FROM Nutricionista n " +
            "WHERE n.especialidade = :especialidade " +
            "AND n.ativo = true " +
            "AND n.id NOT IN (SELECT c.idNutricionista FROM Consulta c WHERE c.data = :data) " +
            "ORDER BY FUNCTION('RAND')")
    Nutricionista escolherNutricionistaAleatorioDisponivelNaData(@Param("especialidade") Especialidade especialidade, @Param("data") LocalDate data);
}