package ru.itis;

import ru.itis.dto.PackageDto;

import java.util.HashMap;
import java.util.Map;

public class PluginUtil {

	public static Map<String, Boolean> createPackagePropertiesMap(PackageDto packageDto) {
		return new HashMap<>() {{
			put(PluginProperties.getEntitiesPackage(), packageDto.getIsCreateEntities());
			put(PluginProperties.getDtosPackage(), packageDto.getIsCreateDtos());
			put(PluginProperties.getRepositoriesPackage(), packageDto.getIsCreateRepositories());
			put(PluginProperties.getServicesPackage(), packageDto.getIsCreateServices());
			put(PluginProperties.getServiceImplsPackage(), packageDto.getIsCreateServices());
			put(PluginProperties.getMappersPackage(), packageDto.getIsCreateMappers());
			if (packageDto.getIsSoap()) {
				put(PluginProperties.getEndpointsPackage(), packageDto.getIsCreateControllers());
				put(PluginProperties.getRequestMappersPackage(), packageDto.getIsCreateMappers());
				put(PluginProperties.getResponseMappersPackage(), packageDto.getIsCreateMappers());
			} else {
				put(PluginProperties.getControllersPackage(), packageDto.getIsCreateControllers());
			}
		}};
	}
}
