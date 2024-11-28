package view;

import interface_adapter.add_input_file.AddInputFileViewModel;
import interface_adapter.add_input_file.AddInputFileState;
import interface_adapter.add_input_file.AddInputFileController;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;


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



    public AddInputFileView(AddInputFileViewModel addInputFileViewModel){
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
        JPanel mainContentPanel = new JPanel(new GridBagLayout());
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); //creates padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // the components will stretch horizontally within its grid cell
        gbc.insets = new Insets(5,5,5,5); // there will be 5 pixels of padding between components

        // First row: Source File label, text field, and browse button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0; // element stays at its preferred size
        mainContentPanel.add(chooseFile, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;// chooseFile can expand to take up extra space
        mainContentPanel.add(selectedFilePath, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0; // fixed size
        mainContentPanel.add(browseButton, gbc);

        // Second row: Next button (centered)
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 5, 5, 5);  // Extra top padding
        mainContentPanel.add(nextButton, gbc);

        // Third row: Error message
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        mainContentPanel.add(errorField, gbc);



        add(mainContentPanel, BorderLayout.NORTH);

        //initial button state
        nextButton.setEnabled(false);

        setPreferredSize(new Dimension(400, 150));

        browseButton.addActionListener(
                e -> {
                    if (e.getSource() == browseButton) {
                        int result = fileChooser.showOpenDialog(AddInputFileView.this);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile(); // gets the file object taht represents teh user selected
                            selectedFilePath.setText(selectedFile.getAbsolutePath()); //gets teh full path fo teh file object
                            errorField.setText("");
                            nextButton.setEnabled(true);
                            AddInputFileState currentState = addInputFileViewModel.getState();
                            currentState.setFilePath(selectedFile.getAbsolutePath());
                        }
                    }
                }
        );

        nextButton.addActionListener(
                e -> {
                    if (e.getSource() == nextButton) {
                        AddInputFileState currentState = addInputFileViewModel.getState();
                        addInputFileController.execute(currentState.getFilePath());
                    }
                }
        );

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddInputFileState currentState = addInputFileViewModel.getState(); //gets teh current state from teh view model
        selectedFilePath.setText(currentState.getFilePath()); // takes curr file pay and displays it
        errorField.setText(currentState.getFileError());
    }

}