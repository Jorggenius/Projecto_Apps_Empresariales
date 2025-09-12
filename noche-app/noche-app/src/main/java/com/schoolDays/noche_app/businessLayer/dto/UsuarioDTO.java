package com.schoolDays.noche_app.businessLayer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO COMPLETO de usuarios - Para LECTURA/RESPUESTAS
 *
 * ¿POR QUÉ ESTE DTO?
 * - Se usa cuando DEVOLVEMOS información completa al cliente
 * - Incluye el ID (solo lectura) y todos los campos de la entidad
 *
 * USO: GET /usuarios/{id}, GET /usuarios, respuestas de POST/PUT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información del usuario")
public class UsuarioDTO {

    @Schema(description = "ID único del usuario",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre completo del usuario",
            example = "María González",
            required = true,
            maxLength = 100)
    private String nombre;

    @Schema(description = "Correo electrónico único del usuario",
            example = "maria.gonzalez@empresa.com",
            required = true,
            maxLength = 150)
    private String email;

    @Schema(description = "Rol del usuario en el sistema",
            example = "INSTRUCTOR",
            required = true,
            maxLength = 50,
            allowableValues = {"ADMIN", "INSTRUCTOR", "USER"})
    private String rol;

    @Schema(description = "Departamento/área del usuario",
            example = "Talento Humano",
            maxLength = 100)
    private String departamento;
}