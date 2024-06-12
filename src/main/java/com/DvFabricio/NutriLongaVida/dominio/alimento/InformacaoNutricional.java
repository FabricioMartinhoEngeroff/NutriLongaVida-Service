package com.DvFabricio.NutriLongaVida.dominio.alimento;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
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
    private double unidade;

    public double getCaloriasPorUnidade() {
        if (unidade == 0) {
            throw new IllegalArgumentException("Unidade não pode ser zero.");
        }
        return calorias / unidade;
    }

}
