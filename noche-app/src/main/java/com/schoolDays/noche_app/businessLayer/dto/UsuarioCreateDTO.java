package com.schoolDays.noche_app.businessLayer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para CREAR usuarios - Solo campos que el cliente debe proporcionar
 *
 * ¿POR QUÉ SEPARAR CREATE DEL DTO COMPLETO?
 * 1. SEGURIDAD: Cliente NO puede enviar ID (lo genera la BD)
 * 2. SIMPLICIDAD: Solo lo esencial para crear el usuario
 * 3. VALIDACIÓN: Campos obligatorios claramente definidos
 *
 * CAMPOS INCLUIDOS:
 * - nombre, email, rol, departamento (opcional)
 *
 * CAMPOS EXCLUIDOS:
 * - id: Lo genera la base de datos
 *
 * USO: POST /usuarios
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear un nuevo usuario")
public class UsuarioCreateDTO {

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