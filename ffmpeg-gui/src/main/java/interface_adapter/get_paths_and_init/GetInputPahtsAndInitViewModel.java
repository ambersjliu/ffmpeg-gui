package interface_adapter.get_paths_and_init;
import interface_adapter.ViewModel;

public class GetInputPahtsAndInitViewModel extends ViewModel<GetInputPahtsAndInitState> {
    public GetInputPahtsAndInitViewModel() {
        super("Getter view");
        setState(new GetInputPahtsAndInitState());
    }
}
