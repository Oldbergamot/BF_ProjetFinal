package be.bruxellesformation.bf_projet_final.exceptions;

public class BookNotFoundException extends RuntimeException{

    private Long id;

    public BookNotFoundException(Long id) {
        super("Book "+id+" not found");
        this.id = id;
    }
}
