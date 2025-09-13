package com.schoolDays.noche_app.businessLayer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
@Schema(description = "Información completa de una respuesta")
public class RespuestaDTO {
    private Long id;
    private Long evaluacionId;
    private Long usuarioId;
    private BigDecimal puntuacion;
    private LocalDate fecha;
}