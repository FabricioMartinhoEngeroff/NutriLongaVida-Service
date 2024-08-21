package com.DvFabricio.NutriLongaVida.core.nutricionista.domain;

import com.DvFabricio.NutriLongaVida.core.endereco.Endereco;
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

    public Nutricionista(NutricionistaDTO dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.registroProfissionalCrm = dto.registroProfissionalCrm();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.especialidade = dto.especialidade();
        this.endereco = new Endereco(dto.endereco());
        this.ativo = true;
    }

    public void excluir() {
        this.ativo = false;
    }

    public void reativar() {
        this.ativo = true;
    }
}
