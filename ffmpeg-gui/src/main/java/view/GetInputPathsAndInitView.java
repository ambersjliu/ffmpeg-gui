package view;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.get_paths_and_init.GetInputPathsAndInitController;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitState;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitViewModel;
import lombok.Setter;

/**
 * View for getting the paths to the FFmpeg and FFprobe binaries, then initializing.
 */
public class GetInputPathsAndInitView extends JPanel implements PropertyChangeListener {

    private final GetInputPathsAndInitViewModel getInputPathsAndInitViewModel;
    @Setter
    private GetInputPathsAndInitController getInputPathsAndInitController;

    private final JLabel hyperlinkLabel = new JLabel();
    private final JButton ffmpegButton;
    private final JButton ffprobeButton;

    private final JLabel ffprobePathField = new JLabel();
    private final JLabel ffmpegPathField = new JLabel();

    private final JLabel pathErrorfield = new JLabel();

    private final FileChooserDialog fileChooserDialog = new FileChooserDialog();

    public GetInputPathsAndInitView(GetInputPathsAndInitViewModel getInputPathsAndInitViewModel) {
        this.getInputPathsAndInitViewModel = getInputPathsAndInitViewModel;
        this.getInputPathsAndInitViewModel.addPropertyChangeListener(this);

        // Title at the top.
        final JLabel title = new JLabel(GetInputPathsAndInitViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Instructions to install FFMPEG, with a provided clickable hyperlink
        final JLabel instructionLabel = new JLabel(GetInputPathsAndInitViewModel.INTRUCTION_LABEL);
        hyperlinkLabel.setText(GetInputPathsAndInitViewModel.HYPERLINK_LABEL);

        // Directs the user to locate FFMPEG from the computer, with a button that will launch a file explorer dialog
        final JLabel ffmpegLabel = new JLabel(GetInputPathsAndInitViewModel.FFMPEG_LABEL);
        ffmpegButton = new JButton(GetInputPathsAndInitViewModel.BROWSE_TEXT);
        final LabelButtonPanel ffmpegBrowse = new LabelButtonPanel(ffmpegLabel, ffmpegButton);

        // Directs the user to locate FFPROBE from the computer, with a button that will launch a file explorer dialog
        final JLabel ffprobeLabel = new JLabel(GetInputPathsAndInitViewModel.FFPROBE_LABEL);
        ffprobeButton = new JButton(GetInputPathsAndInitViewModel.BROWSE_TEXT);
        final LabelButtonPanel ffprobeBrowse = new LabelButtonPanel(ffprobeLabel, ffprobeButton);

        // Once the files are selected, the next button allows the user to move on with the program.
        final JButton nextButton = new JButton(GetInputPathsAndInitViewModel.NEXT_TEXT);
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ffmpegButton.addActionListener(
                evt -> {
                    if (evt.getSource() == ffmpegButton) {
                        final GetInputPathsAndInitState currentState = getInputPathsAndInitViewModel.getState();
                        currentState.setFfmpegPath(fileChooserDialog.takeFilePath());
                        getInputPathsAndInitViewModel.firePropertyChanged();
                    }
                }
        );

        ffprobeButton.addActionListener(
                evt -> {
                    if (evt.getSource() == ffprobeButton) {
                        final GetInputPathsAndInitState currentState = getInputPathsAndInitViewModel.getState();
                        currentState.setFfprobePath(fileChooserDialog.takeFilePath());
                        getInputPathsAndInitViewModel.firePropertyChanged();

                    }
                });

        hyperlinkLabel.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            Desktop.getDesktop().browse(new URI("https://www.ffmpeg.org/download.html#build-windows"));
                        }
                        catch (IOException | URISyntaxException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );

        nextButton.addActionListener(
                evt -> {
                    final GetInputPathsAndInitState currentState = getInputPathsAndInitViewModel.getState();
                    getInputPathsAndInitController.execute(currentState.getFfmpegPath(), currentState.getFfprobePath());
                }
        );

        final JPanel instructionPanel = new JPanel();
        instructionPanel.add(instructionLabel);
        instructionPanel.add(hyperlinkLabel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        pathErrorfield.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);
        this.add(instructionPanel);
        this.add(ffmpegBrowse);
        this.add(ffmpegPathField);
        this.add(ffprobeBrowse);
        this.add(ffprobePathField);
        this.add(pathErrorfield);
        this.add(nextButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final GetInputPathsAndInitState currentState = getInputPathsAndInitViewModel.getState();
        pathErrorfield.setText(currentState.getPathError());
        ffmpegPathField.setText(currentState.getFfmpegPath());
        ffprobePathField.setText(currentState.getFfprobePath());
    }
}
