package ${PROJECT_PACKAGE}.${ENTITY_PACKAGE};

import javax.persistence.*;
<#if USE_LOMBOK>
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
<#else>

</#if>
@Entity
public class ${ENTITY_NAME_UPPER} {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
<#list fields as field>
	<#if field.custom>
	@OneToOne
	</#if>
	private ${field.type} ${field.name};
</#list>
}