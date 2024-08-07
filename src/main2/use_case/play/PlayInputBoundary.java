package main.use_case.play;

public interface PlayInputBoundary {
    void execute(PlayInputData PlayInputData);

    void prepareView();
}
