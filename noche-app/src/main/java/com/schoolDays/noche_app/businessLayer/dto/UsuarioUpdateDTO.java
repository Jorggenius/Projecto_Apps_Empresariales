package com.schoolDays.noche_app.businessLayer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para ACTUALIZAR parcialmente un usuario.
 * - Todos los campos opcionales (null -> no se actualiza).
 * - NO incluye email (política: no se cambia por este endpoint).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para actualizar parcialmente un usuario")
public class UsuarioUpdateDTO {

    @Schema(description = "Nombre completo del usuario", example = "María González", maxLength = 100)
    private String nombre;

    @Schema(description = "Rol del usuario en el sistema", example = "INSTRUCTOR", maxLength = 50,
            allowableValues = {"ADMIN", "INSTRUCTOR", "USER"})
    private String rol;

    @Schema(description = "Departamento/área del usuario", example = "Talento Humano", maxLength = 100)
    private String departamento;
}
