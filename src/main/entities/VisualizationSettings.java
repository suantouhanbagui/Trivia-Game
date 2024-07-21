package main.entities;

public class VisualizationSettings {
    private final boolean MusicEnabled;
    private final boolean darkModeEnabled;
    private final boolean showTimer;

    public VisualizationSettings(boolean musicEnabled, boolean darkModeEnabled, boolean showTimer) {
        MusicEnabled = musicEnabled;
        this.darkModeEnabled = darkModeEnabled;
        this.showTimer = showTimer;
    }

    public boolean isMusicEnabled() {return MusicEnabled;}

    public boolean isDarkModeEnabled() {return darkModeEnabled;}

    public boolean isShowTimer() {return showTimer;}
}