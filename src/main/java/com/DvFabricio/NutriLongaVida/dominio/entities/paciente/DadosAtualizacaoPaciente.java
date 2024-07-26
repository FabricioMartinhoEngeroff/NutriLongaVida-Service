package com.DvFabricio.NutriLongaVida.dominio.entities.paciente;

import com.DvFabricio.NutriLongaVida.dominio.endereco.DadosEndereco;
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
