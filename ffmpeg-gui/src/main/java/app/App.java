package app;

import interface_adapter.add_input_file.AddInputFileViewModel;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitViewModel;
import view.AddInputFileView;
import view.GetInputPathsAndInitView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getenv("FFMPEG"));
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame frame = appBuilder
                .addGetPathsAndInitView()
                .addGetPathsAndInitUseCase()
                .build();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel panel = new JPanel(new BorderLayout());
//        GetInputPathsAndInitViewModel getInputPathsAndInitViewModel = new GetInputPathsAndInitViewModel();
//        GetInputPathsAndInitView getInputPathsAndInitView = new GetInputPathsAndInitView(getInputPathsAndInitViewModel);
//        AddInputFileViewModel addInputFileViewModel = new AddInputFileViewModel();
//        AddInputFileView addInputFileView = new AddInputFileView(addInputFileViewModel);
//        panel.add(getInputPathsAndInitView, BorderLayout.CENTER);
//        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
