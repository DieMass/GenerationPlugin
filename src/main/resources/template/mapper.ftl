package ${PROJECT_PACKAGE}.${MAPPER_PACKAGE};

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ${PROJECT_PACKAGE}.${DTO_PACKAGE}.${ENTITY_NAME_UPPER}Dto;
import ${PROJECT_PACKAGE}.${ENTITY_PACKAGE}.${ENTITY_NAME_UPPER};

@Mapper(componentModel = "spring")
public interface ${ENTITY_NAME_UPPER}Mapper {

    ${ENTITY_NAME_UPPER}Dto entityToDto(${ENTITY_NAME_UPPER} entity);

    ${ENTITY_NAME_UPPER} dtoToEntity(${ENTITY_NAME_UPPER}Dto dto);

    void merge(${ENTITY_NAME_UPPER}Dto dto, @MappingTarget ${ENTITY_NAME_UPPER} entity);

}