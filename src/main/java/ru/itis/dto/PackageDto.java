package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDto {

	private Boolean isCreateEntities;
	private Boolean isCreateDtos;
	private Boolean isCreateServices;
	private Boolean isCreateControllers;
	private Boolean isCreateMappers;
	private Boolean isCreateRepositories;
	private Boolean isSoap;
}
