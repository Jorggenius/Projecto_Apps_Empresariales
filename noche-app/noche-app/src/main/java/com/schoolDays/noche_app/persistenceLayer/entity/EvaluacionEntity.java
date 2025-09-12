package com.schoolDays.noche_app.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Entidad que representa la tabla evaluaciones
 */
@Entity
@Table(name = "evaluaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Modulo
    @Column(name = "modulo_id", nullable = false)
    private Long moduloId;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 50)
    private String tipo; // Ej: MCQ, Abierta, Mixta

    @Column(name = "puntaje_max", nullable = false, precision = 5, scale = 2)
    private BigDecimal puntajeMax;

    public void setId(Long id) {

    }
}
