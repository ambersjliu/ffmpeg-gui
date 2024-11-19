package app;

import javax.swing.*;

import java.io.IOException;

public class App {
    public static void main(String[] args){
        System.out.println(System.getenv("FFMPEG"));
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame frame = appBuilder
                .addGetPathsAndInitView()
                .addGetPathsAndInitUseCase()
                .build();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
