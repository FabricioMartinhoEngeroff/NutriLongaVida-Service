package com.DvFabricio.NutriLongaVida.dto;

public record NutricionistaDTO(Long id,
                               String nome,
                               String cpf, String registroProfissionalCrm,
                               String email,
                               String telefone,
                               String endereco,
                               double valorConsulta) {
}
