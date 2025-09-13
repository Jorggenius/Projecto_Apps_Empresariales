package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.ModuloCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.ModuloDTO;
import com.schoolDays.noche_app.businessLayer.dto.ModuloUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.ModuloEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModuloMapper {

    // ===== Entity -> DTO =====
    // cursoTitulo NO se puede poblar aquí porque no existe en la entidad.
    ModuloDTO toDTO(ModuloEntity entity);
    List<ModuloDTO> toDTOList(List<ModuloEntity> entities);

    // ===== CreateDTO -> Entity =====
    @Mapping(target = "id", ignore = true)
    ModuloEntity toEntity(ModuloCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    // Si NO quieres permitir cambiar el curso del módulo, ignora cursoId:
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cursoId", ignore = true) // quita esta línea si SÍ quieres permitir cambiar el curso
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ModuloUpdateDTO dto, @MappingTarget ModuloEntity entity);
}