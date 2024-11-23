package interface_adapter.add_input_file;

import entity.AudioAttributes;
import entity.TimeCode;
import entity.VideoAttributes;
import interface_adapter.ViewManagerModel;
import interface_adapter.convert_video_file.ConvertVideoFileState;
import interface_adapter.convert_video_file.ConvertVideoFileViewModel;
import lombok.AllArgsConstructor;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import use_case.add_input_file.AddInputFileOutputAudioData;
import use_case.add_input_file.AddInputFileOutputBoundary;
import use_case.add_input_file.AddInputFileOutputVideoData;

@AllArgsConstructor
public class AddInputFilePresenter implements AddInputFileOutputBoundary {

    private final AddInputFileViewModel addInputFileViewModel;
    private final ConvertVideoFileViewModel convertVideoFileViewModel;
    private final ViewManagerModel viewManagerModel;

    @Override
    public void prepareAudioSuccessView(AddInputFileOutputAudioData outputData) {
        
    }


    @Override
    public void prepareVideoSuccessView(AddInputFileOutputVideoData outputVideoData) {
        System.out.println("Success");
        final ConvertVideoFileState state = createConvertVideoState(outputVideoData);
        this.convertVideoFileViewModel.setState(state);
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
        String formatName = format.format_name;

        VideoAttributes videoAttributes = outputVideoData.getVideoAttributes();
        AudioAttributes audioAttributes = outputVideoData.getAudioAttributes();
        TimeCode startTime = new TimeCode(format.start_time);
        TimeCode endTime = new TimeCode(format.start_time + format.duration);
        return new ConvertVideoFileState(inputFilePath, formatName, startTime, endTime, videoAttributes, audioAttributes);
    }
}
