package view;

import interface_adapter.add_input_file.AddInputFileController;
import interface_adapter.add_input_file.AddInputFileViewModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

public class AddInputFileView extends JPanel {
    @Getter
    private final String viewName = "add input file";

    @Setter
    private AddInputFileController controller;

    public AddInputFileView(AddInputFileViewModel addInputFileViewModel){


    }
}
