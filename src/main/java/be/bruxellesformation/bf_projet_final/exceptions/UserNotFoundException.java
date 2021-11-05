package be.bruxellesformation.bf_projet_final.exceptions;

public class UserNotFoundException extends RuntimeException{

    private Long id;

    public UserNotFoundException(Long id) {
        super("User "+id+ " not found");
        this.id = id;
    }
}
