package be.bruxellesformation.bf_projet_final.exceptions;

public class ReviewNotFoundException extends RuntimeException{
    private final Long id;


    public ReviewNotFoundException(Long id) {
        super("Review "+id+" not found");
        this.id = id;
    }
}
