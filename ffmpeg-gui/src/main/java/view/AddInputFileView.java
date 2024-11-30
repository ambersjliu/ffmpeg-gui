package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.add_input_file.AddInputFileController;
import interface_adapter.add_input_file.AddInputFileState;
import interface_adapter.add_input_file.AddInputFileViewModel;
import lombok.Setter;

/**
 * Defines the view for add input file use case.
 */

public class AddInputFileView extends JPanel implements PropertyChangeListener {
    private final AddInputFileViewModel addInputFileViewModel;
    @Setter
    private AddInputFileController addInputFileController;

    private final JLabel chooseFile;
    private final JLabel selectedFilePath;
    private final JButton browseButton;
    private final JButton nextButton;
    private final JFileChooser fileChooser;
    private final JLabel errorField;

    public AddInputFileView(AddInputFileViewModel addInputFileViewModel) {
        this.addInputFileViewModel = addInputFileViewModel;

        this.addInputFileViewModel.addPropertyChangeListener(this);
        // initializes the views viewModel reference, the views controllers reference
        // and registers this view as a listener to teh view model changes. When
        // ViewModel state changes, the view's propertyChange method will be called
        // this allows the view to update itself when data changes.
        this.chooseFile = new JLabel(AddInputFileViewModel.CHOOSE_FILE_LABEL);
        this.nextButton = new JButton(AddInputFileViewModel.NEXT_BUTTON_LABEL);
        this.browseButton = new JButton(AddInputFileViewModel.BROWSE_BUTTON_LABEL);

        this.errorField = new JLabel("");
        this.errorField.setForeground(Color.red);

        this.selectedFilePath = new JLabel("");

        // displays a label for possible error message
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // provides a dialog window allowing users to navigate their computers files

        // Layout setup
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.white);

        // main content panel
        final JPanel mainContentPanel = new JPanel(new GridBagLayout());
        // creates padding
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // the components will stretch horizontally within its grid cell
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // there will be 5 pixels of padding between components
        gbc.insets = new Insets(5,5,5,5);

        // First row: Source File label, text field, and browse button
        gbc.gridx = 0;
        gbc.gridy = 0;
        // element stays at its preferred size
        gbc.weightx = 0;
        mainContentPanel.add(chooseFile, gbc);

        gbc.gridx = 1;
        // chooseFile can expand to take up extra space
        gbc.weightx = 1.0;
        mainContentPanel.add(selectedFilePath, gbc);

        gbc.gridx = 2;
        // fixed size
        gbc.weightx = 0;
        mainContentPanel.add(browseButton, gbc);

        // Second row: Next button (centered)
        gbc.gridx = 1;
        gbc.gridy = 1;
        // Extra top padding
        gbc.insets = new Insets(20, 5, 5, 5);
        mainContentPanel.add(nextButton, gbc);

        // Third row: Error message
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        mainContentPanel.add(errorField, gbc);

        add(mainContentPanel, BorderLayout.NORTH);

        // initial button state
        nextButton.setEnabled(false);

        setPreferredSize(new Dimension(400, 150));

        browseButton.addActionListener(
                evt -> {
                    if (evt.getSource() == browseButton) {
                        final int result = fileChooser.showOpenDialog(AddInputFileView.this);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            updateViewAndSetPathInViewModel(addInputFileViewModel);
                        }
                    }
                }
        );

        nextButton.addActionListener(
                evt -> {
                    if (evt.getSource() == nextButton) {
                        final AddInputFileState currentState = addInputFileViewModel.getState();
                        addInputFileController.execute(currentState.getFilePath());
                    }
                }
        );
    }

    private void updateViewAndSetPathInViewModel(AddInputFileViewModel viewModel) {
        // gets the file object that represents teh user selected
        final File selectedFile = fileChooser.getSelectedFile();
        final AddInputFileState currentState = viewModel.getState();
        currentState.setFilePath(selectedFile.getAbsolutePath());
        viewModel.firePropertyChanged();
        nextButton.setEnabled(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // gets teh current state from teh view model
        final AddInputFileState currentState = addInputFileViewModel.getState();
        // takes curr file pay and displays it
        selectedFilePath.setText(currentState.getFilePath());
        errorField.setText(currentState.getFileError());
    }

}
