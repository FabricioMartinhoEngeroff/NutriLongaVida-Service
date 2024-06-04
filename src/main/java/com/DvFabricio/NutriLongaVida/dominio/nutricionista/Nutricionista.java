package com.DvFabricio.NutriLongaVida.dominio.nutricionista;

import com.DvFabricio.NutriLongaVida.dominio.consulta.Consulta;
import com.DvFabricio.NutriLongaVida.dominio.paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "nutricionistas")
@Entity(name = "Nutricionista")
public class Nutricionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String registroProfissionalCrm;
    private String email;
    private String telefone;
    private String endereco;
    private double valorConsulta;

    private List<String> especializacoes;
    private String horarioAtendimento;
    private List<Paciente> pacientes;
    private List<Consulta> consultasAgendadas;

}
