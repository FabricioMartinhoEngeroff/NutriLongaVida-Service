package com.DvFabricio.NutriLongaVida.dominio.paciente;

import com.DvFabricio.NutriLongaVida.dominio.agendamento.Consulta;
import com.DvFabricio.NutriLongaVida.dominio.alimento.Dieta;
import com.DvFabricio.NutriLongaVida.dominio.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String email;
    private String telefone;

    @Embedded
    private Endereco endereco;

    private String genero;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<Consulta> historicoConsultas = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Dieta dietaAtual;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<MedidasPaciente> medidasCorporais = new ArrayList<>();

    private Boolean ativo = Boolean.TRUE;

    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.dataNascimento = dados.dataNascimento();
        this.genero = dados.genero();
        this.endereco = new Endereco(dados.endereco());
    }


    public void excluir() {
        this.ativo = false;
    }
}
