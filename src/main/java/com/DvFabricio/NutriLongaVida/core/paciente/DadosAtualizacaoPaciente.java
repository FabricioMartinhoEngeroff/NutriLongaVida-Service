package com.DvFabricio.NutriLongaVida.core.paciente;

import com.DvFabricio.NutriLongaVida.core.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(

        @NotNull
        Long id,

        String nome,

        String email,

        String telefone,

        DadosEndereco dadosEndereco
) {
}
