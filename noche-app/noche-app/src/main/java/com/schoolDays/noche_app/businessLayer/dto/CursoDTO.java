package com.schoolDays.noche_app.businessLayer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información completa de un curso")
public class CursoDTO {

    @Schema(description = "ID único del curso",
            example = "10", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Título del curso",
            example = "Introducción a Spring Boot")
    private String titulo;

    @Schema(description = "Descripción detallada del curso",
            example = "Curso introductorio sobre el framework Spring Boot y sus principales características")
    private String descripcion;

    @Schema(description = "Duración estimada del curso en horas",
            example = "40")
    private Integer duracionEstim;

    @Schema(description = "Nivel de dificultad del curso",
            example = "Intermedio",
            allowableValues = {"Básico", "Intermedio", "Avanzado"})
    private String nivel;
}