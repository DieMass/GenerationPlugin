package ${PROJECT_PACKAGE}.${ENDPOINT_PACKAGE};

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ${PROJECT_PACKAGE}.${SERVICE_PACKAGE}.${ENTITY_NAME_UPPER}Service;
import ${PROJECT_PACKAGE}.${MAPPER_PACKAGE}.request.${ENTITY_NAME_UPPER}RequestMapper;
import ${PROJECT_PACKAGE}.${MAPPER_PACKAGE}.response.${ENTITY_NAME_UPPER}ResponseMapper;
import ${PROJECT_PACKAGE}.${DTO_PACKAGE}.${ENTITY_NAME_UPPER}Dto;
import ${PROJECT_PACKAGE}.${DTO_PACKAGE}.xml.*;
<#if USE_LOMBOK>
import lombok.RequiredArgsConstructor;
</#if>

@Endpoint
<#if USE_LOMBOK>
@RequiredArgsConstructor
</#if>
public class ${ENTITY_NAME_UPPER}Endpoint {

    private static final String NAMESPACE_URI = "";

    private final ${ENTITY_NAME_UPPER}Service ${ENTITY_NAME_LOWER}Service;
    private final ${ENTITY_NAME_UPPER}RequestMapper ${ENTITY_NAME_LOWER}RequestMapper;
    private final ${ENTITY_NAME_UPPER}ResponseMapper ${ENTITY_NAME_LOWER}ResponseMapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "get${ENTITY_NAME_UPPER}Request")
    @ResponsePayload
    public Get${ENTITY_NAME_UPPER}Response get${ENTITY_NAME_UPPER}(@RequestPayload Get${ENTITY_NAME_UPPER}Request request) {
        ${ENTITY_NAME_UPPER}Dto responseDto = ${ENTITY_NAME_LOWER}Service.find(request.getId());
        return ${ENTITY_NAME_LOWER}ResponseMapper.dtoToGetResponse(responseDto);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "save${ENTITY_NAME_UPPER}Request")
    @ResponsePayload
    public Save${ENTITY_NAME_UPPER}Response save${ENTITY_NAME_UPPER}(@RequestPayload Save${ENTITY_NAME_UPPER}Request request) {
        ${ENTITY_NAME_UPPER}Dto requestDto = ${ENTITY_NAME_LOWER}RequestMapper.saveRequestToDto(request);
        Long id = ${ENTITY_NAME_LOWER}Service.save(requestDto);
        Save${ENTITY_NAME_UPPER}Response response = new Save${ENTITY_NAME_UPPER}Response();
        response.setId(id);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "update${ENTITY_NAME_UPPER}Request")
    @ResponsePayload
    public Update${ENTITY_NAME_UPPER}Response update${ENTITY_NAME_UPPER}(@RequestPayload Update${ENTITY_NAME_UPPER}Request request) {
        ${ENTITY_NAME_UPPER}Dto requestDto = ${ENTITY_NAME_LOWER}RequestMapper.updateRequestToDto(request);
        ${ENTITY_NAME_UPPER}Dto responseDto = ${ENTITY_NAME_LOWER}Service.update(request.get${ENTITY_NAME_UPPER}().getId(), requestDto);
        return ${ENTITY_NAME_LOWER}ResponseMapper.dtoToUpdateResponse(responseDto);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "delete${ENTITY_NAME_UPPER}Request")
    @ResponsePayload
    public Delete${ENTITY_NAME_UPPER}Response delete${ENTITY_NAME_UPPER}(@RequestPayload Delete${ENTITY_NAME_UPPER}Request request) {
        ${ENTITY_NAME_UPPER}Dto responseDto = ${ENTITY_NAME_LOWER}Service.delete(request.getId());
        return ${ENTITY_NAME_LOWER}ResponseMapper.dtoToDeleteResponse(responseDto);
    }
}
