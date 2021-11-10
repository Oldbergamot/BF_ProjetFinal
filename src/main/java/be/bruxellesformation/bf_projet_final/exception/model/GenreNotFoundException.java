package be.bruxellesformation.bf_projet_final.exception.model;

public class GenreNotFoundException extends RuntimeException{

    private Long id;

    public GenreNotFoundException(Long id) {
        super("Genre "+id+" not found");
        this.id = id;
    }
}
