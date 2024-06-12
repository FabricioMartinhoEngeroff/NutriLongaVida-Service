package com.DvFabricio.NutriLongaVida.dominio.agendamento;


import com.DvFabricio.NutriLongaVida.dominio.nutricionista.Nutricionista;
import com.DvFabricio.NutriLongaVida.dominio.paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "consultas")
@Entity(name = "Consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "data_consulta_id", referencedColumnName = "id")
    private DataConsulta dataConsulta;

    @ManyToOne
    @JoinColumn(name = "nutricionista_id", nullable = false)
    private Nutricionista nutricionista;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    @Column(name = "observacoes", length = 500)
    private String observacoes;

    @Column(name = "consulta_concluida")
    private boolean consultaConcluida;

    public static Consulta agendarConsulta(DataConsulta dataConsulta, Nutricionista nutricionista, Paciente paciente) {
        Consulta consulta = new Consulta();
        consulta.dataConsulta = dataConsulta;
        consulta.nutricionista = nutricionista;
        consulta.paciente = paciente;
        consulta.consultaConcluida = false;
        return consulta;
    }

    public boolean concluirConsulta() {
        if (consultaConcluida) {
            return false;
        }
        consultaConcluida = true;
        return true;
    }
}



