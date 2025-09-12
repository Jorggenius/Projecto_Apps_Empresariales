package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.EvaluacionCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.EvaluacionDTO;
import com.schoolDays.noche_app.businessLayer.dto.EvaluacionUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.EvaluacionEntity;
import com.schoolDays.noche_app.persistenceLayer.entity.ModuloEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface EvaluacionMapper {

    // ===== Entity -> DTO (LECTURA ENRIQUECIDA) =====
    @Mapping(target = "moduloId",     source = "modulo.id")
    @Mapping(target = "moduloTitulo", source = "modulo.titulo")
    EvaluacionDTO toDTO(EvaluacionEntity entity);

    List<EvaluacionDTO> toDTOList(List<EvaluacionEntity> entities);

    // ===== CreateDTO -> Entity (CREAR) =====
    // Mapea moduloId -> modulo (entidad placeholder por ID)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modulo", source = "moduloId", qualifiedByName = "moduloFromId")
    // Quita estas dos líneas si tu entidad NO tiene auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    EvaluacionEntity toEntity(EvaluacionCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    // No permite cambiar el módulo en update (regla de negocio)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modulo", ignore = true)
    // Quita si no tienes auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(EvaluacionUpdateDTO dto, @MappingTarget EvaluacionEntity entity);

    // ===== DTO -> Entity (opcional: útil en tests) =====
    @Mapping(target = "modulo", source = "moduloId", qualifiedByName = "moduloFromId")
    // Quita si no tienes auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    EvaluacionEntity toEntity(EvaluacionDTO dto);

    // ===== Helper: construir ModuloEntity por ID (sin ir a la BD) =====
    @Named("moduloFromId")
    default ModuloEntity moduloFromId(Long id) {
        if (id == null) return null;
        ModuloEntity m = new ModuloEntity();
        m.setId(id);
        return m;
    }
}