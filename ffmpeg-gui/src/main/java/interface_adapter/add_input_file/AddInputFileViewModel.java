package interface_adapter.add_input_file;

import interface_adapter.ViewModel;

/**
 * View model for add input file use case.
 */

public class AddInputFileViewModel extends ViewModel<AddInputFileState> {
    public static final String CHOOSE_FILE_LABEL = "Choose a file to convert:";
    public static final String BROWSE_BUTTON_LABEL = "Browse";
    public static final String NEXT_BUTTON_LABEL = "Next";
    public static final int BORDER_LAYOUT_HORIZONTAL = 5;
    public static final int BORDER_LAYOUT_VERTICAL = 5;
    public static final int BORDER_PADDING_TOP = 10;
    public static final int BORDER_PADDING_BOTTOM = 10;
    public static final int BORDER_PADDING_LEFT = 10;
    public static final int BORDER_PADDING_RIGHT = 10;
    public static final int FIRST_ROW_GRID_CELL_PADDING_TOP = 5;
    public static final int FIRST_ROW_GRID_CELL_PADDING_LEFT = 5;
    public static final int FIRST_ROW_GRID_CELL_PADDING_BOTTOM = 5;
    public static final int FIRST_ROW_GRID_CELL_PADDING_RIGHT = 5;
    public static final int SOURCE_FILE_LOCATION_X = 0;
    public static final int SOURCE_FILE_LOCATION_Y = 0;
    public static final int SOURCE_FILE_WEIGHT = 0;
    public static final int TEXT_FIELD_LOCATION_X = 1;
    public static final int TEXT_FIELD_WEIGHT = 1;
    public static final int BROWSE_BUTTON_LOCATION_X = 2;
    public static final int BROWSE_BUTTON_WEIGHT = 0;
    public static final int NEXT_BUTTON_LOCATION_X = 1;
    public static final int NEXT_BUTTON_LOCATION_Y = 1;
    public static final int SECOND_ROW_GRID_CELL_PADDING_TOP = 20;
    public static final int SECOND_ROW_GRID_CELL_PADDING_LEFT = 5;
    public static final int SECOND_ROW_GRID_CELL_PADDING_BOTTOM = 5;
    public static final int SECOND_ROW_GRID_CELL_PADDING_RIGHT = 5;
    public static final int ERROR_MESSAGE_LOCATION_Y = 2;
    public static final int ERROR_MESSAGE_WIDTH = 3;
    public static final int COMPONENT_WIDTH = 400;
    public static final int COMPONENT_HEIGHT = 150;

    public AddInputFileViewModel() {
        super("add input file");
        setState(new AddInputFileState());
    }
}
