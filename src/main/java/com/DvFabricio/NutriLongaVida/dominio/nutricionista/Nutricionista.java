package com.DvFabricio.NutriLongaVida.dominio.nutricionista;

import com.DvFabricio.NutriLongaVida.dominio.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "nutricionistas")
@Entity(name = "Nutricionista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Nutricionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String registroProfissionalCrm;
    private String email;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Nutricionista(DadosCadastroNutricionista dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.registroProfissionalCrm = dados.registroProfissionalCrm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoesNutricionista(DadosAtualizacaoNutricionista dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (endereco != null) {
            this.endereco.atualizarInformaca(dados.dadosEndereco());
        }

    }
}
