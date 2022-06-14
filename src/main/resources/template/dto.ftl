package ${PROJECT_PACKAGE}.${DTO_PACKAGE};

<#if USE_LOMBOK>
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@FieldNameConstants
<#else>

</#if>
public class ${ENTITY_NAME_UPPER}Dto {

    private Long id;
<#list fields as field>
    private ${field.type} ${field.name};
</#list>

}
