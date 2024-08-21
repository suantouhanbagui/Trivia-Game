package adapters.play;

import main.adapters.play.PlayController;
import main.adapters.play.PlayPresenter;
import main.adapters.play.PlayViewModel;
import main.adapters.settings.SettingsPresenter;
import main.adapters.settings.SettingsViewModel;
import main.adapters.start.StartViewModel;
import main.data_access.ResultRecordingDAO;
import main.data_access.TriviaDB;
import main.data_access.TriviaDBInterface;
import main.use_case.play.*;
import main.use_case.settings.SettingsInteractor;
import main.use_case.settings.SettingsOutputBoundary;
import main.view.ViewManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;


import javax.swing.*;

import static org.mockito.Mockito.mock;

class PlayControllerTest {
    private PlayInputBoundary playInputBoundary;
    private PlayOutputBoundary playOutputBoundary;
    private PlayViewModel playViewModel;
    private StartViewModel startViewModel;
    private final JFrame dummyFrame = new JFrame();
    private ViewManager viewManager;
    private SettingsInteractor settingsInteractor;
    private SettingsOutputBoundary settingsOutputBoundary;
    private SettingsViewModel settingsViewModel;
    private TriviaDBInterface triviaDBInterface;
    private ResultRecordingDAO resultRecordingDAO;
    private PlayController playController;
    private final String dummyAnswer = "some answer here";

    @BeforeEach
    void setUp() {
        this.playViewModel = new PlayViewModel();
        this.startViewModel = new StartViewModel();
        this.viewManager = new ViewManager(dummyFrame);
        this.playOutputBoundary = new PlayPresenter(playViewModel, startViewModel, viewManager);

        this.settingsViewModel = new SettingsViewModel();
        this.settingsOutputBoundary = new SettingsPresenter(settingsViewModel, startViewModel, viewManager);
        this.settingsInteractor = new SettingsInteractor(settingsOutputBoundary);

        this.triviaDBInterface = new TriviaDB();
        this.resultRecordingDAO = new ResultRecordingDAO();

    }

    @Test
    void prepareView() {
        this.playInputBoundary = new OnePlayerInteractor(playOutputBoundary, settingsInteractor,
                triviaDBInterface, resultRecordingDAO){
            @Override
            public void prepareView() {
            }
        };
        PlayInputBoundary mockPlayInputBoundary = mock(playInputBoundary.getClass());
        this.playController = new PlayController(mockPlayInputBoundary);
        playController.prepareView();
        Mockito.verify(mockPlayInputBoundary).prepareView();
    }

    @Test
    void execute() {
        this.playInputBoundary = new OnePlayerInteractor(playOutputBoundary, settingsInteractor,
                triviaDBInterface, resultRecordingDAO){
            @Override
            public void execute(PlayInputData playInputData) {
            }
        };
        PlayInputBoundary mockPlayInputBoundary = mock(playInputBoundary.getClass());
        this.playController = new PlayController(mockPlayInputBoundary);
        playController.execute(dummyAnswer);
        Mockito.verify(mockPlayInputBoundary).execute(ArgumentMatchers.any());
    }
}