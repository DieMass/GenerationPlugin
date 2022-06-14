package ru.itis;

import com.google.gson.Gson;
import ru.itis.dto.CreatingClass;
import ru.itis.dto.DialogDto;
import freemarker.template.utility.StringUtil;
import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JSONParser {

	private final Gson gson = new Gson();

	@Getter
	private final Set<CreatingClass> generatedObjects = new HashSet<>();

	public void fillGeneratedObjects(DialogDto dialogDto) {
//		parseJsonToMaps(dialogDto).forEach((key, value) -> parse(value, key));
		parse(gson.fromJson(dialogDto.getDtoBody(), (Class<Map<String, ?>>) (Class<?>) Map.class), dialogDto.getEntityName());
	}

	//TODO: сделать для сложных вложенных объектов
	private Map<String, Map<String, Object>> parseJsonToMaps(DialogDto dialogDto) {
		Map<String, ?> parsedJsonBody = gson.fromJson(dialogDto.getDtoBody(), (Class<Map<String, ?>>) (Class<?>) Map.class);
		// Вложенные объекты
		Map<String, Map<String, Object>> objects = new HashMap<>();
		Map<String, Object> mainObject = new HashMap<>();

		parsedJsonBody.entrySet().stream().filter(fieldObject -> !parsedJsonBody.getClass().isAssignableFrom(fieldObject.getValue().getClass()))
				.forEach(primitiveField -> mainObject.put(primitiveField.getKey(), primitiveField.getValue()));
		objects.put(dialogDto.getEntityName(), mainObject);

		parsedJsonBody.entrySet().stream().filter(fieldObject -> parsedJsonBody.getClass().isAssignableFrom(fieldObject.getValue().getClass()))
				.forEach(stringEntry -> {
					objects.put(stringEntry.getKey(), (Map<String, Object>) stringEntry.getValue());
					mainObject.put(stringEntry.getKey(), new HashMap<>());
				});
		return objects;
	}

	public void parse(Map<String, ?> map, String entityName) {
		CreatingClass creatingClass = new CreatingClass();
		creatingClass.setName(StringUtil.capitalize(entityName));
		creatingClass.setImports(new HashSet<>());
		creatingClass.setFields(new HashSet<>());
		map.forEach((name, type) -> {
			boolean isMap = map.getClass().isAssignableFrom(type.getClass());
			if (isMap) parse((Map<String, Object>) type, name);
			creatingClass.getFields().add(
					new CreatingClass.Field(isMap ? StringUtil.capitalize(name) : type.getClass().getSimpleName(), name, isMap)
			);
		});
		generatedObjects.add(creatingClass);
	}
}
