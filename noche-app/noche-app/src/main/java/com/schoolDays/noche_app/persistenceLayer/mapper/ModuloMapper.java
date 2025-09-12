package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.ModuloCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.ModuloDTO;
import com.schoolDays.noche_app.businessLayer.dto.ModuloUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.CursoEntity;
import com.schoolDays.noche_app.persistenceLayer.entity.ModuloEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface ModuloMapper {

    // ===== Entity -> DTO (LECTURA ENRIQUECIDA) =====
    @Mapping(target = "cursoId",     source = "curso.id")
    @Mapping(target = "cursoTitulo", source = "curso.titulo")
    ModuloDTO toDTO(ModuloEntity entity);

    List<ModuloDTO> toDTOList(List<ModuloEntity> entities);

    // ===== CreateDTO -> Entity (CREAR) =====
    // cursoId -> curso (entidad placeholder por ID)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", source = "cursoId", qualifiedByName = "cursoFromId")
    // Quita estas dos líneas si tu entidad NO tiene auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ModuloEntity toEntity(ModuloCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    // No se permite cambiar el curso en update (regla de negocio)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", ignore = true)
    // Quita si no usas auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ModuloUpdateDTO dto, @MappingTarget ModuloEntity entity);

    // ===== DTO -> Entity (opcional: útil en tests) =====
    @Mapping(target = "curso", source = "cursoId", qualifiedByName = "cursoFromId")
    // Quita si no usas auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ModuloEntity toEntity(ModuloDTO dto);

    // ===== Helper: construir CursoEntity por ID (sin ir a la BD) =====
    @Named("cursoFromId")
    default CursoEntity cursoFromId(Long id) {
        if (id == null) return null;
        CursoEntity c = new CursoEntity();
        c.setId(id);
        return c;
    }
}