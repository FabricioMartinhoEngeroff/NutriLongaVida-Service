package com.DvFabricio.NutriLongaVida.dominio.nutricionista;

import com.DvFabricio.NutriLongaVida.dominio.objetosValor.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoNutricionista(

        @NotNull
        Long id,

        String nome,

        String email,

        String telefone,

        DadosEndereco dadosEndereco
) {
}

