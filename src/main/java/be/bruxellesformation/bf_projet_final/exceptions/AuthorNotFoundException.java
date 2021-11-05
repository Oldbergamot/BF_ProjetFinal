package be.bruxellesformation.bf_projet_final.exceptions;

public class AuthorNotFoundException extends RuntimeException{

    private Long id;

    public AuthorNotFoundException(Long id) {
        super("Author "+id+" not found");
        this.id = id;
    }
}
