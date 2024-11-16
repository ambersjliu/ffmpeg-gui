package app;

import interface_adapter.add_input_file.AddInputFileViewModel;
import view.AddInputFileView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        final AppBuilder appBuilder = new AppBuilder()
                .addGetPathsAndInitUseCase();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        AddInputFileViewModel addInputFileViewModel = new AddInputFileViewModel();
        AddInputFileView addInputFileView = new AddInputFileView(addInputFileViewModel);
        panel.add(addInputFileView, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
