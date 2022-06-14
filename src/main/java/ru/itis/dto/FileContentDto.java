package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileContentDto {

	private String entityName;
	private String entityNameSuffix;
	private String projectPackage;
	private Boolean useLombok;
	private Boolean useSpring;
	private Boolean useSpringDataRest;
}
