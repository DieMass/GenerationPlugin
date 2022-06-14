package ${PROJECT_PACKAGE}.${REPOSITORY_PACKAGE};

import ${PROJECT_PACKAGE}.${ENTITY_PACKAGE}.${ENTITY_NAME_UPPER};
import org.springframework.data.jpa.repository.JpaRepository;

public interface ${ENTITY_NAME_UPPER}Repository extends JpaRepository<${ENTITY_NAME_UPPER}, Long> {

}
