package be.bruxellesformation.bf_projet_final.repository;

import be.bruxellesformation.bf_projet_final.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.genre = ?1")
    List<Book> findByGenre(Genre g);

    @Query("SELECT b FROM Book b where b.authors member?a")
    List<Book> findByAuthor(Author a);

    @Query("SELECT b FROM Book b WHERE b.language = ?1")
    List<Book> findByLanguage(Language l);

    @Query("SELECT b FROM Book b WHERE Publisher = ?1")
    List<Book> findByPublisher(Publisher p);
}
