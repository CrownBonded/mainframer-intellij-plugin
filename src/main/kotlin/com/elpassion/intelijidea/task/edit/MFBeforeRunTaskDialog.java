package com.elpassion.intelijidea.task.edit;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.*;
import com.intellij.util.IconUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MFBeforeRunTaskDialog extends DialogWrapper implements TaskEditForm {

    private final TaskEditValidator taskEditValidator = new TaskEditValidator(this);
    private final Project project;
    private LabeledComponent<TextFieldWithBrowseButton> mainframerToolHolder;
    private JPanel contentPane;
    public JTextField buildCommandField;
    public JTextField taskField;
    public TextFieldWithBrowseButton mainframerToolField;

    public MFBeforeRunTaskDialog(Project project) {
        super(project);
        this.project = project;
        setModal(true);
        init();
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        return taskEditValidator.doValidate();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }

    private void createUIComponents() {
        mainframerToolField = new TextFieldWithBrowseButton();
        mainframerToolField.setButtonIcon(IconUtil.getAddIcon());
        TextBrowseFolderListener textBrowseFolderListener = new TextBrowseFolderListener(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project);
        mainframerToolField.addBrowseFolderListener(textBrowseFolderListener);
        mainframerToolHolder = new LabeledComponent<>();
        mainframerToolHolder.setComponent(mainframerToolField);
    }

    public void restoreMainframerData(String mainframerPath, String buildCommand, String taskName) {
        mainframerToolField.setText(mainframerPath);
        buildCommandField.setText(buildCommand);
        taskField.setText(taskName);
    }

    @NotNull
    @Override
    public JTextField taskField() {
        return taskField;
    }

    @NotNull
    @Override
    public JTextField buildCommandField() {
        return buildCommandField;
    }

    @NotNull
    @Override
    public TextFieldWithBrowseButton mainframerToolField() {
        return mainframerToolField;
    }
}