package use_case.change_file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.index.qual.GTENegativeOne;

@Getter
@AllArgsConstructor
public class ChangeFileOutputData {
    @Setter
    private String Path;
    private boolean useCaseFailed;

}
