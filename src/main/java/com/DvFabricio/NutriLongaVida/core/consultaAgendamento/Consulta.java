package com.DvFabricio.NutriLongaVida.core.consultaAgendamento;


import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Nutricionista;
import com.DvFabricio.NutriLongaVida.core.paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nutricionista_id")
    private Nutricionista nutricionista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

    private MotivoCancelamento motivoCancelamento;


    public void cancelar(MotivoCancelamento motivo) {
        this.motivoCancelamento = motivo;
    }

}



