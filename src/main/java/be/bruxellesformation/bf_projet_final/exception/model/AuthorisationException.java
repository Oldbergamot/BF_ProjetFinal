package be.bruxellesformation.bf_projet_final.exception.model;

public class AuthorisationException extends RuntimeException{

    private Long id;

    public AuthorisationException(Long id) {
        super("Authorisation denied to access "+id);
        this.id = id;
    }
}
