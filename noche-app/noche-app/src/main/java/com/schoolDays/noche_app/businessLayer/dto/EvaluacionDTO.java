package com.schoolDays.noche_app.businessLayer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información completa de una evaluación")
public class EvaluacionDTO {

    @Schema(description = "ID único de la evaluación",
            example = "15",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "ID del módulo al que pertenece la evaluación",
            example = "7")
    private Long moduloId;

    @Schema(description = "Título del módulo asociado",
            example = "Módulo de Programación Orientada a Objetos",
            accessMode = Schema.AccessMode.READ_ONLY)
    private String moduloTitulo;

    @Schema(description = "Título de la evaluación",
            example = "Examen Final de Java")
    private String titulo;

    @Schema(description = "Tipo de evaluación",
            example = "Mixta",
            allowableValues = {"MCQ", "Abierta", "Mixta"})
    private String tipo;

    @Schema(description = "Puntaje máximo posible en la evaluación",
            example = "100.00")
    private BigDecimal puntajeMax;
}