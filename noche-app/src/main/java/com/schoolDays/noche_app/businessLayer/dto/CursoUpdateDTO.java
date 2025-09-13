package com.schoolDays.noche_app.businessLayer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para actualizar parcialmente un curso")
public class CursoUpdateDTO {

    @Schema(description = "Nuevo título del curso",
            example = "Spring Boot Profesional", maxLength = 150)
    private String titulo;

    @Schema(description = "Nueva descripción del curso",
            example = "Contenido actualizado con prácticas avanzadas")
    private String descripcion;

    @Schema(description = "Nueva duración estimada (horas)",
            example = "48")
    private Integer duracionEstim;

    @Schema(description = "Nuevo nivel de dificultad",
            example = "Avanzado",
            allowableValues = {"Básico", "Intermedio", "Avanzado"})
    private String nivel;
}