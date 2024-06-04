package com.DvFabricio.NutriLongaVida.dto;

import com.DvFabricio.NutriLongaVida.dominio.alimento.InformacaoNutricional;

public record ItemDietaDTO(Long id, String nomeAlimento, double quantidade, String unidadeMedida, int frequenciaConsumo,
                           String categoriaAlimento, String substituicoes, String observacoes, InformacaoNutricional informacaoNutricional) {
}
