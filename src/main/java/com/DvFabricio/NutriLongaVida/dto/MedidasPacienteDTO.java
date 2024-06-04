package com.DvFabricio.NutriLongaVida.dto;

import java.time.LocalDate;

public record MedidasPacienteDTO(Long id, LocalDate dataMedicao, double peso, double altura,
                                 double circunferenciaCintura, double circunferenciaQuadril,
                                 double percentualGorduraCorpora, double massaMuscular, double indiceMassaCorporal) {
}
