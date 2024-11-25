package utils;

import exceptions.BadFileException;

public class Validator {
    private Validator() {
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
