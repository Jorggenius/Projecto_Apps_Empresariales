package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.UsuarioCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.UsuarioDTO;
import com.schoolDays.noche_app.businessLayer.dto.UsuarioUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.UsuarioEntity;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper para conversiones entre UsuarioEntity y sus DTOs usando MapStruct.
 *
 * - UsuarioDTO: lectura/respuesta completa.
 * - UsuarioCreateDTO: creación (ignora id).
 * - UsuarioUpdateDTO: actualización parcial (IGNORA nulls) y NO permite cambiar email.
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface UsuarioMapper {

    // ===== Entity -> DTO (LECTURA) =====
    UsuarioDTO toDTO(UsuarioEntity entity);

    List<UsuarioDTO> toDTOList(List<UsuarioEntity> entities);

    // ===== CreateDTO -> Entity (CREAR) =====
    @Mapping(target = "id", ignore = true)
    // Quita estas dos líneas si tu entidad NO tiene auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UsuarioEntity toEntity(UsuarioCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    // Regla de negocio: NO se cambia el email por este endpoint
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    // Quita si no usas auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UsuarioUpdateDTO dto, @MappingTarget UsuarioEntity entity);

    // ===== DTO -> Entity (opcional: útil en tests) =====
    // Mantén email del DTO si lo necesitas en pruebas; ignora auditoría si no existe
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UsuarioEntity toEntity(UsuarioDTO dto);
}
