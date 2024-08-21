package com.DvFabricio.NutriLongaVida.core.paciente.domain;

import com.DvFabricio.NutriLongaVida.core.endereco.DadosEndereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacienteDTO(

        Long id,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato do email é inválido")
        String email,

        @NotBlank(message = "CPF é obrigatório")
        String cpf,

        @NotBlank(message = "Data de nascimento é obrigatória")
        String dataNascimento,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotBlank(message = "Gênero é obrigatório")
        String genero,

        @NotNull(message = "Dados do endereço são obrigatórios")
        DadosEndereco endereco) {
}
