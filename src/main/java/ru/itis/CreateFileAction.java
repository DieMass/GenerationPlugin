package ru.itis;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.JBUI;
import ru.itis.dto.DialogDto;
import ru.itis.dto.PackageDto;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class CreateFileAction extends AnAction {

	private final JTextArea textArea = new JTextArea(15, 20);
	private final JTextField entityNameTextField = new JBTextField("Entity name");
	private final JTextField projectPackageTextField = new JBTextField("Project package");
	private JButton jButton;
	private final ContentGenerator contentGenerator = new ContentGenerator();
	private final FileGenerator fileGenerator = new FileGenerator();
	private final JBCheckBox isCreateEntities = new JBCheckBox("Create entities");
	private final JBCheckBox isCreateDtos = new JBCheckBox("Create dtos");
	private final JBCheckBox isCreateServices = new JBCheckBox("Create services");
	private final JBCheckBox isCreateControllers = new JBCheckBox("Create controllers");
	private final JBCheckBox isCreateMappers = new JBCheckBox("Create mappers");
	private final JBCheckBox isCreateRepos = new JBCheckBox("Create repositories");
	private final ButtonGroup typeGroup = new ButtonGroup();
	private final JRadioButton isUseSoap = new JRadioButton("SOAP");
	private final JRadioButton isUseJson = new JRadioButton("JSON");
//	private final JBCheckBox isUseSpring = new JBCheckBox("Use Spring");
	private final JBCheckBox isUseLombok = new JBCheckBox("Use lombok");
//	private final JBCheckBox isUseSpringRest = new JBCheckBox("Use Spring Data REST");

	{
		typeGroup.add(isUseJson);
		typeGroup.add(isUseSoap);
	}

	private JPanel createPanel(AnActionEvent e) {
		createCreatingButton(e);

		textArea.setTabSize(4);
		Font font = new Font("Serif", Font.BOLD, 12);

		textArea.setFont(font);

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(projectPackageTextField)
						.addComponent(entityNameTextField)
						.addComponent(textArea)
				)
				.addGroup(layout.createParallelGroup()
						.addComponent(isUseSoap)
						.addComponent(isUseJson)
				)
				.addGroup(layout.createParallelGroup()
						.addComponent(isUseLombok)
				)
				.addGroup(layout.createParallelGroup()
						.addComponent(isCreateEntities)
						.addComponent(isCreateDtos)
						.addComponent(isCreateServices)
						.addComponent(isCreateControllers)
						.addComponent(isCreateMappers)
						.addComponent(isCreateRepos)
				)
				.addComponent(jButton)
		);
		isUseLombok.setMargin(JBUI.insetsRight(50));
		layout.setVerticalGroup(
				layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addComponent(entityNameTextField)
								.addComponent(projectPackageTextField)
								.addComponent(textArea)
						)
						.addGroup(layout.createSequentialGroup()
								.addComponent(isUseSoap)
								.addComponent(isUseJson)
						)
						.addGroup(layout.createSequentialGroup()
								.addComponent(isUseLombok)
						)
						.addGroup(layout.createSequentialGroup()
								.addComponent(isCreateEntities)
								.addComponent(isCreateDtos)
								.addComponent(isCreateServices)
								.addComponent(isCreateControllers)
								.addComponent(isCreateMappers)
								.addComponent(isCreateRepos)
						)
						.addComponent(jButton)
		);
		return panel;
	}

	private PackageDto createPackageDto() {
		PackageDto packageDto = new PackageDto();
		packageDto.setIsCreateEntities(isCreateEntities.isSelected());
		packageDto.setIsCreateDtos(isCreateDtos.isSelected());
		packageDto.setIsCreateRepositories(isCreateRepos.isSelected());
		packageDto.setIsCreateMappers(isCreateMappers.isSelected());
		packageDto.setIsCreateServices(isCreateServices.isSelected());
		packageDto.setIsCreateControllers(isCreateControllers.isSelected());
		packageDto.setIsSoap(isUseSoap.isSelected());
		return packageDto;
	}

	private DialogDto createDialogDto() {
		DialogDto dialogDto = new DialogDto();
		dialogDto.setPackageDto(createPackageDto());
		dialogDto.setProjectPackage(projectPackageTextField.getText());
		dialogDto.setEntityName(entityNameTextField.getText());
		dialogDto.setProjectPackage(projectPackageTextField.getText());
		dialogDto.setDtoBody(textArea.getText());
		dialogDto.setJSON(isUseJson.isSelected());
		dialogDto.setSOAP(isUseSoap.isSelected());
		dialogDto.setUseLombok(isUseLombok.isSelected());
		return dialogDto;
	}

	private void createCreatingButton(AnActionEvent e) {
		jButton = new JButton("Create CRUD-project");
		jButton.addActionListener(actionListener -> {
			DialogDto dialogDto = createDialogDto();
			contentGenerator.getJsonParser().fillGeneratedObjects(dialogDto);
			contentGenerator.createContent(dialogDto);
			fileGenerator.createFolders(e, dialogDto);
			fileGenerator.createFiles(e.getProject(), contentGenerator.getDirectoryToFiles());
		});
	}

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		DialogBuilder builder = new DialogBuilder();
		builder.setTitle("Create");
		builder.setCenterPanel(createPanel(e));
		builder.show();
	}

	@Override
	public boolean isDumbAware() {
		return super.isDumbAware();
	}
}
