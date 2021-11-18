package be.bruxellesformation.bf_projet_final.exception.model;

public class ModificationException extends RuntimeException{

    private final Long id;

    public ModificationException(Long id) {
        super("Cannot modify user "+id+ " because either account is locked or expired");
        this.id = id;
    }
}
