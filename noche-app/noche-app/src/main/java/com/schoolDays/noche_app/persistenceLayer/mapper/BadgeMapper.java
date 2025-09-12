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
 * - BadgeDTO: lectura/respuestas completas.
 * - BadgeCreateDTO: creación (ignora id).
 * - BadgeUpdateDTO: actualización parcial (null -> IGNORE).
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface BadgeMapper {

    /** Convierte BadgeEntity a BadgeDTO (LECTURA) */
    BadgeDTO toDTO(BadgeEntity entity);

    /** Convierte lista de BadgeEntity a lista de BadgeDTO */
    List<BadgeDTO> toDTOList(List<BadgeEntity> entities);

    /** Convierte BadgeCreateDTO a BadgeEntity (CREAR) */
    @Mapping(target = "id", ignore = true)
    BadgeEntity toEntity(BadgeCreateDTO createDTO);

    /**
     * Actualiza un BadgeEntity existente con datos de BadgeUpdateDTO
     * - id se ignora (no se puede cambiar)
     * - nulls no pisan (actualización parcial)
     */
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(BadgeUpdateDTO updateDTO, @MappingTarget BadgeEntity entity);

    /** Opcional: DTO completo a Entity (útil en tests/casos especiales) */
    BadgeEntity toEntity(BadgeDTO dto);
}
