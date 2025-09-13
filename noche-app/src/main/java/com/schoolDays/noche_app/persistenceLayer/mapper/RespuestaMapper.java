package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.RespuestaCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.RespuestaDTO;
import com.schoolDays.noche_app.businessLayer.dto.RespuestaUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.EvaluacionEntity;
import com.schoolDays.noche_app.persistenceLayer.entity.RespuestaEntity;
import com.schoolDays.noche_app.persistenceLayer.entity.UsuarioEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RespuestaMapper {

    // Entity -> DTO
    RespuestaDTO toDTO(RespuestaEntity entity);
    List<RespuestaDTO> toDTOList(List<RespuestaEntity> entities);

    // CreateDTO -> Entity
    @Mapping(target = "id", ignore = true)
    RespuestaEntity toEntity(RespuestaCreateDTO dto);

    // Update parcial: no permitas cambiar FKs si es tu regla
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evaluacionId", ignore = true)
    @Mapping(target = "usuarioId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(RespuestaUpdateDTO dto, @MappingTarget RespuestaEntity entity);
}