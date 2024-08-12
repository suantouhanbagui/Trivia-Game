package main.adapters.start;

public class StartState {
    private String error = null;

    public void setError(String error) {
        this.error = error;
    }

    public Object getError() {
        return error;
    }
}
