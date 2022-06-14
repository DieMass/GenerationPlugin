package ${PROJECT_PACKAGE}.${MAPPER_PACKAGE}.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ${PROJECT_PACKAGE}.${DTO_PACKAGE}.${ENTITY_NAME_UPPER}Dto;
import ${PROJECT_PACKAGE}.${DTO_PACKAGE}.xml.*;

@Mapper(componentModel = "spring")
public interface ${ENTITY_NAME_UPPER}ResponseMapper {

    Get${ENTITY_NAME_UPPER}Response dtoToGetResponse(${ENTITY_NAME_UPPER}Dto dto);

    Save${ENTITY_NAME_UPPER}Response dtoToSaveResponse(${ENTITY_NAME_UPPER}Dto dto);

    Update${ENTITY_NAME_UPPER}Response dtoToUpdateResponse(${ENTITY_NAME_UPPER}Dto dto);

    Delete${ENTITY_NAME_UPPER}Response dtoToDeleteResponse(${ENTITY_NAME_UPPER}Dto dto);

}