package com.DvFabricio.NutriLongaVida.core.paciente.domain;

import com.DvFabricio.NutriLongaVida.core.Pagamento.CartaoCredito;
import com.DvFabricio.NutriLongaVida.core.alimento.Dieta;
import com.DvFabricio.NutriLongaVida.core.consultaAgendamento.Consulta;
import com.DvFabricio.NutriLongaVida.core.endereco.Endereco;
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
    private String genero;

    @Embedded
    private Endereco endereco;


    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<Consulta> historicoConsultas = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Dieta dietaAtual;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<MedidasPaciente> medidasCorporais = new ArrayList<>();

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CartaoCredito> cartoesCredito = new ArrayList<>();

    private Boolean ativo = Boolean.TRUE;

    public Paciente(PacienteDTO dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.dataNascimento = dto.dataNascimento();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.genero = dto.genero();
        this.endereco = new Endereco(dto.endereco());
    }


    public void excluir() {
        this.ativo = false;
    }

    public void reativar() {
        this.ativo = true;
    }

}
