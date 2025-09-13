package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.CursoCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.CursoDTO;
import com.schoolDays.noche_app.businessLayer.dto.CursoUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.CursoEntity;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper para conversiones entre CursoEntity y sus DTOs usando MapStruct.
 * - CursoDTO: lectura/respuesta completa.
 * - CursoCreateDTO: creación (ignora id).
 * - CursoUpdateDTO: actualización parcial (ignora nulls).
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CursoMapper {

    // ===== Entity -> DTO =====
    CursoDTO toDTO(CursoEntity entity);
    List<CursoDTO> toDTOList(List<CursoEntity> entities);

    // ===== CreateDTO -> Entity =====
    @Mapping(target = "id", ignore = true)
    CursoEntity toEntity(CursoCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(CursoUpdateDTO dto, @MappingTarget CursoEntity entity);

    // ===== DTO -> Entity (útil en tests) =====
    @Mapping(target = "id", ignore = true) // evita sobreescribir id por accidente
    CursoEntity toEntity(CursoDTO dto);
}