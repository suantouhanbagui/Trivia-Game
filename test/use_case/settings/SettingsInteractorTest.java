package use_case.settings;

import com.formdev.flatlaf.FlatDarculaLaf;
import main.adapters.settings.SettingsPresenter;
import main.adapters.settings.SettingsViewModel;
import main.adapters.start.StartViewModel;
import main.entities.QuestionList;
import main.use_case.settings.SettingsDTO;
import main.use_case.settings.SettingsInputData;
import main.use_case.settings.SettingsInteractor;
import main.use_case.settings.SettingsOutputBoundary;
import main.view.ViewManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class SettingsInteractorTest {
    // dummy input
    private String AMOUNT = "15";
    private String CATEGORY = "General Knowledge";
    private String DIFFICULTY = "Easy";
    private String TYPE = "Multiple Choice";
    private String GAMEMODE = "Single Player";
    private String DARKMODE = "Dark Mode";

    private SettingsInteractor settingsInteractor;
    private SettingsViewModel settingsViewModel;
    private StartViewModel startViewModel;
    private ViewManager viewManager;
    private JFrame dummyFrame = new JFrame();

    // helper to allow checking private variables
    public static <T, V> V getPrivateVariableHelper(T obj, String variable) throws IllegalAccessException, NoSuchFieldException {
        Field field = obj.getClass().getDeclaredField(variable);
        field.setAccessible(true);
        return (V) field.get(obj);
    }

    @BeforeEach
    void setUp() {
        this.settingsViewModel = new SettingsViewModel();
        this.startViewModel = new StartViewModel();
        this.viewManager = new ViewManager(dummyFrame);
    }

    @Test
    void prepareView(){
        SettingsOutputBoundary settingsPresenter = new SettingsPresenter(settingsViewModel, startViewModel, viewManager) {
            @Override
            public void prepareView() {
                assert true;
            }
        };
        this.settingsInteractor = new SettingsInteractor(settingsPresenter);
        settingsInteractor.prepareView();
    }

    @Test
    void execute() {
        SettingsOutputBoundary settingsPresenter = new SettingsPresenter(settingsViewModel, startViewModel, viewManager) {
            @Override
            public void prepareSuccessView(){
                assert UIManager.getLookAndFeel() instanceof FlatDarculaLaf;
                // check that settings DTO is created with the right variables
                try{
                    SettingsDTO settingsDTO = getPrivateVariableHelper(settingsInteractor,
                            "settingsDTO");
                    QuestionList creationSettings = settingsDTO.getCreationSettings();
                    QuestionList expectedSettings = new QuestionList(Integer.parseInt(AMOUNT), CATEGORY, DIFFICULTY, TYPE);
                    assertEquals(creationSettings.getCategory(), expectedSettings.getCategory());
                    assertEquals(creationSettings.getDifficulty(), expectedSettings.getDifficulty());
                    assertEquals(creationSettings.getType(), expectedSettings.getType());
                    assertEquals((Integer)getPrivateVariableHelper(creationSettings, "amount"),
                            getPrivateVariableHelper(expectedSettings, "amount"));
                }
                catch (IllegalAccessException | NoSuchFieldException exception){
                    exception.printStackTrace();
                }
            }
            @Override
            public void prepareFailView(String e){
                fail("Use case failure is unexpected");
            }
        };
        this.settingsInteractor = new SettingsInteractor(settingsPresenter);
        SettingsInputData settingsInputData = new SettingsInputData(AMOUNT, CATEGORY, DIFFICULTY, TYPE, GAMEMODE, DARKMODE);
        settingsInteractor.execute(settingsInputData);
    }

    @Test
    void executeIllegalAmount(){
        // test if proper behaviour for wrong amount occurs
        SettingsOutputBoundary settingsPresenter = new SettingsPresenter(settingsViewModel, startViewModel, viewManager){
            @Override
            public void prepareFailView(String error) {
                // if this method is called then behaviour is correct
                assert true;
            }
            @Override
            public void prepareSuccessView(){
                fail("Success is unexpected");
            }
        };
        this.settingsInteractor = new SettingsInteractor(settingsPresenter);
        SettingsInputData settingsInputData = new SettingsInputData("100", CATEGORY, DIFFICULTY, TYPE, GAMEMODE, DARKMODE);
        settingsInteractor.execute(settingsInputData);
    }
}