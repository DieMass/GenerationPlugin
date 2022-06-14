package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DialogDto {

	private String entityName;
	private String projectPackage;
	private String dtoBody;
	private Boolean useLombok;
	private PackageDto packageDto;
	private Boolean SOAP;
	private Boolean JSON;
	private Boolean useSpring;
	private Boolean useSpringDataRest;

}
