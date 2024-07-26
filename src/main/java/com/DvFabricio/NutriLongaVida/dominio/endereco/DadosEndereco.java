package com.DvFabricio.NutriLongaVida.dominio.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosEndereco(
        @NotBlank(message = "Logradouro é obrigatório")
        String logradouro,

        @NotBlank(message = "Bairro é obrigatório")
        String bairro,

        @NotBlank(message = "CEP é obrigatório")
        String cep,

        @NotNull(message = "Número da casa é obrigatório")
        Integer numeroCasa,

        String complemento,

        @NotBlank(message = "Cidade é obrigatória")
        String cidade,

        @NotBlank(message = "País é obrigatório")
        String pais) {
}
