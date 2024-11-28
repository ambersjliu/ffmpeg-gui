package utils;

import exceptions.BadFileException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validator {
    private Validator() {
    }

    public static void doesEndOfPathContain(String filePath, String targetString) throws IOException {
        Path path = Paths.get(filePath);
        Path endOfPath = path.getFileName();
        if (endOfPath == null || endOfPath.toString().contains(targetString)) {
            throw new IOException();
        }
    }

    public static void validateFilePaths(String... paths) throws BadFileException {
        for (String path : paths) {
            validateFilePath(path);
        }
    }

    public static void validateFilePath(String path) throws BadFileException {
        if (!isValidPath(path))
            throw new BadFileException();
    }

    private static boolean isValidPath(String path) {
        return (path != null && !path.trim().isEmpty());
    }


}
