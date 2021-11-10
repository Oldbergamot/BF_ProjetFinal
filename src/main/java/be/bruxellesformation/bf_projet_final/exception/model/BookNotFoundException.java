package be.bruxellesformation.bf_projet_final.exception.model;

public class BookNotFoundException extends RuntimeException{

    private Long id;

    public BookNotFoundException(Long id) {
        super("Book "+id+" not found");
        this.id = id;
    }
}
