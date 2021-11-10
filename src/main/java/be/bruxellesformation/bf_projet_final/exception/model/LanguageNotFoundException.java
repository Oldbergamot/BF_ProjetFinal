package be.bruxellesformation.bf_projet_final.exception.model;

public class LanguageNotFoundException extends RuntimeException{

    private Long id;

    public LanguageNotFoundException(Long id) {
        super("Language "+id+" not found");
        this.id = id;
    }
}
