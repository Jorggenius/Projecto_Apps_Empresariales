package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.RespuestaCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.RespuestaDTO;
import com.schoolDays.noche_app.businessLayer.dto.RespuestaUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.EvaluacionEntity;
import com.schoolDays.noche_app.persistenceLayer.entity.RespuestaEntity;
import com.schoolDays.noche_app.persistenceLayer.entity.UsuarioEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface RespuestaMapper {

    // ===== Entity -> DTO (LECTURA ENRIQUECIDA) =====
    @Mapping(target = "evaluacionId",  source = "evaluacion.id")
    @Mapping(target = "usuarioId",     source = "usuario.id")
    @Mapping(target = "usuarioNombre", source = "usuario.nombre")
    RespuestaDTO toDTO(RespuestaEntity entity);

    List<RespuestaDTO> toDTOList(List<RespuestaEntity> entities);

    // ===== CreateDTO -> Entity (CREAR) =====
    // Mapea ids a entidades relacionadas usando helpers @Named
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evaluacion", source = "evaluacionId", qualifiedByName = "evaluacionFromId")
    @Mapping(target = "usuario",    source = "usuarioId",    qualifiedByName = "usuarioFromId")
    // Quita estas dos líneas si tu entidad NO tiene auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RespuestaEntity toEntity(RespuestaCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    // No permite cambiar evaluación/usuario en update (regla de negocio)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evaluacion", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    // Quita si no usas auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(RespuestaUpdateDTO dto, @MappingTarget RespuestaEntity entity);

    // ===== DTO -> Entity (opcional: útil en tests) =====
    @Mapping(target = "evaluacion", source = "evaluacionId", qualifiedByName = "evaluacionFromId")
    @Mapping(target = "usuario",    source = "usuarioId",    qualifiedByName = "usuarioFromId")
    // Quita si no usas auditoría:
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RespuestaEntity toEntity(RespuestaDTO dto);

    // ===== Helpers: construir entidades por ID (sin ir a la BD) =====
    @Named("evaluacionFromId")
    default EvaluacionEntity evaluacionFromId(Long id) {
        if (id == null) return null;
        EvaluacionEntity e = new EvaluacionEntity();
        e.setId(id);
        return e;
    }

    @Named("usuarioFromId")
    default UsuarioEntity usuarioFromId(Long id) {
        if (id == null) return null;
        UsuarioEntity u = new UsuarioEntity();
        u.setId(id);
        return u;
    }
}