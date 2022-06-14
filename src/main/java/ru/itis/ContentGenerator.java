package ru.itis;

import ru.itis.dto.CreatingClass;
import ru.itis.dto.DialogDto;
import ru.itis.dto.FileContentDto;
import ru.itis.dto.FileDto;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.*;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContentGenerator {

	@Getter
	private final JSONParser jsonParser = new JSONParser();
	@Getter
	private final Map<String, Set<FileDto>> directoryToFiles = new HashMap<>();

	public void createContent(DialogDto dialogDto) {
		// Freemarker configs
		final Configuration freeMarCfg = new Configuration(Configuration.VERSION_2_3_30);
		final ClassTemplateLoader loader = new ClassTemplateLoader(ContentGenerator.class, PluginProperties.getTemplateDirectory());
		freeMarCfg.setTemplateLoader(loader);
		freeMarCfg.setDefaultEncoding("UTF-8");
		freeMarCfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		Map<String, Boolean> directoriesCreate = PluginUtil.createPackagePropertiesMap(dialogDto.getPackageDto());
		List<String> createdDirectories = directoriesCreate.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
		createdDirectories.forEach(i -> directoryToFiles.put(i, new HashSet<>()));
		directoryToFiles.put("root", new HashSet<>());

		// Map for getting template file
		Map<String, String> directoryToTemplate = new HashMap<>() {{
			put(PluginProperties.getEntitiesPackage(), PluginProperties.getEntityTemplate());
			put(PluginProperties.getDtosPackage(), PluginProperties.getDtoTemplate());
			put(PluginProperties.getMappersPackage(), PluginProperties.getMapperTemplate());
			put(PluginProperties.getRequestMappersPackage(), PluginProperties.getRequestMapperTemplate());
			put(PluginProperties.getResponseMappersPackage(), PluginProperties.getResponseMapperTemplate());
			put(PluginProperties.getRepositoriesPackage(), PluginProperties.getRepositoryTemplate());
			put(PluginProperties.getServicesPackage(), PluginProperties.getServiceTemplate());
			put(PluginProperties.getServiceImplsPackage(), PluginProperties.getServiceImplTemplate());
			put(PluginProperties.getControllersPackage(), PluginProperties.getControllerTemplate());
			put(PluginProperties.getEndpointsPackage(), PluginProperties.getEndpointTemplate());
		}};
		Map<String, String> directoryToSuffix = new HashMap<>() {{
			put(PluginProperties.getEntitiesPackage(), "");
			put(PluginProperties.getDtosPackage(), "Dto");
			put(PluginProperties.getMappersPackage(), "Mapper");
			put(PluginProperties.getRequestMappersPackage(), "RequestMapper");
			put(PluginProperties.getResponseMappersPackage(), "ResponseMapper");
			put(PluginProperties.getRepositoriesPackage(), "Repository");
			put(PluginProperties.getServicesPackage(), "Service");
			put(PluginProperties.getServiceImplsPackage(), "ServiceImpl");
			put(PluginProperties.getControllersPackage(), "Controller");
			put(PluginProperties.getEndpointsPackage(), "Endpoint");
		}};

		jsonParser.getGeneratedObjects().forEach(creatingClass ->
				directoryToTemplate.forEach((directoryName, templateName) -> {
					if (!createdDirectories.contains(directoryName)) return;
					try {
						Template temp = freeMarCfg.getTemplate(templateName);
						StringWriter stringWriter = new StringWriter();
						temp.process(createRootMap(createFileContentDto(dialogDto, creatingClass.getName(), directoryToSuffix.get(directoryName))), stringWriter);
						directoryToFiles.get(directoryName).add(new FileDto(creatingClass.getName() + directoryToSuffix.get(directoryName), stringWriter.toString()));
					} catch (TemplateException | IOException e) {
						throw new RuntimeException(e);
					}
				}));
		if (dialogDto.getSOAP()) {
			Map<String, Object> root = new HashMap<>();
			jsonParser.getGeneratedObjects()
					.forEach(entity -> entity.getFields()
							.forEach(field -> field.setType(field.getCustom() ? "long" : field.getType().equals("String") ? "string" : field.getType()))
					);
			root.put("entities", jsonParser.getGeneratedObjects());
			root.put("DTO_PACKAGE", PluginProperties.getDtosPackage());
			String[] packages = dialogDto.getProjectPackage().split("\\.");
			root.put("PROJECT_PACKAGE", IntStream.rangeClosed(1, packages.length).mapToObj(i -> packages[packages.length - i]).collect(Collectors.joining(".")));
			try {
				Template temp = freeMarCfg.getTemplate(PluginProperties.getWsdlTemplate());
				StringWriter stringWriter = new StringWriter();
				temp.process(root, stringWriter);
				directoryToFiles.put("", new HashSet<>());
				directoryToFiles.get("").add(new FileDto("wsdl", stringWriter.toString()));
			} catch (IOException | TemplateException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private FileContentDto createFileContentDto(DialogDto dialogDto, String entityName, String suffix) {
		FileContentDto fileContentDto = new FileContentDto();
		fileContentDto.setEntityName(entityName);
		fileContentDto.setEntityNameSuffix(suffix);
		fileContentDto.setProjectPackage(dialogDto.getProjectPackage());
		fileContentDto.setUseLombok(dialogDto.getUseLombok());
		fileContentDto.setUseSpring(dialogDto.getUseSpring());
		fileContentDto.setUseSpringDataRest(dialogDto.getUseSpringDataRest());
		return fileContentDto;
	}

	@SneakyThrows
	private Map<String, Object> createRootMap(FileContentDto fileContentDto) {
		Map<String, Object> root = new HashMap<>();
		root.put("ENTITY_PACKAGE", PluginProperties.getEntitiesPackage());
		root.put("DTO_PACKAGE", PluginProperties.getDtosPackage());
		root.put("MAPPER_PACKAGE", PluginProperties.getMappersPackage());
		root.put("REPOSITORY_PACKAGE", PluginProperties.getRepositoriesPackage());
		root.put("SERVICE_PACKAGE", PluginProperties.getServicesPackage());
		root.put("CONTROLLER_PACKAGE", PluginProperties.getControllersPackage());
		root.put("ENDPOINT_PACKAGE", PluginProperties.getEndpointsPackage());

		Set<CreatingClass.Field> fields = jsonParser.getGeneratedObjects().stream()
				.filter(i -> i.getName().equals(StringUtils.capitalize(fileContentDto.getEntityName())))
				.findFirst().get().getFields();
		root.put("ENTITY_NAME_UPPER", StringUtils.capitalize(fileContentDto.getEntityName()));
		root.put("ENTITY_NAME_LOWER", StringUtils.uncapitalize(fileContentDto.getEntityName()));
		root.put("imports", getImports(fields));
		root.put("PROJECT_PACKAGE", fileContentDto.getProjectPackage());
		root.put("USE_LOMBOK", fileContentDto.getUseLombok());
		List<CreatingClass.Field> newFields = new ArrayList<>();
		fields.forEach(i -> newFields.add(new CreatingClass.Field(i.getType(), i.getName(), i.getCustom())));
		newFields.stream().filter(CreatingClass.Field::getCustom).forEach(i -> {
			i.setType(i.getType() + fileContentDto.getEntityNameSuffix());
			i.setName(i.getName() + fileContentDto.getEntityNameSuffix());
		});
		root.put("fields", newFields);
		return root;
	}

	//TODO: create imports
	private List<String> getImports(Set<CreatingClass.Field> fields) {
		return fields.stream().map(CreatingClass.Field::getType)
				.filter(i -> false)
				.collect(Collectors.toList());
	}
}
