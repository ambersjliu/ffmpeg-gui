package app;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        final AppBuilder appBuilder = new AppBuilder()
                .addGetPathsAndInitUseCase();
    }
}
