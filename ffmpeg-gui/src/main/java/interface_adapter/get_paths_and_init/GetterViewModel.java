package interface_adapter.get_paths_and_init;
import interface_adapter.ViewModel;

public class GetterViewModel extends ViewModel<GetterState> {
    public GetterViewModel() {
        super("Getter view");
        setState(new GetterState());
    }
}
