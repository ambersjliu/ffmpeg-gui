package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import constant.AppConstants;
import data_access.FFmpegService;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_input_file.AddInputFileController;
import interface_adapter.add_input_file.AddInputFilePresenter;
import interface_adapter.add_input_file.AddInputFileViewModel;
import interface_adapter.change_file.ChangeFileController;
import interface_adapter.change_file.ChangeFilePresenter;
import interface_adapter.convert_audio_file.ConvertAudioFileController;
import interface_adapter.convert_audio_file.ConvertAudioFilePresenter;
import interface_adapter.convert_audio_file.ConvertAudioFileViewModel;
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
import use_case.convert_audio.ConvertAudioFileInputBoundary;
import use_case.convert_audio.ConvertAudioFileInteractor;
import use_case.convert_audio.ConvertAudioFileOutputBoundary;
import use_case.convert_video.ConvertVideoFileInputBoundary;
import use_case.convert_video.ConvertVideoFileInteractor;
import use_case.convert_video.ConvertVideoFileOutputBoundary;
import use_case.get_paths_and_init.GetPathsAndInitInputBoundary;
import use_case.get_paths_and_init.GetPathsAndInitInteractor;
import use_case.get_paths_and_init.GetPathsAndInitOutputBoundary;
import view.AddInputFileView;
import view.ConvertAudioFileView;
import view.ConvertVideoFileView;
import view.GetInputPathsAndInitView;
import view.ViewManager;

/**
 * Builds the app.
 */

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

    private ConvertAudioFileViewModel convertAudioFileViewModel;
    private ConvertAudioFileView convertAudioFileView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds get ffmpeg and ffprobe path view.
     * @return itself
     */

    public AppBuilder addGetPathsAndInitView() {
        getInputPathsAndInitViewModel = new GetInputPathsAndInitViewModel();
        getInputPathsAndInitView = new GetInputPathsAndInitView(getInputPathsAndInitViewModel);
        cardPanel.add(getInputPathsAndInitView, getInputPathsAndInitViewModel.getViewName());
        return this;
    }

    /**
     * Adds input file path view.
     * @return itself
     */

    public AppBuilder addInputFileView() {
        addInputFileViewModel = new AddInputFileViewModel();
        addInputFileView = new AddInputFileView(addInputFileViewModel);
        cardPanel.add(addInputFileView, addInputFileViewModel.getViewName());
        return this;
    }

    /**
     * Adds convert video view.
     * @return itself
     */

    public AppBuilder convertVideoFileView() {
        convertVideoFileViewModel = new ConvertVideoFileViewModel();
        convertVideoFileView = new ConvertVideoFileView(convertVideoFileViewModel);
        cardPanel.add(convertVideoFileView, convertVideoFileViewModel.getViewName());
        return this;
    }

    /**
     * Adds convert audio view.
     * @return itself
     */

    public AppBuilder convertAudioFileView() {
        convertAudioFileViewModel = new ConvertAudioFileViewModel();
        convertAudioFileView = new ConvertAudioFileView(convertAudioFileViewModel);
        cardPanel.add(convertAudioFileView, convertAudioFileViewModel.getViewName());
        return this;
    }

    /**
     * Add get ffmpeg and ffprobe path use case.
     * @return itself
     */

    public AppBuilder addGetPathsAndInitUseCase() {
        final GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary =
                new GetInputPathsAndInitPresenter(
                        viewManagerModel, addInputFileViewModel, getInputPathsAndInitViewModel);

        final GetPathsAndInitInputBoundary getPathsAndInitInteractor =
                new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);

        final GetInputPathsAndInitController getInputPathsAndInitController = new GetInputPathsAndInitController(
                getPathsAndInitInteractor);
        getInputPathsAndInitView.setGetInputPathsAndInitController(getInputPathsAndInitController);
        return this;
    }

    /**
     * Add input file path use case.
     * @return itself
     */

    public AppBuilder addInputFileUseCase() {
        final AddInputFileOutputBoundary addInputFileOutputBoundary =
                new AddInputFilePresenter(addInputFileViewModel, convertVideoFileViewModel, convertAudioFileViewModel,
                        viewManagerModel, convertVideoFileView, convertAudioFileView);

        final AddInputFileInputBoundary addInputFileInteractor =
                new AddInputFileInteractor(ffmpegService, addInputFileOutputBoundary);

        final AddInputFileController addInputFileController = new AddInputFileController(addInputFileInteractor);
        addInputFileView.setAddInputFileController(addInputFileController);
        return this;
    }

    /**
     * Add change file use case.
     * @return itself
     */

    public AppBuilder addChangeFileUseCase() {
        final ChangeFileOutputBoundary changeFileOutputBoundary =
                new ChangeFilePresenter(viewManagerModel, addInputFileViewModel);

        final ChangeFileInputBoundary changeFileInteractor =
                new ChangeFileInteractor(changeFileOutputBoundary);

        final ChangeFileController changeFileController = new ChangeFileController(changeFileInteractor);
        convertVideoFileView.setChangeFileController(changeFileController);
        convertAudioFileView.setChangeFileController(changeFileController);
        return this;
    }

    /**
     * Add convert video use case.
     * @return itself
     */

    public AppBuilder addConvertVideoFileUseCase() {
        final ConvertVideoFileOutputBoundary convertVideoFileOutputBoundary =
                new ConvertVideoFilePresenter(convertVideoFileViewModel);

        final ConvertVideoFileInputBoundary convertVideoFileInteractor =
                new ConvertVideoFileInteractor(convertVideoFileOutputBoundary, ffmpegService);

        final ConvertVideoFileController convertVideoFileController = new ConvertVideoFileController(
                convertVideoFileInteractor);
        convertVideoFileView.setConvertVideoFileController(convertVideoFileController);
        return this;
    }

    /**
     * Add convert audio use case.
     * @return itself
     */

    public AppBuilder addConvertAudioFileUseCase() {
        final ConvertAudioFileOutputBoundary convertAudioFileOutputBoundary =
                new ConvertAudioFilePresenter(convertAudioFileViewModel);
        final ConvertAudioFileInputBoundary convertAudioFileInputBoundary =
                new ConvertAudioFileInteractor(convertAudioFileOutputBoundary, ffmpegService);
        final ConvertAudioFileController convertAudioFileController = new ConvertAudioFileController(
                convertAudioFileInputBoundary);
        convertAudioFileView.setConvertAudioFileController(convertAudioFileController);
        return this;
    }

    /**
     * Builds the java swing ui.
     * @return the built ui
     */

    public JFrame build() {
        final JFrame application = new JFrame();
        application.setSize(AppConstants.WIDTH, AppConstants.HEIGHT);
        application.setResizable(AppConstants.RESIZABLE);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(getInputPathsAndInitViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
