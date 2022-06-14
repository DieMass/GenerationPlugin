package ru.itis;

import lombok.Getter;

import java.io.IOException;
import java.util.Properties;

public class PluginProperties {
	@Getter
	private static final String entitiesPackage;
	@Getter
	private static final String dtosPackage;
	@Getter
	private static final String requestMappersPackage;
	@Getter
	private static final String responseMappersPackage;
	@Getter
	private static final String mappersPackage;
	@Getter
	private static final String repositoriesPackage;
	@Getter
	private static final String servicesPackage;
	@Getter
	private static final String serviceImplsPackage;
	@Getter
	private static final String controllersPackage;
	@Getter
	private static final String endpointsPackage;
	@Getter
	private static final String templateDirectory;

	@Getter
	private static final String wsdlTemplate;
	@Getter
	private static final String controllerTemplate;
	@Getter
	private static final String endpointTemplate;
	@Getter
	private static final String serviceTemplate;
	@Getter
	private static final String serviceImplTemplate;
	@Getter
	private static final String mapperTemplate;
	@Getter
	private static final String requestMapperTemplate;
	@Getter
	private static final String responseMapperTemplate;
	@Getter
	private static final String repositoryTemplate;
	@Getter
	private static final String dtoTemplate;
	@Getter
	private static final String entityTemplate;

	static {
		Properties prop = new Properties();
		try {
			prop.load(PluginProperties.class.getResourceAsStream("/application.properties"));
			entitiesPackage = prop.getProperty("package.entities");
			dtosPackage = prop.getProperty("package.dtos");
			mappersPackage = prop.getProperty("package.mappers");
			requestMappersPackage = prop.getProperty("package.mappers.request");
			responseMappersPackage = prop.getProperty("package.mappers.response");
			repositoriesPackage = prop.getProperty("package.repositories");
			servicesPackage = prop.getProperty("package.services");
			serviceImplsPackage = prop.getProperty("package.services.impl");
			controllersPackage = prop.getProperty("package.controllers");
			endpointsPackage = prop.getProperty("package.endpoints");
			templateDirectory = prop.getProperty("template.directory");

			wsdlTemplate = prop.getProperty("template.wsdl");
			controllerTemplate = prop.getProperty("template.controller");
			endpointTemplate = prop.getProperty("template.endpoint");
			serviceTemplate = prop.getProperty("template.service");
			serviceImplTemplate = prop.getProperty("template.service.impl");
			mapperTemplate = prop.getProperty("template.mapper");
			requestMapperTemplate = prop.getProperty("template.mapper.request");
			responseMapperTemplate = prop.getProperty("template.mapper.response");
			repositoryTemplate = prop.getProperty("template.repository");
			dtoTemplate = prop.getProperty("template.dto");
			entityTemplate = prop.getProperty("template.entity");

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
