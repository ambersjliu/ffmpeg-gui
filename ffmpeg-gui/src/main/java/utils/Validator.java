package utils;

import exceptions.BadFileException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validator {
    private Validator() {
    }

    public static void doesEndOfPathContain(String filePath, String targetString) throws IOException {
        Path path = Paths.get(filePath);
        Path endOfPath = path.getFileName();
        if (endOfPath == null || !endOfPath.toString().contains(targetString)) {
            throw new IOException();
        }
    }

    public static void validateFilePaths(String... paths) throws BadFileException {
        for (String path : paths) {
            validateFilePath(path);
        }
    }

    public static void validateFilePath(String path) throws BadFileException {
        if (isStringNullOrEmpty(path))
            throw new BadFileException();
    }

    public static boolean validateNonEmptyTextFields(JPanel panel){
        for (Component c : panel.getComponents()) {
            if (c instanceof JPanel) {
                if (!validateNonEmptyTextFields((JPanel) c)) {
                    return false;
                }
            }
            if (c instanceof JTextField){
                String text = ((JTextField) c).getText();
                if (isStringNullOrEmpty(text)){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isStringNullOrEmpty(String path) {
        return (path == null || path.trim().isEmpty());
    }


}
