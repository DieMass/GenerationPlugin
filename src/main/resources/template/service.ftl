package ${PROJECT_PACKAGE}.${SERVICE_PACKAGE};

import ${PROJECT_PACKAGE}.${DTO_PACKAGE}.${ENTITY_NAME_UPPER}Dto;

public interface ${ENTITY_NAME_UPPER}Service {

    ${ENTITY_NAME_UPPER}Dto find(Long id);
    Long save(${ENTITY_NAME_UPPER}Dto dto);
    ${ENTITY_NAME_UPPER}Dto update(${ENTITY_NAME_UPPER}Dto dto);
    ${ENTITY_NAME_UPPER}Dto delete(Long id);
}