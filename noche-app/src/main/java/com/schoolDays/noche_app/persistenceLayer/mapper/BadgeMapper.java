package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.BadgeCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.BadgeDTO;
import com.schoolDays.noche_app.businessLayer.dto.BadgeUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.BadgeEntity;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper para conversiones entre BadgeEntity y sus DTOs usando MapStruct
 *
 * - BadgeDTO: para lectura/respuestas completas.
 * - BadgeCreateDTO: para creación (ignora id).
 * - BadgeUpdateDTO: actualización parcial (ignora nulls).
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BadgeMapper {

    // ===== Entity -> DTO =====
    BadgeDTO toDTO(BadgeEntity entity);

    List<BadgeDTO> toDTOList(List<BadgeEntity> entities);

    // ===== CreateDTO -> Entity =====
    @Mapping(target = "id", ignore = true)
    BadgeEntity toEntity(BadgeCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(BadgeUpdateDTO dto, @MappingTarget BadgeEntity entity);

    // ===== DTO -> Entity (útil en tests o casos especiales) =====
    BadgeEntity toEntity(BadgeDTO dto);
}