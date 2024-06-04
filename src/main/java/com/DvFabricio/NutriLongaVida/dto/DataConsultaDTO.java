package com.DvFabricio.NutriLongaVida.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataConsultaDTO(Long id, LocalDate data, LocalTime horarioInicio, boolean disponivel) {
}
