package interface_adapter.add_input_file;

import attribute.AudioAttributes;
import attribute.TimeCode;
import attribute.VideoAttributes;
import constant.AudioFormat;
import constant.VideoFormat;
import interface_adapter.ViewManagerModel;
import interface_adapter.convert_audio_file.ConvertAudioFileState;
import interface_adapter.convert_audio_file.ConvertAudioFileViewModel;
import interface_adapter.convert_video_file.ConvertVideoFileState;
import interface_adapter.convert_video_file.ConvertVideoFileViewModel;
import lombok.AllArgsConstructor;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import use_case.add_input_file.AddInputFileOutputAudioData;
import use_case.add_input_file.AddInputFileOutputBoundary;
import use_case.add_input_file.AddInputFileOutputVideoData;
import utils.Truncator;
import view.ConvertAudioFileView;
import view.ConvertVideoFileView;

@AllArgsConstructor
public class AddInputFilePresenter implements AddInputFileOutputBoundary {

    private final AddInputFileViewModel addInputFileViewModel;
    private final ConvertVideoFileViewModel convertVideoFileViewModel;
    private final ConvertAudioFileViewModel convertAudioFileViewModel;
    private final ViewManagerModel viewManagerModel;
    // Views are needed ONLY so we can call a one-time view init method
    // Similar to calling firePropertyChanged, but we didn't want to update every
    // attribute in the View every time a property changed.
    private final ConvertVideoFileView convertVideoFileView;
    private final ConvertAudioFileView convertAudioFileView;

    @Override
    public void prepareAudioSuccessView(AddInputFileOutputAudioData outputData) {
        System.out.println("Success, audio imported");
        final ConvertAudioFileState state = createConvertAudioState(outputData);
        this.convertAudioFileViewModel.setState(state);
        this.convertAudioFileView.init();
        this.convertAudioFileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(convertAudioFileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }




    @Override
    public void prepareVideoSuccessView(AddInputFileOutputVideoData outputVideoData) {
        System.out.println("Success, video imported");
        final ConvertVideoFileState state = createConvertVideoState(outputVideoData);
        this.convertVideoFileViewModel.setState(state);
        this.convertVideoFileView.init();
        this.convertVideoFileViewModel.firePropertyChanged();


        this.viewManagerModel.setState(convertVideoFileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }



    @Override
    public void prepareFailView(String errorMessage) {
        final AddInputFileState state = addInputFileViewModel.getState();
        state.setFileError(errorMessage);
        addInputFileViewModel.firePropertyChanged();
    }


    private ConvertVideoFileState createConvertVideoState(AddInputFileOutputVideoData outputVideoData){
        FFmpegFormat format = outputVideoData.getFormat();

        String inputFilePath = outputVideoData.getInputFilePath();
        String formatName = VideoFormat.DEFAULT_VIDEO_FORMAT;

        VideoAttributes videoAttributes = outputVideoData.getVideoAttributes();
        AudioAttributes audioAttributes = outputVideoData.getAudioAttributes();
        TimeCode startTime = new TimeCode(Truncator.truncate(format.start_time));
        TimeCode endTime = new TimeCode(Truncator.truncate(format.start_time + format.duration));
        return new ConvertVideoFileState(inputFilePath, formatName, startTime, endTime, videoAttributes, audioAttributes);
    }

    private ConvertAudioFileState createConvertAudioState(AddInputFileOutputAudioData outputData) {
        FFmpegFormat format = outputData.getFormat();
        String inputFilePath = outputData.getInputFilePath();
        String formatName = AudioFormat.DEFAULT_AUDIO_FORMAT;

        AudioAttributes audioAttributes = outputData.getAudioAttributes();
        TimeCode startTime = new TimeCode(Truncator.truncate(format.start_time));
        TimeCode endTime = new TimeCode(Truncator.truncate(format.start_time + format.duration));
        return new ConvertAudioFileState(inputFilePath, formatName, startTime, endTime, audioAttributes);
    }
}
