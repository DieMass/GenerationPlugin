package ${PROJECT_PACKAGE}.${CONTROLLER_PACKAGE};

import org.springframework.web.bind.annotation.*;
import ${PROJECT_PACKAGE}.${SERVICE_PACKAGE}.${ENTITY_NAME_UPPER}Service;
import ${PROJECT_PACKAGE}.${DTO_PACKAGE}.${ENTITY_NAME_UPPER}Dto;
<#if USE_LOMBOK>
import lombok.RequiredArgsConstructor;
</#if>

@RestController
@RequestMapping("/${ENTITY_NAME_LOWER}")
<#if USE_LOMBOK>
@RequiredArgsConstructor
</#if>
public class ${ENTITY_NAME_UPPER}Controller {

    private final ${ENTITY_NAME_UPPER}Service ${ENTITY_NAME_LOWER}Service;

<#if !USE_LOMBOK>
    public ${ENTITY_NAME_UPPER}Controller(${ENTITY_NAME_UPPER}Service ${ENTITY_NAME_LOWER}Service) {
        this.${ENTITY_NAME_LOWER}Service = ${ENTITY_NAME_LOWER}Service;
    }
</#if>
    @GetMapping("/{id}")
	public ${ENTITY_NAME_UPPER}Dto find(@PathVariable Long id) {
		return ${ENTITY_NAME_LOWER}Service.find(id);
	}

	@PostMapping
    public Long save(@RequestBody ${ENTITY_NAME_UPPER}Dto dto) {
    	return ${ENTITY_NAME_LOWER}Service.save(dto);
    }

    @PutMapping
    public ${ENTITY_NAME_UPPER}Dto update(@RequestBody ${ENTITY_NAME_UPPER}Dto dto) {
        return ${ENTITY_NAME_LOWER}Service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ${ENTITY_NAME_UPPER}Dto delete(@PathVariable Long id) {
        return ${ENTITY_NAME_LOWER}Service.delete(id);
    }
}
