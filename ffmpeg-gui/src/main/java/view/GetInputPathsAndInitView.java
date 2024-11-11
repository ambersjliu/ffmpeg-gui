package view;

import interface_adapter.get_paths_and_init.GetInputPathsAndInitViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GetInputPathsAndInitView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "GetterView";

    private final GetInputPathsAndInitViewModel getInputPathsAndInitViewModel;
    private final JLabel intructionLabel = new JLabel();
    private final JLabel hyperlinkLabel = new JLabel();
    private final JLabel ffmpegLabel;
    private final JLabel ffprobeLabel;

    private final JButton ffmpegButton;
    private final JButton ffprobeButton;

    public GetInputPathsAndInitView(GetInputPathsAndInitViewModel getInputPathsAndInitViewModel) {
        this.getInputPathsAndInitViewModel = getInputPathsAndInitViewModel;
        final JLabel title = new JLabel(GetInputPathsAndInitViewModel.TITLE_LABEL);

        intructionLabel.setText(GetInputPathsAndInitViewModel.INTRUCTION_LABEL);
        hyperlinkLabel.setText(GetInputPathsAndInitViewModel.HYPERLINK_LABEL);

        ffmpegLabel = new JLabel(GetInputPathsAndInitViewModel.FFMPEG_LABEL);
        ffmpegButton = new JButton("Browse");
        final LabelButtonPanel ffmpegBrowse = new LabelButtonPanel(ffmpegLabel, ffmpegButton);

        ffprobeLabel = new JLabel(GetInputPathsAndInitViewModel.FFPROBE_LABEL);
        ffprobeButton = new JButton("Browse");
        final LabelButtonPanel ffprobeBrowse = new LabelButtonPanel(ffprobeLabel, ffprobeButton);

        final JButton nextButton = new JButton("Next");

        ffmpegButton.addActionListener(this); //TODO buttons should do something
        ffprobeButton.addActionListener(this);
        hyperlinkLabel.addMouseListener(new MouseAdapter() {});
        nextButton.addActionListener(this);

        JPanel intructionPanel = new JPanel();
        intructionPanel.add(intructionLabel);
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
