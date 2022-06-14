package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassCreatingDto {

	private String entityName;
	private String projectPackage;
	private Boolean useLombok;
	private Boolean useSpring;
	private Boolean useSpringDataRest;
	private Map<String, String> fields;
	private List<String> imports;

}
