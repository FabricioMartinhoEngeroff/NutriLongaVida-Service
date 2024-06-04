package com.DvFabricio.NutriLongaVida.dominio.alimento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "itemsDieta")
@Entity(name = "ItemDieta")
public class AlimentosDaDieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeAlimento;
    private double quantidade;
    private String unidadeMedida;
    private int frequenciaConsumo;
    private String categoriaAlimento;
    private String substituicoes;
    private String observacoes;
    private InformacaoNutricional informacaoNutricional;
}
