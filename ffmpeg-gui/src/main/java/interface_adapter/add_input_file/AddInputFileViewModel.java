package interface_adapter.add_input_file;

import interface_adapter.ViewModel;

/**
 * View model for add input file use case.
 */

public class AddInputFileViewModel extends ViewModel<AddInputFileState> {
    public static final String CHOOSE_FILE_LABEL = "Choose a file to convert:";
    public static final String BROWSE_BUTTON_LABEL = "Browse";
    public static final String NEXT_BUTTON_LABEL = "Next";

    public AddInputFileViewModel() {
        super("add input file");
        setState(new AddInputFileState());
    }
}
