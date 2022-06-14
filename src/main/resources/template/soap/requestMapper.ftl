package ${PROJECT_PACKAGE}.${MAPPER_PACKAGE}.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ${PROJECT_PACKAGE}.${DTO_PACKAGE}.${ENTITY_NAME_UPPER}Dto;
import ${PROJECT_PACKAGE}.${DTO_PACKAGE}.xml.*;

@Mapper(componentModel = "spring")
public interface ${ENTITY_NAME_UPPER}RequestMapper {

    ${ENTITY_NAME_UPPER}Dto getRequestToDto(Get${ENTITY_NAME_UPPER}Request request);

    ${ENTITY_NAME_UPPER}Dto saveRequestToDto(Save${ENTITY_NAME_UPPER}Request request);

    ${ENTITY_NAME_UPPER}Dto updateRequestToDto(Update${ENTITY_NAME_UPPER}Request request);

    ${ENTITY_NAME_UPPER}Dto deleteRequestToDto(Delete${ENTITY_NAME_UPPER}Request request);

}