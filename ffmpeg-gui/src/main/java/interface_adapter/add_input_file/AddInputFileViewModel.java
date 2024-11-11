package interface_adapter.add_input_file;

import interface_adapter.ViewModel;

public class AddInputFileViewModel extends ViewModel {
    public AddInputFileViewModel() {
        super("add input file");
        setState(new AddInputFileState());
    }
}
