package com.schoolDays.noche_app.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad que representa la tabla inscripciones
 */
@Entity
@Table(name = "inscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Usuario
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    // Relación con Curso
    @Column(name = "curso_id", nullable = false)
    private Long cursoId;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal progreso; // Ejemplo: 75.50 %

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDate fechaInscripcion;

    @Column(nullable = false, length = 50)
    private String estado; // Ej: Inscrito, En progreso, Completado
}