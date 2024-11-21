package view;

import interface_adapter.convert_video_file.ConvertVideoFileController;
import interface_adapter.convert_video_file.ConvertVideoFileState;
import interface_adapter.convert_video_file.ConvertVideoFileViewModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ConvertVideoFileView extends JPanel implements ActionListener, PropertyChangeListener {
    @Getter
    private final String viewName = "ConvertVideoFileView";

    private final ConvertVideoFileViewModel convertVideoFileViewModel;
    @Setter
    private ConvertVideoFileController convertVideoFileController;

    //some J objects
    private final JButton fileChangeButton;
    private final JButton convertButton;

//    private final JLabel fileName = new JLabel();

    private final JComboBox<String> outputFormatDropdown;


    private final JTextField startTimeHour = new JTextField(4);
    private final JTextField startTimeMinute = new JTextField(4);
    private final JTextField startTimeSecond = new JTextField(4);

    private final JTextField endTimeHour = new JTextField(4);
    private final JTextField endTimeMinute = new JTextField(4);
    private final JTextField endTimeSecond = new JTextField(4);

    private final JTextField dimensionWidth = new JTextField(7);
    private final JTextField dimensionHeight = new JTextField(7);

    private final JSpinner frameRate = new JSpinner();

    private final JComboBox<String> videoCodecDropdown;

    private final JComboBox<String> audioCodecDropdown;

    private final JSpinner bitrate = new JSpinner();

    private final JTextField numberOfChannel = new JTextField(14);

    private final JTextField sampleRate = new JTextField(14);

    private final JButton saveAsDestination;


    public ConvertVideoFileView(ConvertVideoFileViewModel convertVideoFileViewModel) {
        this.convertVideoFileViewModel = convertVideoFileViewModel;
        this.convertVideoFileViewModel.addPropertyChangeListener(this);

        //Title
        final JLabel title = new JLabel(ConvertVideoFileViewModel.TITLE_LABEL);

        //Change file
        fileChangeButton = new JButton(ConvertVideoFileViewModel.FILE_CHANGE_LABEL);

        //Encode button
        convertButton = new JButton(ConvertVideoFileViewModel.START_ENCODE_LABEL);

        //output format
        JPanel outputFormatField = new JPanel();
        JLabel outputFormatLabel = new JLabel(ConvertVideoFileViewModel.OUTPUT_FORMAT_LABEL);
        outputFormatDropdown = new JComboBox<>(ConvertVideoFileViewModel.FILE_FORMAT);
        outputFormatField.add(outputFormatLabel);
        outputFormatField.add(outputFormatDropdown);

        //start time
        JPanel startTimeField = new JPanel();
        JLabel startTimeLabel = new JLabel(ConvertVideoFileViewModel.START_TIME_LABEL);
        startTimeField.add(startTimeLabel);
        startTimeField.add(startTimeHour);
        startTimeField.add(new JLabel(":"));
        startTimeField.add(startTimeMinute);
        startTimeField.add(new JLabel(":"));
        startTimeField.add(startTimeSecond);

        //end time
        JPanel endTimeField = new JPanel();
        JLabel endTimeLabel = new JLabel(ConvertVideoFileViewModel.END_TIME_LABEL);
        endTimeField.add(endTimeLabel);
        endTimeField.add(endTimeHour);
        endTimeField.add(new JLabel(":"));
        endTimeField.add(endTimeMinute);
        endTimeField.add(new JLabel(":"));
        endTimeField.add(endTimeSecond);

        //dimension
        JPanel dimensionField = new JPanel();
        JLabel dimensionLabel = new JLabel(ConvertVideoFileViewModel.DIMENSION_LABEL);
        dimensionField.add(dimensionLabel);
        dimensionField.add(dimensionWidth);
        dimensionField.add(new JLabel("X"));
        dimensionField.add(dimensionHeight);

        //frame rate
        JPanel frameRateField = new JPanel();
        JLabel frameRateLabel = new JLabel(ConvertVideoFileViewModel.FRAME_RATE_LABEL);
        frameRate.setPreferredSize(new Dimension(75, 20));
        frameRateField.add(frameRateLabel);
        frameRateField.add(frameRate);

        //video encoder
        JPanel videoCodecField = new JPanel();
        JLabel videoCodecLabel = new JLabel(ConvertVideoFileViewModel.VIDEO_CODEC_LABEL);
        videoCodecDropdown = new JComboBox<>(ConvertVideoFileViewModel.VIDEO_CODEC);
        videoCodecField.add(videoCodecLabel);
        videoCodecField.add(videoCodecDropdown);

        //audio encoder
        JPanel audioCodecField = new JPanel();
        JLabel audioCodecLabel = new JLabel(ConvertVideoFileViewModel.AUDIO_CODEC_LABEL);
        audioCodecDropdown = new JComboBox<>(ConvertVideoFileViewModel.AUDIO_CODEC);
        audioCodecField.add(audioCodecLabel);
        audioCodecField.add(audioCodecDropdown);

        //bitrate
        JPanel bitrateField = new JPanel();
        JLabel bitrateLabel = new JLabel(ConvertVideoFileViewModel.BITRATE_LABEL);
        bitrate.setPreferredSize(new Dimension(75, 20));
        bitrateField.add(bitrateLabel);
        bitrateField.add(bitrate);

        //channels
        JPanel numberOfChannelField = new JPanel();
        JLabel numberOfChannelLabel = new JLabel(ConvertVideoFileViewModel.CHANNEL_LABEL);
        numberOfChannelField.add(numberOfChannelLabel);
        numberOfChannelField.add(numberOfChannel);

        //Sample rate
        JPanel sampleRateField = new JPanel();
        JLabel sampleRateLabel = new JLabel(ConvertVideoFileViewModel.SAMPLE_RATE_LABEL);
        sampleRateField.add(sampleRateLabel);
        sampleRateField.add(sampleRate);

        //save as
        JLabel saveAsDestinationLabel = new JLabel(ConvertVideoFileViewModel.SAVE_AS_DESTINATION_LABEL);
        saveAsDestination =new JButton(ConvertVideoFileViewModel.BROWSE_LABEL);
        LabelButtonPanel saveAsDestinationField = new LabelButtonPanel(saveAsDestinationLabel, saveAsDestination);

        convertButton.addActionListener(
                e -> {
                    final ConvertVideoFileState currentState = convertVideoFileViewModel.getState();
                    convertVideoFileController.execute();
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(fileChangeButton);
        this.add(convertButton);
        this.add(outputFormatField);
        this.add(startTimeField);
        this.add(endTimeField);
        this.add(dimensionField);
        this.add(frameRateField);
        this.add(videoCodecField);
        this.add(audioCodecField);
        this.add(bitrateField);
        this.add(numberOfChannelField);
        this.add(sampleRateField);
        this.add(saveAsDestinationField);
    }

    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
