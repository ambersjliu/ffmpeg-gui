package app;

import javax.swing.JFrame;

/**
 * Defines and initializes the app.
 */

public class App {
    /**
     * Initializes the app.
     * @param args args in psvm
     */
    public static void main(String[] args) {
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

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
