package interface_adapter.convert_audio_file;

import interface_adapter.ViewModel;

import java.io.IOException;

public class ConvertAudioFileViewModel extends ViewModel<ConvertAudioFileState> {

    public ConvertAudioFileViewModel() throws IOException {
        super("convert audio file");
        setState(new ConvertAudioFileState());
    }
}
