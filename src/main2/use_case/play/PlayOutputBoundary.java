package main.use_case.play;

public interface PlayOutputBoundary {
    void prepareView(PlayOutputData playOutputData);

    void prepareSuccessView(PlayOutputData playOutputData);

    void prepareFailView(String error);

    String gatherName(String message);
}
