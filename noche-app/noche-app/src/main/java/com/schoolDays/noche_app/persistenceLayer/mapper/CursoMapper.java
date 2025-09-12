package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.CursoCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.CursoDTO;
import com.schoolDays.noche_app.businessLayer.dto.CursoUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.CursoEntity;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper para conversiones entre CursoEntity y sus DTOs usando MapStruct.
 *
 * - CursoDTO: lectura/respuesta completa.
 * - CursoCreateDTO: creación (ignora id y auditoría).
 * - CursoUpdateDTO: actualización parcial (IGNORA nulls).
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CursoMapper {

    // ===== Entity -> DTO (LECTURA) =====
    CursoDTO toDTO(CursoEntity entity);

    List<CursoDTO> toDTOList(List<CursoEntity> entities);

    // ===== CreateDTO -> Entity (CREAR) =====
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)   // <- elimínala si tu entidad no lo tiene
    @Mapping(target = "updatedAt", ignore = true)   // <- idem
    CursoEntity toEntity(CursoCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)   // <- opcional según tu entidad
    @Mapping(target = "updatedAt", ignore = true)   // <- opcional según tu entidad
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(CursoUpdateDTO dto, @MappingTarget CursoEntity entity);

    // ===== DTO -> Entity (opcional: útil en tests) =====
    @Mapping(target = "createdAt", ignore = true)   // <- opcional
    @Mapping(target = "updatedAt", ignore = true)   // <- opcional
    CursoEntity toEntity(CursoDTO dto);
}
