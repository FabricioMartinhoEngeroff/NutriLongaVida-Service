package com.DvFabricio.NutriLongaVida.core.alimento;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "alimentos_da_dieta")
@Entity(name = "AlimentosDaDieta")
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Dieta dieta;

    @ManyToOne(fetch = FetchType.LAZY)
    private InformacaoNutricional informacaoNutricional;

    // Calcula as calorias com base na informação nutricional e quantidade
    public int getCalorias() {
        if (informacaoNutricional != null) {
            return (int) (informacaoNutricional.getCaloriasPorUnidade() * quantidade);
        }
        return 0;
    }

    // Define a dieta associada a este item
    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    public void ajustarQuantidadeParaMetaCalorica(double metaCaloricaDiaria) {
        if (informacaoNutricional != null && informacaoNutricional.getCaloriasPorUnidade() > 0) {
            quantidade = metaCaloricaDiaria / informacaoNutricional.getCaloriasPorUnidade();
        }
    }
}

