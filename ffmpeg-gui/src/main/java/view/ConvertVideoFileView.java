package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import constant.AppConstants;
import constant.NumericalConstants;
import interface_adapter.change_file.ChangeFileController;
import interface_adapter.convert_video_file.ConvertVideoFileController;
import interface_adapter.convert_video_file.ConvertVideoFileState;
import interface_adapter.convert_video_file.ConvertVideoFileViewModel;
import lombok.Setter;
import utils.Validator;

/**
 * Class for the view of convert video files.
 */
public class ConvertVideoFileView extends JPanel implements ActionListener, PropertyChangeListener {

    private final ConvertVideoFileViewModel convertVideoFileViewModel;
    @Setter
    private ConvertVideoFileController convertVideoFileController;
    @Setter
    private ChangeFileController changeFileController;

    private final JButton fileChangeButton;

    private final JButton convertButton;

    private final JComboBox<String> outputFormatDropdown;

    private final JTextField startTimeHour = new JTextField(ConvertVideoFileViewModel.COLUMN_OF_TEXTFIELD_SHORT);
    private final JTextField startTimeMinute = new JTextField(ConvertVideoFileViewModel.COLUMN_OF_TEXTFIELD_SHORT);
    private final JTextField startTimeSecond = new JTextField(ConvertVideoFileViewModel.COLUMN_OF_TEXTFIELD_SHORT);

    private final JTextField endTimeHour = new JTextField(ConvertVideoFileViewModel.COLUMN_OF_TEXTFIELD_SHORT);
    private final JTextField endTimeMinute = new JTextField(ConvertVideoFileViewModel.COLUMN_OF_TEXTFIELD_SHORT);
    private final JTextField endTimeSecond = new JTextField(ConvertVideoFileViewModel.COLUMN_OF_TEXTFIELD_SHORT);

    private final JTextField dimensionWidth = new JTextField(ConvertVideoFileViewModel.COLUMN_OF_TEXTFIELD_LONG);
    private final JTextField dimensionHeight = new JTextField(ConvertVideoFileViewModel.COLUMN_OF_TEXTFIELD_LONG);

    private final JSpinner frameRate = new JSpinner(new SpinnerNumberModel(0, 0,
            NumericalConstants.MAX_FRAMERATE, 0.5));

    private final JSpinner videoBitrate = new JSpinner(new SpinnerNumberModel(0, 0,
            NumericalConstants.MAX_BITRATE, 1));

    private final JComboBox<String> videoCodecDropdown;

    private final JComboBox<String> audioCodecDropdown;

    private final JSpinner audioBitrate = new JSpinner(new SpinnerNumberModel(0, 0,
            NumericalConstants.MAX_BITRATE, 1));

    private final JSpinner numberOfChannel = new JSpinner();

    private final JTextField sampleRate = new JTextField(14);

    private final JButton saveAsDestination;

    private final JFileChooser fileChooserDialog = new JFileChooser();

    private final JLabel outputPath = new JLabel();

    private final JLabel errorField = new JLabel();

    private final JLabel successField = new JLabel();

    private final JLabel warningField = new JLabel();

