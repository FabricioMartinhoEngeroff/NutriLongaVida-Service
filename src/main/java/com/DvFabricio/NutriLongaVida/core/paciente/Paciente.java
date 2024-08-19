package com.DvFabricio.NutriLongaVida.core.paciente;

import com.DvFabricio.NutriLongaVida.core.consultaAgendamento.Consulta;
import com.DvFabricio.NutriLongaVida.core.alimento.Dieta;
import com.DvFabricio.NutriLongaVida.core.endereco.Endereco;
import com.DvFabricio.NutriLongaVida.core.Pagamento.CartaoCredito;
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


    public void atualizarInformacoesPaciente(DadosAtualizacaoPaciente dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (endereco != null) {
            this.endereco.atualizarInformacaoEndereco(dados.dadosEndereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }

    public void reativar() {
        this.ativo = true;
    }

}
