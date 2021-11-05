package be.bruxellesformation.bf_projet_final.exceptions;

public class PublisherNotFoundException extends RuntimeException{

    private Long id;

    public PublisherNotFoundException(Long id) {
        super("Publisher "+id+" not found");
        this.id = id;
    }
}
