package view;

import javax.swing.JFileChooser;

/**
 * Define file chooser using JFileChooser.
 */

public class FileChooserDialog {
    /**
     * Take file path from JFileChooser.
     * @return file path from JFileChooser
     */
    public String takeFilePath() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        String filePath = "";

        final int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
        }

        return filePath;
    }
}
