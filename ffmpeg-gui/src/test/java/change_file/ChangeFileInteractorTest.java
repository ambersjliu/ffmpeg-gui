package change_file;

import org.junit.jupiter.api.Test;
import use_case.change_file.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ChangeFileInteractorTest {
    @Test
    void successTest(){
        ChangeFileInputData inputData = new ChangeFileInputData("C:\\Users\\yinmi\\OneDrive\\Desktop\\Travel photo\\MVI5105.mp4");
        ChangeFileOutputBoundary successPresenter = new ChangeFileOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeFileOutputData changeFileOutputData) {
                assertEquals("C:\\Users\\yinmi\\OneDrive\\Desktop\\Travel photo\\MVI5105.mp4", changeFileOutputData.getPath());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("WHY ARE YOU FAILING :sob:");
            }
        };

        ChangeFileInputBoundary interactor = new ChangeFileInteractor(successPresenter);
        interactor.execute(inputData);
   }
}
