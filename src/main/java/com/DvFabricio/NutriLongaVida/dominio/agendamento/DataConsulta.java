package com.DvFabricio.NutriLongaVida.dominio.agendamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "dataConsultas")
@Entity(name = "DataConsulta")
public class DataConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private LocalTime horarioInicio;
    private boolean disponivel;

    public void tornarIndisponivel() {
        if (!disponivel) {
            throw new IllegalStateException("A data já está marcada como indisponível.");
        }
        disponivel = false;
    }

    public void tornarDisponivel() {
        if (disponivel) {
            throw new IllegalStateException("A data já está marcada como disponível.");
        }
        disponivel = true;
    }

    public void reagendar(LocalDate novaData, LocalTime novoHorario) {
        if (!disponivel) {
            throw new IllegalStateException("Não é possível reagendar uma data de consulta indisponível.");
        }
        this.data = novaData;
        this.horarioInicio = novoHorario;
        // Após reagendar, a data deve ser marcada como indisponível
        this.disponivel = false;
    }
}
