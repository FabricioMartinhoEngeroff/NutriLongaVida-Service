package com.DvFabricio.NutriLongaVida.dominio.entities.nutricionista;

import com.DvFabricio.NutriLongaVida.dominio.endereco.DadosEndereco;
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

