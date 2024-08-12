package main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import main.adapters.play.PlayController;
import main.adapters.play.PlayPresenter;
import main.adapters.play.PlayViewModel;
import main.adapters.results.ResultsController;
import main.adapters.results.ResultsPresenter;
import main.adapters.results.ResultsViewModel;
import main.adapters.settings.SettingsController;
import main.adapters.settings.SettingsPresenter;
import main.adapters.settings.SettingsViewModel;
import main.adapters.start.StartViewModel;
import main.adapters.two_player.TwoPlayerPresenter;
import main.adapters.two_player.TwoPlayerViewModel;
import main.data_access.ResultRecordingDAO;
import main.data_access.ResultRetrievalDAO;
import main.data_access.TriviaDB;
import main.data_access.TriviaDBInterface;
import main.use_case.play.OnePlayerInteractor;
import main.use_case.play.PlayOutputBoundary;
import main.use_case.results.ResultsInteractor;
import main.use_case.results.ResultsOutputBoundary;
import main.use_case.settings.SettingsInteractor;
import main.use_case.settings.SettingsOutputBoundary;
import main.use_case.two_player.PlayInteractorAdapter;
import main.use_case.two_player.TwoPlayerInteractor;
import main.view.*;

import javax.swing.*;

public class Main2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Trivia Quiz");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ViewManager manager = new ViewManager(frame);

        FlatIntelliJLaf.setup();
        FlatIntelliJLaf.updateUI();

        // instantiate view models
        SettingsViewModel settingsViewModel = new SettingsViewModel();
        ResultsViewModel resultsViewModel = new ResultsViewModel();
        PlayViewModel playViewModel = new PlayViewModel();
        TwoPlayerViewModel twoPlayerViewModel = new TwoPlayerViewModel();
        StartViewModel startViewModel = new StartViewModel();

        // settings
        SettingsOutputBoundary settingsPresenter = new SettingsPresenter(settingsViewModel,
                startViewModel,
                manager);
        SettingsInteractor settingsInteractor = new SettingsInteractor(settingsPresenter);
        SettingsController settingsController = new SettingsController(settingsInteractor);
        SettingsView settingsView = new SettingsView(settingsController, settingsViewModel);

        // results
        ResultsOutputBoundary resultsPresenter = new ResultsPresenter(resultsViewModel,
                startViewModel,
                manager);
        ResultRetrievalDAO resultRetrievalDAO = new ResultRetrievalDAO();
        ResultsInteractor resultsInteractor = new ResultsInteractor(resultsPresenter, resultRetrievalDAO);
        ResultsController resultsController = new ResultsController(resultsInteractor);
        ResultsView resultsView = new ResultsView(resultsController, resultsViewModel);

        // play
        PlayOutputBoundary playPresenter = new PlayPresenter(playViewModel,
                startViewModel,
                manager);
        TriviaDBInterface questionGenerator = new TriviaDB();
        ResultRecordingDAO resultRecordingDAO = new ResultRecordingDAO();
        OnePlayerInteractor playInteractor = new OnePlayerInteractor(playPresenter,
                settingsInteractor,
                questionGenerator,
                resultRecordingDAO);
        // two player
        PlayOutputBoundary twoPlayerPresenter = new TwoPlayerPresenter(twoPlayerViewModel,
                startViewModel,
                manager);
        TwoPlayerInteractor twoPlayerInteractor = new TwoPlayerInteractor(twoPlayerPresenter,
                settingsInteractor,
                questionGenerator,
                resultRecordingDAO);
        // make an adapter for the two play use cases and controller for said adapter
        PlayInteractorAdapter adapter = new PlayInteractorAdapter(playInteractor,
                twoPlayerInteractor,
                settingsInteractor);
        PlayController playController = new PlayController(adapter);
        // create views for the play use cases
        PlayView playView = new PlayView(playController, playViewModel);
        TwoPlayerView twoPlayerView = new TwoPlayerView(playController, twoPlayerViewModel);

        // start view
        StartView startView = new StartView(settingsController,
                resultsController,
                playController,
                startViewModel);

        // add everything to the view manager
        manager.addView(settingsView, settingsViewModel);
        manager.addView(resultsView, resultsViewModel);
        manager.addView(playView, playViewModel);
        manager.addView(twoPlayerView, twoPlayerViewModel);
        manager.addView(startView, startViewModel);

        manager.setActiveView(startViewModel.getViewName());
        frame.setVisible(true);
        AdditionalFeatureMusic.playSound("\\z_additional_features\\adventure_game_music.wav");
    }
}
