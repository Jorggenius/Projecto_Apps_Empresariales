package com.schoolDays.noche_app.businessLayer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información completa de una respuesta")
public class RespuestaDTO {

    @Schema(description = "ID único de la respuesta",
            example = "101", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "ID de la evaluación asociada", example = "5")
    private Long evaluacionId;

    @Schema(description = "ID del usuario que responde", example = "12")
    private Long usuarioId;

    @Schema(description = "Nombre del usuario que responde",
            example = "Carlos Ramírez", accessMode = Schema.AccessMode.READ_ONLY)
    private String usuarioNombre;

    @Schema(description = "Puntuación obtenida en la respuesta",
            example = "4.50")
    private BigDecimal puntuacion;

    @Schema(description = "Fecha en que se registró la respuesta",
            example = "2025-09-11")
    private LocalDate fecha;
}