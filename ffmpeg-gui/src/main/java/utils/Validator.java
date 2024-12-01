package utils;

import java.awt.Component;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptions.BadFileException;

/**
 * Various Validation methods.
 */

public final class Validator {
    private Validator() {
    }

    /**
     * Check if the path contains the required string at the end.
     * @param filePath input path
     * @param targetString requried string
     * @throws IOException thrown if the end of the path doesn't contain the required string at the end
     */
    public static void doesEndOfPathContain(String filePath, String targetString) throws IOException {
        final Path path = Paths.get(filePath);
        final Path endOfPath = path.getFileName();
        if (endOfPath == null || !endOfPath.toString().contains(targetString)) {
            throw new IOException();
        }
    }

    /**
     * Validate multiple file paths.
     * @param paths object contains multiple paths
     * @throws BadFileException if any one of the path is invalid.
     */

    public static void validateFilePaths(String... paths) throws BadFileException {
        for (String path : paths) {
            validateFilePath(path);
        }
    }

    /**
     * Valide one file path.
     * @param path file path
     * @throws BadFileException if the path is null or empty
     */

    public static void validateFilePath(String path) throws BadFileException {
        if (isStringNullOrEmpty(path)) {
            throw new BadFileException();
        }
    }

    /**
     * Chec if JPanel contains any empty TextFields.
     * @param panel input JPanel object
     * @return if panel contains empty field
     */

    public static boolean validateNonEmptyTextFields(JPanel panel) {
        boolean valid = true;
        for (Component c : panel.getComponents()) {
            if (c instanceof JPanel) {
                if (!validateNonEmptyTextFields((JPanel) c)) {
                    valid = false;
                }
            }
            if (c instanceof JTextField) {
                final String text = ((JTextField) c).getText();
                if (isStringNullOrEmpty(text)) {
                    valid = false;
                }
            }
        }
        return valid;
    }

    private static boolean isStringNullOrEmpty(String path) {
        return path == null || path.trim().isEmpty();
    }
}
