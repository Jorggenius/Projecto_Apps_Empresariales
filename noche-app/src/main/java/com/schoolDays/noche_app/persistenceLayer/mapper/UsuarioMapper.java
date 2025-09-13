package com.schoolDays.noche_app.persistenceLayer.mapper;

import com.schoolDays.noche_app.businessLayer.dto.UsuarioCreateDTO;
import com.schoolDays.noche_app.businessLayer.dto.UsuarioDTO;
import com.schoolDays.noche_app.businessLayer.dto.UsuarioUpdateDTO;
import com.schoolDays.noche_app.persistenceLayer.entity.UsuarioEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UsuarioMapper {

    // ===== Entity -> DTO =====
    UsuarioDTO toDTO(UsuarioEntity entity);
    List<UsuarioDTO> toDTOList(List<UsuarioEntity> entities);

    // ===== CreateDTO -> Entity =====
    @Mapping(target = "id", ignore = true)
    UsuarioEntity toEntity(UsuarioCreateDTO dto);

    // ===== UpdateDTO -> Entity (PATCH parcial) =====
    // Regla: NO se cambia el email en update
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UsuarioUpdateDTO dto, @MappingTarget UsuarioEntity entity);

    // ===== DTO completo -> Entity (útil en tests o mapeos internos) =====
    // Mantiene el id del DTO si viene seteado.
    UsuarioEntity toEntity(UsuarioDTO dto);
}
