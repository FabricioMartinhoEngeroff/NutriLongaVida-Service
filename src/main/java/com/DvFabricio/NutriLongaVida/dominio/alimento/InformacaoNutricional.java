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
@Table(name = "informacoesNutricionais")
@Entity(name = "InformacaoNutricional")
public class InformacaoNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double calorias;
    private double proteinas;
    private double carboidratos;
    private double gorduras;
    private double fibras;
    private double sodio;
}
