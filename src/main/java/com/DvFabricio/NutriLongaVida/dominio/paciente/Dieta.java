package com.DvFabricio.NutriLongaVida.dominio.paciente;

import com.DvFabricio.NutriLongaVida.dominio.alimento.AlimentosDaDieta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "dietas")
@Entity(name = "Dieta")
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<AlimentosDaDieta> itensDieta;
    private String observacoes;
}
