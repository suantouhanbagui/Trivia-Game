package main.use_case.play;

public interface PlayOutputBoundary {
    void prepareView(PlayOutputData playOutputData);

    void prepareSuccessView(String message);

    void prepareFailView(String error);

    String gatherName(String message);
}
