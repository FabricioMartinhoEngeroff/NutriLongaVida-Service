package com.DvFabricio.NutriLongaVida.dto;


public record PacienteDTO(
        Long id,
        String nome,

        String dataNascimento,
        String email,
        String telefone,
        String endereco,
        String genero

) {
}
