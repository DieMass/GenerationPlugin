package ${PROJECT_PACKAGE}.${SERVICE_PACKAGE}.impl;

import org.springframework.stereotype.Service;
import ${PROJECT_PACKAGE}.${ENTITY_PACKAGE}.${ENTITY_NAME_UPPER};
import ${PROJECT_PACKAGE}.${REPOSITORY_PACKAGE}.${ENTITY_NAME_UPPER}Repository;
import ${PROJECT_PACKAGE}.${MAPPER_PACKAGE}.${ENTITY_NAME_UPPER}Mapper;
import ${PROJECT_PACKAGE}.${DTO_PACKAGE}.${ENTITY_NAME_UPPER}Dto;
import ${PROJECT_PACKAGE}.${SERVICE_PACKAGE}.${ENTITY_NAME_UPPER}Service;
<#if USE_LOMBOK>
import lombok.RequiredArgsConstructor;
</#if>

@Service
<#if USE_LOMBOK>
@RequiredArgsConstructor
</#if>
public class ${ENTITY_NAME_UPPER}ServiceImpl implements ${ENTITY_NAME_UPPER}Service {

    private final ${ENTITY_NAME_UPPER}Repository ${ENTITY_NAME_LOWER}Repository;
    private final ${ENTITY_NAME_UPPER}Mapper ${ENTITY_NAME_LOWER}Mapper;

<#if !USE_LOMBOK>
    public ${ENTITY_NAME_UPPER}ServiceImpl(${ENTITY_NAME_UPPER}Repository ${ENTITY_NAME_LOWER}Repository, ${ENTITY_NAME_UPPER}Mapper ${ENTITY_NAME_LOWER}Mapper) {
    this.${ENTITY_NAME_LOWER}Repository = ${ENTITY_NAME_LOWER}Repository;
    this.${ENTITY_NAME_LOWER}Mapper = ${ENTITY_NAME_LOWER}Mapper;
    }
</#if>
    @Override
    public ${ENTITY_NAME_UPPER}Dto find(Long id) {
        return ${ENTITY_NAME_LOWER}Repository.findById(id).map(${ENTITY_NAME_LOWER}Mapper::entityToDto).get();
    }

    @Override
    public Long save(${ENTITY_NAME_UPPER}Dto dto) {
        return ${ENTITY_NAME_LOWER}Repository.save(${ENTITY_NAME_LOWER}Mapper.dtoToEntity(dto)).getId();
    }

    @Override
    public ${ENTITY_NAME_UPPER}Dto update(${ENTITY_NAME_UPPER}Dto dto) {
        ${ENTITY_NAME_UPPER} entity = ${ENTITY_NAME_LOWER}Repository.findById(dto.getId()).get();
        ${ENTITY_NAME_LOWER}Mapper.merge(dto, entity);
        ${ENTITY_NAME_LOWER}Repository.save(entity);
        return ${ENTITY_NAME_LOWER}Mapper.entityToDto(entity);
    }

    @Override
    public ${ENTITY_NAME_UPPER}Dto delete(Long id) {
        ${ENTITY_NAME_UPPER}Dto dto = find(id);
        ${ENTITY_NAME_LOWER}Repository.deleteById(id);
        return dto;
    }
}