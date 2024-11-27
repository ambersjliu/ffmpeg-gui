package view;

import constant.AppConstants;
import interface_adapter.change_file.ChangeFileController;
import interface_adapter.convert_audio_file.ConvertAudioFileController;
import interface_adapter.convert_audio_file.ConvertAudioFileState;
import interface_adapter.convert_audio_file.ConvertAudioFileViewModel;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Objects;

public class ConvertAudioFileView extends JPanel implements ActionListener, PropertyChangeListener {

    private final ConvertAudioFileViewModel convertAudioFileViewModel;
    
    @Setter
    private ConvertAudioFileController convertAudioFileController;
    @Setter
    private ChangeFileController changeFileController;

    private final JButton fileChangeButton;

    private final JButton convertButton;

    private final JComboBox<String> outputFormatDropdown;

    private final JTextField startTimeHour = new JTextField(4);
    private final JTextField startTimeMinute = new JTextField(4);
    private final JTextField startTimeSecond = new JTextField(4);

    private final JTextField endTimeHour = new JTextField(4);
    private final JTextField endTimeMinute = new JTextField(4);
    private final JTextField endTimeSecond = new JTextField(4);

    private final JComboBox<String> audioCodecDropdown;

    private final JSpinner audioBitrate = new JSpinner(new SpinnerNumberModel(0, 0, 100_000_000, 1));

    private final JSpinner numberOfChannel = new JSpinner();

    private final JTextField sampleRate = new JTextField(14);

    private final JButton saveAsDestination;

    private final JFileChooser fileChooserDialog = new JFileChooser();

    private final JLabel outputPath = new JLabel();

    private final JLabel errorField = new JLabel();

    private final JLabel successField = new JLabel();

