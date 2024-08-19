package com.DvFabricio.NutriLongaVida.core.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosEndereco(
        @NotBlank(message = "Estado é obrigatório")
        String estado,

        @NotBlank(message = "Cidade é obrigatória")
        String cidade,

        @NotBlank(message = "Bairro é obrigatório")
        String bairro,

        @NotBlank(message = "Rua é obrigatória")
        String rua,

        @NotBlank(message = "CEP é obrigatório")
        String cep,

        @NotNull(message = "Número da casa é obrigatório")
        Integer numeroCasa,

        String complemento) {


}
