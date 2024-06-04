package com.DvFabricio.NutriLongaVida.dominio.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "medidasPacientes")
@Entity(name = "MedidaPaciente")
public class MedidasPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataMedicao;
    private double peso;
    private double altura;
    private double circunferenciaCintura;
    private double circunferenciaQuadril;
    private double percentualGorduraCorporal;
    private double massaMuscular;
    private double indiceMassaCorporal;
}