    public ConvertAudioFileView(ConvertAudioFileViewModel convertAudioFileViewModel) {
        this.convertAudioFileViewModel = convertAudioFileViewModel;
        this.convertAudioFileViewModel.addPropertyChangeListener(this);

        //Title
        final JLabel title = new JLabel(ConvertAudioFileViewModel.TITLE_LABEL);

        //Change file
        fileChangeButton = new JButton(ConvertAudioFileViewModel.FILE_CHANGE_LABEL);

        //Encode button
        convertButton = new JButton(ConvertAudioFileViewModel.START_ENCODE_LABEL);

        //output format
        JPanel outputFormatField = new JPanel();
        JLabel outputFormatLabel = new JLabel(ConvertAudioFileViewModel.OUTPUT_FORMAT_LABEL);
        outputFormatDropdown = new JComboBox<>(ConvertAudioFileViewModel.FILE_FORMAT);
        outputFormatField.add(outputFormatLabel);
        outputFormatField.add(outputFormatDropdown);

        //start time
        JPanel startTimeField = new JPanel();
        JLabel startTimeLabel = new JLabel(ConvertAudioFileViewModel.START_TIME_LABEL);
        startTimeField.add(startTimeLabel);
        startTimeField.add(startTimeHour);
        startTimeField.add(new JLabel(":"));
        startTimeField.add(startTimeMinute);
        startTimeField.add(new JLabel(":"));
        startTimeField.add(startTimeSecond);

        //end time
        JPanel endTimeField = new JPanel();
        JLabel endTimeLabel = new JLabel(ConvertAudioFileViewModel.END_TIME_LABEL);
        endTimeField.add(endTimeLabel);
        endTimeField.add(endTimeHour);
        endTimeField.add(new JLabel(":"));
        endTimeField.add(endTimeMinute);
        endTimeField.add(new JLabel(":"));
        endTimeField.add(endTimeSecond);

        //audio encoder
        JPanel audioCodecField = new JPanel();
        JLabel audioCodecLabel = new JLabel(ConvertAudioFileViewModel.AUDIO_CODEC_LABEL);
        audioCodecDropdown = new JComboBox<>(ConvertAudioFileViewModel.AUDIO_CODEC);
        audioCodecField.add(audioCodecLabel);
        audioCodecField.add(audioCodecDropdown);

        //audio bitrate
        JPanel audioBitrateField = new JPanel();
        JLabel audioBitrateLabel = new JLabel(ConvertAudioFileViewModel.AUDIO_BITRATE_LABEL);
        audioBitrate.setPreferredSize(new Dimension(75, 20));
        audioBitrateField.add(audioBitrateLabel);
        audioBitrateField.add(audioBitrate);

        //channels
        JPanel numberOfChannelField = new JPanel();
        JLabel numberOfChannelLabel = new JLabel(ConvertAudioFileViewModel.CHANNEL_LABEL);
        numberOfChannelField.add(numberOfChannelLabel);
        numberOfChannelField.add(numberOfChannel);

        //Sample rate
        JPanel sampleRateField = new JPanel();
        JLabel sampleRateLabel = new JLabel(ConvertAudioFileViewModel.SAMPLE_RATE_LABEL);
        sampleRateField.add(sampleRateLabel);
        sampleRateField.add(sampleRate);

        //save as
        JLabel saveAsDestinationLabel = new JLabel(ConvertAudioFileViewModel.SAVE_AS_DESTINATION_LABEL);
        saveAsDestination = new JButton(ConvertAudioFileViewModel.BROWSE_LABEL);
        LabelButtonPanel saveAsDestinationField = new LabelButtonPanel(saveAsDestinationLabel, saveAsDestination);

        //error field
        errorField.setForeground(AppConstants.ERROR_COLOR);

        //success field
        successField.setForeground(AppConstants.SUCCESS_COLOR);

        convertButton.addActionListener(
                e -> {
                    if (e.getSource().equals(convertButton)) {
                        final ConvertAudioFileState currentState = convertAudioFileViewModel.getState();
                        updateState(currentState);
                        currentState.setConversionErrorMessage("");
                        currentState.setConversionSuccessMessage("");
                        convertAudioFileViewModel.firePropertyChanged();
                        convertAudioFileController.execute(currentState);
                    }
                }
        );

        fileChangeButton.addActionListener(
                e -> {
                    if (e.getSource().equals(fileChangeButton)) {
                        final ConvertAudioFileState currentState = convertAudioFileViewModel.getState();
                        this.changeFileController.execute(currentState.getInputFilePath());
                    }
                }
        );

        saveAsDestination.addActionListener(
                e -> {
                    if (e.getSource().equals(saveAsDestination)) {
                        fileChooserDialog.showSaveDialog(this);
                        final ConvertAudioFileState currentState = convertAudioFileViewModel.getState();
                        currentState.setOutputFilePath(fileChooserDialog.getSelectedFile().getAbsolutePath());
                        convertAudioFileViewModel.firePropertyChanged();
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
        this.add(audioCodecField);
        this.add(audioBitrateField);
        this.add(numberOfChannelField);
        this.add(sampleRateField);
        this.add(saveAsDestinationField);
        this.add(outputPath);
        this.add(errorField);
        this.add(successField);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    private void updateState(ConvertAudioFileState currentState) {
        String currentFormat = Objects.requireNonNull(outputFormatDropdown.getSelectedItem()).toString();
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

        currentState.setAudioCodecName(Objects.requireNonNull(audioCodecDropdown.getSelectedItem()).toString());
        currentState.setAudioBitRate(Integer.parseInt(audioBitrate.getValue().toString()));
        currentState.setNumAudioChannels(Integer.parseInt(numberOfChannel.getValue().toString()));
        currentState.setAudioSampleRate(Long.parseLong(sampleRate.getText()));
    }


    private void addOutputFormatDropdownListener() {
        outputFormatDropdown.addActionListener(
                e -> {
                    String currentFormat = Objects.requireNonNull(outputFormatDropdown.getSelectedItem()).toString();
                    final ConvertAudioFileState currentState = convertAudioFileViewModel.getState();
                    String path = currentState.getOutputFilePath();
                    path = path.substring(0, path.lastIndexOf('.')) + '.' + currentFormat;
                    currentState.setOutputFilePath(path);
                    currentState.setOutputFormatName(currentFormat);
                    convertAudioFileViewModel.setState(currentState);
                    convertAudioFileViewModel.firePropertyChanged();
                }
        );
    }
    

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ConvertAudioFileState currentState = convertAudioFileViewModel.getState();
        outputPath.setText(currentState.getOutputFilePath());
        fileChooserDialog.setSelectedFile(new File(currentState.getOutputFilePath()));
        errorField.setText(currentState.getConversionErrorMessage());
        successField.setText(currentState.getConversionSuccessMessage());
    }

    public void init(){
        final ConvertAudioFileState currentState = convertAudioFileViewModel.getState();
        outputFormatDropdown.setSelectedItem(currentState.getOutputFormatName());
        startTimeHour.setText(Integer.toString(currentState.getStartTimeHours()));
        startTimeMinute.setText(String.valueOf(currentState.getStartTimeMinutes()));
        startTimeSecond.setText(String.valueOf(currentState.getStartTimeSeconds()));
        endTimeHour.setText(String.valueOf(currentState.getEndTimeHours()));
        endTimeMinute.setText(String.valueOf(currentState.getEndTimeMinutes()));
        endTimeSecond.setText(String.valueOf(currentState.getEndTimeSeconds()));
        audioCodecDropdown.setSelectedItem(currentState.getAudioCodecName());
        numberOfChannel.setValue(currentState.getNumAudioChannels());
        audioBitrate.setValue(currentState.getAudioBitRate());
        sampleRate.setText(String.valueOf(currentState.getAudioSampleRate()));
        outputPath.setText(String.valueOf(currentState.getOutputFilePath()));
        fileChooserDialog.setSelectedFile(new File(currentState.getOutputFilePath()));
    }

}
