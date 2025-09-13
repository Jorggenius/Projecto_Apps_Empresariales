package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.CertificadoCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.CertificadoDTO;
import com.schoolDays.noche_app.businessLayer.dto.CertificadoUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.CertificadoEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CertificadoMapper {

    // ===== Entity -> DTO =====
    // Nota: usuarioNombre y cursoTitulo NO pueden poblarse aquí porque la entidad solo tiene IDs.
    CertificadoDTO toDTO(CertificadoEntity entity);
    List<CertificadoDTO> toDTOList(List<CertificadoEntity> entities);

    // ===== CreateDTO -> Entity =====
    @Mapping(target = "id", ignore = true)
    CertificadoEntity toEntity(CertificadoCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    // Si NO quieres permitir cambiar las FKs en update, ignóralas:
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuarioId", ignore = true)
    @Mapping(target = "cursoId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(CertificadoUpdateDTO dto, @MappingTarget CertificadoEntity entity);

    // (Opcional) DTO completo -> Entity, útil en tests
    @Mapping(target = "id", ignore = true)
    CertificadoEntity toEntity(CertificadoDTO dto);
}