package be.bruxellesformation.bf_projet_final.exception.model;

public class PreferenceNotCompletedException extends RuntimeException {

    public PreferenceNotCompletedException() {
        super("User has no preference yet");
    }
}
