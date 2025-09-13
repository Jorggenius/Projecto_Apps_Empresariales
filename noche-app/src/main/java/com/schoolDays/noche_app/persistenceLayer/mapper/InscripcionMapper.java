package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.InscripcionCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.InscripcionDTO;
import com.schoolDays.noche_app.businessLayer.dto.InscripcionUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.InscripcionEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InscripcionMapper {

    // ===== Entity -> DTO =====
    // (usuarioNombre y cursoTitulo NO pueden poblarse aquí; ver nota abajo)
    InscripcionDTO toDTO(InscripcionEntity entity);
    List<InscripcionDTO> toDTOList(List<InscripcionEntity> entities);

    // ===== CreateDTO -> Entity =====
    @Mapping(target = "id", ignore = true)
    InscripcionEntity toEntity(InscripcionCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    // Si NO quieres permitir cambiar usuario/curso, ignora sus FKs:
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuarioId", ignore = true)
    @Mapping(target = "cursoId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(InscripcionUpdateDTO dto, @MappingTarget InscripcionEntity entity);

    // (Opcional) DTO completo -> Entity (útil en tests)
    @Mapping(target = "id", ignore = true)
    InscripcionEntity toEntity(InscripcionDTO dto);
}