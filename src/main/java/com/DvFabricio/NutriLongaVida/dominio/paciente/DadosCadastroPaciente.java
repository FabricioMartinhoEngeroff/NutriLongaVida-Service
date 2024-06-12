package com.DvFabricio.NutriLongaVida.dominio.paciente;

import com.DvFabricio.NutriLongaVida.dominio.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPaciente(
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

        @NotBlank(message = "Dados do endereço são obrigatórios")
        DadosEndereco endereco) {
}
