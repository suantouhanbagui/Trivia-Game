package main.use_case.play;

public interface PlayInputBoundary {
    void prepareView();

    void execute(PlayInputData PlayInputData);
}