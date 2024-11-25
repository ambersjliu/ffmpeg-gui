package app;

import data_access.FFmpegService;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_input_file.AddInputFileController;
import interface_adapter.add_input_file.AddInputFilePresenter;
import interface_adapter.add_input_file.AddInputFileViewModel;
import interface_adapter.change_file.ChangeFileController;
import interface_adapter.change_file.ChangeFilePresenter;
import interface_adapter.convert_video_file.ConvertVideoFileController;
import interface_adapter.convert_video_file.ConvertVideoFilePresenter;
import interface_adapter.convert_video_file.ConvertVideoFileViewModel;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitController;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitPresenter;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitViewModel;
import use_case.add_input_file.AddInputFileInputBoundary;
import use_case.add_input_file.AddInputFileInteractor;
import use_case.add_input_file.AddInputFileOutputBoundary;
import use_case.change_file.ChangeFileInputBoundary;
import use_case.change_file.ChangeFileInteractor;
import use_case.change_file.ChangeFileOutputBoundary;
import use_case.convert_video.ConvertVideoFileInputBoundary;
import use_case.convert_video.ConvertVideoFileInteractor;
import use_case.convert_video.ConvertVideoFileOutputBoundary;
import use_case.get_paths_and_init.GetPathsAndInitInputBoundary;
import use_case.get_paths_and_init.GetPathsAndInitInteractor;
import use_case.get_paths_and_init.GetPathsAndInitOutputBoundary;
import view.AddInputFileView;
import view.ConvertVideoFileView;
import view.GetInputPathsAndInitView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // This is our "DAO"
    private final FFmpegService ffmpegService = new FFmpegService();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private AddInputFileViewModel addInputFileViewModel;
    private AddInputFileView addInputFileView;

    private GetInputPathsAndInitView getInputPathsAndInitView;
    private GetInputPathsAndInitViewModel getInputPathsAndInitViewModel;

    private ConvertVideoFileViewModel convertVideoFileViewModel;
    private ConvertVideoFileView convertVideoFileView;

    public AppBuilder(){
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addGetPathsAndInitView(){
        getInputPathsAndInitViewModel = new GetInputPathsAndInitViewModel();
        getInputPathsAndInitView = new GetInputPathsAndInitView(getInputPathsAndInitViewModel);
        cardPanel.add(getInputPathsAndInitView, getInputPathsAndInitView.getViewName());
        return this;
    }

    public AppBuilder addInputFileView(){
        addInputFileViewModel = new AddInputFileViewModel();
        addInputFileView = new AddInputFileView(addInputFileViewModel);
        cardPanel.add(addInputFileView, addInputFileView.getViewName());
        return this;
    }

    public AppBuilder convertVideoFileView() throws IOException {
        convertVideoFileViewModel = new ConvertVideoFileViewModel();
        convertVideoFileView = new ConvertVideoFileView(convertVideoFileViewModel);
        cardPanel.add(convertVideoFileView, convertVideoFileView.getViewName());
        return this;
    }

    public AppBuilder addGetPathsAndInitUseCase(){
        final GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary =
                new GetInputPathsAndInitPresenter(viewManagerModel, addInputFileViewModel, getInputPathsAndInitViewModel);

        final GetPathsAndInitInputBoundary getPathsAndInitInteractor =
                new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);

        final GetInputPathsAndInitController getInputPathsAndInitController = new GetInputPathsAndInitController(getPathsAndInitInteractor);
        getInputPathsAndInitView.setGetInputPathsAndInitController(getInputPathsAndInitController);
        return this;
    }

    public AppBuilder addInputFileUseCase(){
        final AddInputFileOutputBoundary addInputFileOutputBoundary =
                new AddInputFilePresenter(addInputFileViewModel, convertVideoFileViewModel, viewManagerModel, convertVideoFileView);

        final AddInputFileInputBoundary addInputFileInteractor =
                new AddInputFileInteractor(ffmpegService, addInputFileOutputBoundary);

        final AddInputFileController addInputFileController = new AddInputFileController(addInputFileInteractor);
        addInputFileView.setAddInputFileController(addInputFileController);
        return this;
    }

    public AppBuilder addChangeFileUseCase(){
        final ChangeFileOutputBoundary changeFileOutputBoundary =
                new ChangeFilePresenter(convertVideoFileViewModel, viewManagerModel, addInputFileViewModel);

        final ChangeFileInputBoundary changeFileInteractor =
                new ChangeFileInteractor(changeFileOutputBoundary);

        final ChangeFileController changeFileController = new ChangeFileController(changeFileInteractor);
        convertVideoFileView.setChangeFileController(changeFileController);
        return this;
    }

    public AppBuilder addConvertVideoFileUseCase(){
        final ConvertVideoFilePresenter convertVideoFileBoundary =
                new ConvertVideoFilePresenter(convertVideoFileViewModel);

        final ConvertVideoFileInputBoundary convertVideoFileInteractor =
                new ConvertVideoFileInteractor(convertVideoFileBoundary, ffmpegService);

        final ConvertVideoFileController convertVideoFileController = new ConvertVideoFileController(convertVideoFileInteractor);
        convertVideoFileView.setConvertVideoFileController(convertVideoFileController);
        return this;
    }

    public JFrame build(){
        final JFrame application = new JFrame("Setting ffmpeg file path");
        application.setSize(AppConstants.WIDTH, AppConstants.HEIGHT);
        application.setResizable(AppConstants.RESIZABLE);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(getInputPathsAndInitViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
