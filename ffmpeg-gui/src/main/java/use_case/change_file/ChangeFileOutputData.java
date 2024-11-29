package use_case.change_file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Output Data from the change file use case interactor.
 */

@Getter
@AllArgsConstructor
public class ChangeFileOutputData {
    @Setter
    private String path;
    private boolean useCaseFailed;

}
