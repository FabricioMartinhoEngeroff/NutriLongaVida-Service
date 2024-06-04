package com.DvFabricio.NutriLongaVida.dominio.paciente;

import com.DvFabricio.NutriLongaVida.dominio.consulta.Consulta;
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
@Table(name = "pacientes")
@Entity(name = "Paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String email;
    private String telefone;
    private String endereco;
    private String genero;
    private List<Consulta> historicoConsultas;
    private Dieta dietaAtual;
    private List<MedidasPaciente> medidasCorporais;
}