    public ConvertVideoFileView(ConvertVideoFileViewModel convertVideoFileViewModel) {
        this.convertVideoFileViewModel = convertVideoFileViewModel;
        this.convertVideoFileViewModel.addPropertyChangeListener(this);

        // Title
        final JLabel title = new JLabel(ConvertVideoFileViewModel.TITLE_LABEL);

        // Change file
        fileChangeButton = new JButton(ConvertVideoFileViewModel.FILE_CHANGE_LABEL);

        // Encode button
        convertButton = new JButton(ConvertVideoFileViewModel.START_ENCODE_LABEL);

        // Output format
        final JPanel outputFormatField = new JPanel();
        final JLabel outputFormatLabel = new JLabel(ConvertVideoFileViewModel.OUTPUT_FORMAT_LABEL);
        outputFormatDropdown = new JComboBox<>(ConvertVideoFileViewModel.FILE_FORMAT);
        outputFormatField.add(outputFormatLabel);
        outputFormatField.add(outputFormatDropdown);

        // Start time
        final JPanel startTimeField = new JPanel();
        final JLabel startTimeLabel = new JLabel(ConvertVideoFileViewModel.START_TIME_LABEL);
        startTimeField.add(startTimeLabel);
        startTimeField.add(startTimeHour);
        startTimeField.add(new JLabel(ConvertVideoFileViewModel.COLON));
        startTimeField.add(startTimeMinute);
        startTimeField.add(new JLabel(ConvertVideoFileViewModel.COLON));
        startTimeField.add(startTimeSecond);

        // end time
        final JPanel endTimeField = new JPanel();
        final JLabel endTimeLabel = new JLabel(ConvertVideoFileViewModel.END_TIME_LABEL);
        endTimeField.add(endTimeLabel);
        endTimeField.add(endTimeHour);
        endTimeField.add(new JLabel(ConvertVideoFileViewModel.COLON));
        endTimeField.add(endTimeMinute);
        endTimeField.add(new JLabel(ConvertVideoFileViewModel.COLON));
        endTimeField.add(endTimeSecond);

        // Dimension
        final JPanel dimensionField = new JPanel();
        final JLabel dimensionLabel = new JLabel(ConvertVideoFileViewModel.DIMENSION_LABEL);
        dimensionField.add(dimensionLabel);
        dimensionField.add(dimensionWidth);
        dimensionField.add(new JLabel("X"));
        dimensionField.add(dimensionHeight);

        // Frame rate
        final JPanel frameRateField = new JPanel();
        final JLabel frameRateLabel = new JLabel(ConvertVideoFileViewModel.FRAME_RATE_LABEL);
        frameRate.setPreferredSize(
                new Dimension(ConvertVideoFileViewModel.SPINNER_WIDTH, ConvertVideoFileViewModel.SPINNER_HEIGHT));
        frameRateField.add(frameRateLabel);
        frameRateField.add(frameRate);

        // Video bitrate
        final JPanel videoBitrateField = new JPanel();
        final JLabel videoBitrateLabel = new JLabel(ConvertVideoFileViewModel.VIDEO_BITRATE_LABEL);
        audioBitrate.setPreferredSize(
                new Dimension(ConvertVideoFileViewModel.SPINNER_WIDTH, ConvertVideoFileViewModel.SPINNER_HEIGHT));
        videoBitrateField.add(videoBitrateLabel);
        videoBitrateField.add(videoBitrate);

        // Video encoder
        final JPanel videoCodecField = new JPanel();
        final JLabel videoCodecLabel = new JLabel(ConvertVideoFileViewModel.VIDEO_CODEC_LABEL);
        videoCodecDropdown = new JComboBox<>(ConvertVideoFileViewModel.VIDEO_CODEC);
        videoCodecField.add(videoCodecLabel);
        videoCodecField.add(videoCodecDropdown);

        // Audio encoder
        final JPanel audioCodecField = new JPanel();
        final JLabel audioCodecLabel = new JLabel(ConvertVideoFileViewModel.AUDIO_CODEC_LABEL);
        audioCodecDropdown = new JComboBox<>(ConvertVideoFileViewModel.AUDIO_CODEC);
        audioCodecField.add(audioCodecLabel);
        audioCodecField.add(audioCodecDropdown);

        // Audio bitrate
        final JPanel audioBitrateField = new JPanel();
        final JLabel audioBitrateLabel = new JLabel(ConvertVideoFileViewModel.AUDIO_BITRATE_LABEL);
        audioBitrate.setPreferredSize(
                new Dimension(ConvertVideoFileViewModel.SPINNER_WIDTH, ConvertVideoFileViewModel.SPINNER_HEIGHT));
        audioBitrateField.add(audioBitrateLabel);
        audioBitrateField.add(audioBitrate);

        // Channels
        final JPanel numberOfChannelField = new JPanel();
        final JLabel numberOfChannelLabel = new JLabel(ConvertVideoFileViewModel.CHANNEL_LABEL);
        numberOfChannelField.add(numberOfChannelLabel);
        numberOfChannelField.add(numberOfChannel);

        // Sample rate
        final JPanel sampleRateField = new JPanel();
        final JLabel sampleRateLabel = new JLabel(ConvertVideoFileViewModel.SAMPLE_RATE_LABEL);
        sampleRateField.add(sampleRateLabel);
        sampleRateField.add(sampleRate);

        // Save as
        final JLabel saveAsDestinationLabel = new JLabel(ConvertVideoFileViewModel.SAVE_AS_DESTINATION_LABEL);
        saveAsDestination = new JButton(ConvertVideoFileViewModel.BROWSE_LABEL);
        final LabelButtonPanel saveAsDestinationField = new LabelButtonPanel(saveAsDestinationLabel, saveAsDestination);

        // Error field
        errorField.setForeground(AppConstants.ERROR_COLOR);

        // success field
        successField.setForeground(AppConstants.SUCCESS_COLOR);

        // warning field
        warningField.setForeground(AppConstants.WARNING_COLOR);

        convertButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(convertButton)) {
                        final ConvertVideoFileState currentState = convertVideoFileViewModel.getState();
                        if (Validator.validateNonEmptyTextFields(this)) {
                            updateState(currentState);
                            currentState.setConversionErrorMessage("");
                            currentState.setConversionSuccessMessage("");
                            currentState.setConversionWarningMessage("");
                            convertVideoFileViewModel.firePropertyChanged();
                            convertVideoFileController.execute(currentState);
                        }
                        else {
                            currentState.setConversionWarningMessage(ConvertVideoFileViewModel.WARNING_MESSAGE);
                            convertVideoFileViewModel.firePropertyChanged();
                        }

                    }
                }
        );

        fileChangeButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(fileChangeButton)) {
                        final ConvertVideoFileState currentState = convertVideoFileViewModel.getState();
                        this.changeFileController.execute(currentState.getInputFilePath());
                    }
                }
        );

        saveAsDestination.addActionListener(
                evt -> {
                    if (evt.getSource().equals(saveAsDestination)) {
                        fileChooserDialog.showSaveDialog(this);
                        final ConvertVideoFileState currentState = convertVideoFileViewModel.getState();
                        currentState.setOutputFilePath(fileChooserDialog.getSelectedFile().getAbsolutePath());
                        convertVideoFileViewModel.firePropertyChanged();
                    }
                }
        );

        addOutputFormatDropdownListener();

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
        this.add(videoBitrateField);
        this.add(audioCodecField);
        this.add(audioBitrateField);
        this.add(numberOfChannelField);
        this.add(sampleRateField);
        this.add(saveAsDestinationField);
        this.add(outputPath);
        this.add(errorField);
        this.add(successField);
        this.add(warningField);
    }

    private void updateState(ConvertVideoFileState currentState) {
        final String currentFormat = Objects.requireNonNull(outputFormatDropdown.getSelectedItem()).toString();
        String path = currentState.getOutputFilePath();
        path = path.substring(0, path.lastIndexOf('.')) + '.' + currentFormat;
        currentState.setOutputFilePath(path);
        currentState.setOutputFormatName(currentFormat);

        currentState.setStartTimeHours(Integer.parseInt(startTimeHour.getText()));
        currentState.setStartTimeMinutes(Integer.parseInt(startTimeMinute.getText()));
        currentState.setStartTimeSeconds(Double.parseDouble(startTimeSecond.getText()));

        currentState.setEndTimeHours(Integer.parseInt(endTimeHour.getText()));
        currentState.setEndTimeMinutes(Integer.parseInt(endTimeMinute.getText()));
        currentState.setEndTimeSeconds(Double.parseDouble(endTimeSecond.getText()));

        currentState.setWidth(Integer.parseInt(dimensionWidth.getText()));
        currentState.setHeight(Integer.parseInt(dimensionHeight.getText()));

        currentState.setFrameRate(Double.parseDouble(frameRate.getValue().toString()));
        currentState.setVideoBitRate(Integer.parseInt(videoBitrate.getValue().toString()));
        currentState.setVideoCodecName(Objects.requireNonNull(videoCodecDropdown.getSelectedItem()).toString());

        currentState.setAudioCodecName(Objects.requireNonNull(audioCodecDropdown.getSelectedItem()).toString());
        currentState.setAudioBitRate(Integer.parseInt(audioBitrate.getValue().toString()));
        currentState.setNumAudioChannels(Integer.parseInt(numberOfChannel.getValue().toString()));
        currentState.setAudioSampleRate(Long.parseLong(sampleRate.getText()));
    }

    private void addOutputFormatDropdownListener() {
        outputFormatDropdown.addActionListener(
                evt -> {
                    final String currentFormat = Objects.requireNonNull(
                            outputFormatDropdown.getSelectedItem()).toString();
                    final ConvertVideoFileState currentState = convertVideoFileViewModel.getState();
                    String path = currentState.getOutputFilePath();
                    path = path.substring(0, path.lastIndexOf('.')) + '.' + currentFormat;
                    currentState.setOutputFilePath(path);
                    currentState.setOutputFormatName(currentFormat);
                    convertVideoFileViewModel.setState(currentState);
                    convertVideoFileViewModel.firePropertyChanged();
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ConvertVideoFileState currentState = convertVideoFileViewModel.getState();
        outputPath.setText(currentState.getOutputFilePath());
        fileChooserDialog.setSelectedFile(new File(currentState.getOutputFilePath()));
        errorField.setText(currentState.getConversionErrorMessage());
        warningField.setText(currentState.getConversionWarningMessage());
        successField.setText(currentState.getConversionSuccessMessage());
    }

    /**
     * Initialize the view.
     */
    public void init() {
        final ConvertVideoFileState currentState = convertVideoFileViewModel.getState();
        outputFormatDropdown.setSelectedItem(currentState.getOutputFormatName());
        startTimeHour.setText(Integer.toString(currentState.getStartTimeHours()));
        startTimeMinute.setText(String.valueOf(currentState.getStartTimeMinutes()));
        startTimeSecond.setText(String.valueOf(currentState.getStartTimeSeconds()));
        endTimeHour.setText(String.valueOf(currentState.getEndTimeHours()));
        endTimeMinute.setText(String.valueOf(currentState.getEndTimeMinutes()));
        endTimeSecond.setText(String.valueOf(currentState.getEndTimeSeconds()));
        dimensionHeight.setText(String.valueOf(currentState.getHeight()));
        dimensionWidth.setText(String.valueOf(currentState.getWidth()));
        frameRate.setValue(currentState.getFrameRate());
        videoBitrate.setValue(currentState.getVideoBitRate());
        videoCodecDropdown.setSelectedItem(currentState.getVideoCodecName());
        audioCodecDropdown.setSelectedItem(currentState.getAudioCodecName());
        numberOfChannel.setValue(currentState.getNumAudioChannels());
        audioBitrate.setValue(currentState.getAudioBitRate());
        sampleRate.setText(String.valueOf(currentState.getAudioSampleRate()));
        outputPath.setText(String.valueOf(currentState.getOutputFilePath()));
        fileChooserDialog.setSelectedFile(new File(currentState.getOutputFilePath()));
    }

}
