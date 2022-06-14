package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class CreatingClass {

	private String name;
	private Set<Field> fields;
	private Set<String> imports;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Field {
		private String type;
		private String name;
		private Boolean custom;
	}
}
