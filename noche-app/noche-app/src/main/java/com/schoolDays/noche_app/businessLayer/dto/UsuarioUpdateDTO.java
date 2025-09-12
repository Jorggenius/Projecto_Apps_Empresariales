package com.schoolDays.noche_app.businessLayer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para ACTUALIZAR usuarios - Solo campos modificables
 *
 * ¿POR QUÉ SEPARAR UPDATE DEL CREATE?
 * 1. FLEXIBILIDAD: Todos los campos son OPCIONALES (permite actualización parcial)
 * 2. LÓGICA DE NEGOCIO: Email NO se cambia con este DTO (identificador de usuario)
 * 3. SEGURIDAD: Evita cambios no autorizados de email
 *
 * CAMPOS INCLUIDOS:
 * - nombre, rol, departamento (todos opcionales)
 *
 * CAMPOS EXCLUIDOS:
 * - id: Inmutable
 * - email: Política de negocio: no se cambia con este endpoint
 *
 * USO: PUT/PATCH /usuarios/{id}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para actualizar un usuario existente")
public class UsuarioUpdateDTO {

    @Schema(description = "Nombre completo del usuario",
            example = "María G. Rodríguez",
            maxLength = 100)
    private String nombre;

    @Schema(description = "Nuevo rol del usuario",
            example = "ADMIN",
            maxLength = 50,
            allowableValues = {"ADMIN", "INSTRUCTOR", "USER"})
    private String rol;

    @Schema(description = "Departamento/área del usuario",
            example = "Formación y Desarrollo",
            maxLength = 100)
    private String departamento;
}