package app;

import data_access.FFmpegService;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_input_file.AddInputFileViewModel;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitController;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitPresenter;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitViewModel;
import use_case.get_paths_and_init.GetPathsAndInitInputBoundary;
import use_case.get_paths_and_init.GetPathsAndInitInteractor;
import use_case.get_paths_and_init.GetPathsAndInitOutputBoundary;
import view.AddInputFileView;
import view.GetInputPathsAndInitView;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // This is our "DAO"
    private final FFmpegService ffmpegService = new FFmpegService();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();

    private AddInputFileViewModel addInputFileViewModel;
    private AddInputFileView addInputFileView;
    private GetInputPathsAndInitView getInputPathsAndInitView;
    private GetInputPathsAndInitViewModel getInputPathsAndInitViewModel;

    public AppBuilder(){
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addGetPathsAndInitView(){
        getInputPathsAndInitViewModel = new GetInputPathsAndInitViewModel();
        getInputPathsAndInitView = new GetInputPathsAndInitView(getInputPathsAndInitViewModel);
        cardPanel.add(getInputPathsAndInitView, getInputPathsAndInitView.getViewName());
        return this;
    }

    public AppBuilder addGetPathsAndInitUseCase(){
        final GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary =
                new GetInputPathsAndInitPresenter(viewManagerModel, addInputFileViewModel, getInputPathsAndInitViewModel); // should have viewmodel as param

        final GetPathsAndInitInputBoundary getPathsAndInitInputBoundary =
                new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);

        final GetInputPathsAndInitController getInputPathsAndInitController = new GetInputPathsAndInitController(getPathsAndInitInputBoundary);
        getInputPathsAndInitView.setGetInputPathsAndInitController(getInputPathsAndInitController);
        return this;
    }

    public JFrame build(){
        final JFrame application = new JFrame("Setting ffmpeg file path");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(getInputPathsAndInitView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
