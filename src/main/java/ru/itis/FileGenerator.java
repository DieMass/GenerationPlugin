package ru.itis;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import ru.itis.dto.DialogDto;
import ru.itis.dto.FileDto;
import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class FileGenerator {

	private Map<String, File> directories = new HashMap<>();

	@SneakyThrows
	public void createFolders(AnActionEvent event, DialogDto dialogDto) {
		Map<String, Boolean> map = PluginUtil.createPackagePropertiesMap(dialogDto.getPackageDto());
		map.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.forEach((key) -> createFolder(event, key));
		if (dialogDto.getSOAP()) createFolder(event, "");
	}

	public void createFiles(Project project, Map<String, Set<FileDto>> directoryToFiles) {
		directoryToFiles.forEach((directoryName, fileDtos) ->
				fileDtos.forEach(fileDto ->
						createClass(directories.get(directoryName), fileDto.getFileName(), fileDto.getContent())));
	}

	@SneakyThrows
	private void createFolder(AnActionEvent event, String title) {
		VirtualFile vFile = event.getData(PlatformDataKeys.VIRTUAL_FILE);
		String sourceRoot = vFile.getParent().getPath();
		directories.put(title, new File(sourceRoot + File.separator + title));
		directories.get(title).mkdirs();
	}

	@SneakyThrows
	private void createClass(File directory, String title, String content) {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(
						directory.getAbsolutePath() + File.separator + title + (title.equals("wsdl") ? ".xsd" : ".java")
				)
		);
		writer.write(content);
		writer.close();
	}
}
