package com.DvFabricio.NutriLongaVida.core.nutricionista.domain;

import com.DvFabricio.NutriLongaVida.core.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record NutricionistaDTO(

        Long id,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato do email é inválido")
        String email,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Formato do CPF é inválido")
        String cpf,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotNull(message = "CRM é obrigatório")
        @Pattern(regexp = "\\d{4,6}", message = "Formato do CRM é inválido")
        String registroProfissionalCrm,

        @NotNull(message = "Especialidade é obrigatória")
        Especialidade especialidade,

        @NotNull(message = "Dados do endereço são obrigatórios")
        @Valid DadosEndereco endereco) {

}
