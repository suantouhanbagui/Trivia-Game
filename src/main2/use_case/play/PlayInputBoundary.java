package main2.use_case.play;

public interface PlayInputBoundary {
    void execute(PlayInputData playInputData);

    void prepareView();
}
