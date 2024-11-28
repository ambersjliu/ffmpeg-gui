package app;

import interface_adapter.convert_video_file.ConvertVideoFileViewModel;
import net.bramp.ffmpeg.FFmpeg;
import view.ConvertVideoFileView;

import javax.swing.*;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getenv("FFMPEG"));
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame frame = appBuilder
                .addGetPathsAndInitView()
                .addInputFileView()
                .convertVideoFileView()
                .convertAudioFileView()
                .addGetPathsAndInitUseCase()
                .addInputFileUseCase()
                .addChangeFileUseCase()
                .addConvertVideoFileUseCase()
                .addConvertAudioFileUseCase()
                .build();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //JFrame test = new JFrame();
        //ConvertVideoFileViewModel testviewmodel = new ConvertVideoFileViewModel();
        //ConvertVideoFileView testview = new ConvertVideoFileView(testviewmodel);
        //test.add(testview);
        //test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //test.setSize(AppConstants.WIDTH, AppConstants.HEIGHT);
        //test.setVisible(true);
    }
}
