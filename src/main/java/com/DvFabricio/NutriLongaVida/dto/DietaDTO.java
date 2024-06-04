package com.DvFabricio.NutriLongaVida.dto;

import java.time.LocalDate;

public record DietaDTO(Long id, LocalDate dataInicio, LocalDate dataFim,
                       String observacoes) {
}
