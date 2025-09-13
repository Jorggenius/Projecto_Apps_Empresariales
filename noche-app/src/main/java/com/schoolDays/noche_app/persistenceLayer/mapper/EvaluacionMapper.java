package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.EvaluacionCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.EvaluacionDTO;
import com.schoolDays.noche_app.businessLayer.dto.EvaluacionUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.EvaluacionEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EvaluacionMapper {

    // ===== Entity -> DTO =====
    // NOTA: moduloTitulo NO se puede poblar aquí (la entidad solo tiene moduloId)
    EvaluacionDTO toDTO(EvaluacionEntity entity);
    List<EvaluacionDTO> toDTOList(List<EvaluacionEntity> entities);

    // ===== CreateDTO -> Entity =====
    @Mapping(target = "id", ignore = true)
    EvaluacionEntity toEntity(EvaluacionCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    // Si NO quieres permitir cambiar el módulo de una evaluación, ignora moduloId:
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "moduloId", ignore = true) // quita esta línea si SÍ permites cambiar de módulo
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(EvaluacionUpdateDTO dto, @MappingTarget EvaluacionEntity entity);

    // (Opcional) DTO completo -> Entity (útil en tests)
    @Mapping(target = "id", ignore = true)
    EvaluacionEntity toEntity(EvaluacionDTO dto);
}