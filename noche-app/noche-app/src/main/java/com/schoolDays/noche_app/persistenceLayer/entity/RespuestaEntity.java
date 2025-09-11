package com.schoolDays.noche_app.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad que representa la tabla respuestas
 */
@Entity
@Table(name = "respuestas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Evaluación
    @Column(name = "evaluacion_id", nullable = false)
    private Long evaluacionId;

    // Relación con Usuario
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal puntuacion;

    @Column(nullable = false)
    private LocalDate fecha;
}