package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.InscripcionCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.InscripcionDTO;
import com.schoolDays.noche_app.businessLayer.dto.InscripcionUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.CursoEntity;
import com.schoolDays.noche_app.persistenceLayer.entity.InscripcionEntity;
import com.schoolDays.noche_app.persistenceLayer.entity.UsuarioEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface InscripcionMapper {

    // ===== Entity -> DTO (LECTURA ENRIQUECIDA) =====
    @Mapping(target = "usuarioId",     source = "usuario.id")
    @Mapping(target = "usuarioNombre", source = "usuario.nombre")
    @Mapping(target = "cursoId",       source = "curso.id")
    @Mapping(target = "cursoTitulo",   source = "curso.titulo")
    InscripcionDTO toDTO(InscripcionEntity entity);

    List<InscripcionDTO> toDTOList(List<InscripcionEntity> entities);

    // ===== CreateDTO -> Entity (CREAR) =====
    // Mapea ids a entidades relacionadas usando helpers @Named
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = "usuarioFromId")
    @Mapping(target = "curso",   source = "cursoId",   qualifiedByName = "cursoFromId")
    // Quita si tu entidad NO tiene auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    InscripcionEntity toEntity(InscripcionCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    // No permite cambiar usuario/curso en update (regla de negocio)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "curso", ignore = true)
    // Quita si no usas auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(InscripcionUpdateDTO dto, @MappingTarget InscripcionEntity entity);

    // ===== DTO -> Entity (opcional: útil en tests) =====
    @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = "usuarioFromId")
    @Mapping(target = "curso",   source = "cursoId",   qualifiedByName = "cursoFromId")
    // Quita si no usas auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    InscripcionEntity toEntity(InscripcionDTO dto);

    // ===== Helpers: construir entidades por ID (sin ir a la BD) =====
    @Named("usuarioFromId")
    default UsuarioEntity usuarioFromId(Long id) {
        if (id == null) return null;
        UsuarioEntity u = new UsuarioEntity();
        u.setId(id);
        return u;
    }

    @Named("cursoFromId")
    default CursoEntity cursoFromId(Long id) {
        if (id == null) return null;
        CursoEntity c = new CursoEntity();
        c.setId(id);
        return c;
    }
}
