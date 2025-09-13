package com.schoolDays.noche_app.businessLayer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información completa de un módulo")
public class ModuloDTO {

    @Schema(description = "ID único del módulo",
            example = "10",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "ID del curso al que pertenece el módulo",
            example = "3")
    private Long cursoId;

    @Schema(description = "Título del módulo",
            example = "Introducción a Java", maxLength = 150)
    private String titulo;

    @Schema(description = "Tipo de contenido del módulo",
            example = "Quiz",
            allowableValues = {"Video", "Texto", "PDF", "Quiz", "Práctica"})
    private String tipo;

    @Schema(description = "Orden del módulo dentro del curso",
            example = "2")
    private Integer orden;

    @Schema(description = "Título del curso asociado",
            example = "Curso de Programación en Java",
            accessMode = Schema.AccessMode.READ_ONLY)
    private String cursoTitulo;
}