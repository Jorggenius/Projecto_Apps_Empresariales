package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.CertificadoCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.CertificadoDTO;
import com.schoolDays.noche_app.businessLayer.dto.CertificadoUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.CertificadoEntity;
import com.schoolDays.noche_app.persistenceLayer.entity.CursoEntity;
import com.schoolDays.noche_app.persistenceLayer.entity.UsuarioEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CertificadoMapper {

    // ===== Entity -> DTO (LECTURA ENRIQUECIDA) =====
    @Mapping(target = "usuarioId",     source = "usuario.id")
    @Mapping(target = "usuarioNombre", source = "usuario.nombre")
    @Mapping(target = "cursoId",       source = "curso.id")
    @Mapping(target = "cursoTitulo",   source = "curso.titulo")
    CertificadoDTO toDTO(CertificadoEntity entity);

    List<CertificadoDTO> toDTOList(List<CertificadoEntity> entities);

    // ===== CreateDTO -> Entity (CREAR) =====
    // Mapea ids a entidades relacionadas usando helpers @Named
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = "usuarioFromId")
    @Mapping(target = "curso",   source = "cursoId",   qualifiedByName = "cursoFromId")
    CertificadoEntity toEntity(CertificadoCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    // No permite cambiar usuario/curso en update (regla de negocio)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(CertificadoUpdateDTO dto, @MappingTarget CertificadoEntity entity);

    // ===== DTO -> Entity (opcional: útil en tests/conversión puntual) =====
    @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = "usuarioFromId")
    @Mapping(target = "curso",   source = "cursoId",   qualifiedByName = "cursoFromId")
    CertificadoEntity toEntity(CertificadoDTO dto);

    // ===== Helpers: construir entidades por ID (sin hit a BD) =====
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

    // (Opcional) extractores si los necesitas en otros mapeos
    @Named("extractUsuarioId")
    default Long extractUsuarioId(UsuarioEntity u) {
        return u != null ? u.getId() : null;
    }

    @Named("extractCursoId")
    default Long extractCursoId(CursoEntity c) {
        return c != null ? c.getId() : null;
    }
}