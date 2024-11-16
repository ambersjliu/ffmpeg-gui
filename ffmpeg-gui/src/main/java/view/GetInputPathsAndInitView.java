package view;

import interface_adapter.get_paths_and_init.GetInputPathsAndInitState;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GetInputPathsAndInitView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "GetInputPathsAndInitView";

    private final GetInputPathsAndInitViewModel getInputPathsAndInitViewModel;
    private final JLabel hyperlinkLabel = new JLabel();
    private final JButton ffmpegButton;
    private final JButton ffprobeButton;

    private final FileChooserDialog fileChooserDialog = new FileChooserDialog();

    public GetInputPathsAndInitView(GetInputPathsAndInitViewModel getInputPathsAndInitViewModel) {
        this.getInputPathsAndInitViewModel = getInputPathsAndInitViewModel;
        this.getInputPathsAndInitViewModel.addPropertyChangeListener(this);

        //Title at the top.
        final JLabel title = new JLabel(GetInputPathsAndInitViewModel.TITLE_LABEL);

        //Instructions to install FFMPEG, with a provided clickable hyperlink
        JLabel instructionLabel = new JLabel(GetInputPathsAndInitViewModel.INTRUCTION_LABEL);
        hyperlinkLabel.setText(GetInputPathsAndInitViewModel.HYPERLINK_LABEL);

        //Directs the user to locate FFMPEG from the computer, with a button that will launch a file explorer dialog
        JLabel ffmpegLabel = new JLabel(GetInputPathsAndInitViewModel.FFMPEG_LABEL);
        ffmpegButton = new JButton(GetInputPathsAndInitViewModel.BROWSE_TEXT);
        final LabelButtonPanel ffmpegBrowse = new LabelButtonPanel(ffmpegLabel, ffmpegButton);

        //Directs the user to locate FFPROBE from the computer, with a button that will launch a file explorer dialog
        JLabel ffprobeLabel = new JLabel(GetInputPathsAndInitViewModel.FFPROBE_LABEL);
        ffprobeButton = new JButton(GetInputPathsAndInitViewModel.BROWSE_TEXT);
        final LabelButtonPanel ffprobeBrowse = new LabelButtonPanel(ffprobeLabel, ffprobeButton);

        //Once the files are selected, the next button allows the user to move on with the program.
        final JButton nextButton = new JButton(GetInputPathsAndInitViewModel.NEXT_TEXT);

        ffmpegButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == ffmpegButton) {
                        final GetInputPathsAndInitState currentState = getInputPathsAndInitViewModel.getState();
                        currentState.setFfmpegPath(fileChooserDialog.takeFilePath());
                    }
                }
            }
        );

        ffprobeButton.addActionListener(this); //TODO buttons should do something
        hyperlinkLabel.addMouseListener(new MouseAdapter() {});
        nextButton.addActionListener(this);

        JPanel intructionPanel = new JPanel();
        intructionPanel.add(instructionLabel);
        intructionPanel.add(hyperlinkLabel);

        this.add(title);
        this.add(intructionPanel);
        this.add(ffmpegBrowse);
        this.add(ffprobeBrowse);
        this.add(nextButton);
    }

    public void actionPerformed(ActionEvent evt){} //Todo

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
