package be.bruxellesformation.bf_projet_final.exceptions;

public class PreferenceNotCompletedException extends RuntimeException {

    public PreferenceNotCompletedException() {
        super("User has no preference yet");
    }
}
