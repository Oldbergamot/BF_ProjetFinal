package be.bruxellesformation.bf_projet_final.repository;

import be.bruxellesformation.bf_projet_final.model.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    @Query(value = "SELECT distinct b FROM Book b JOIN b.authors a ON a.id=?1")
    Page<Book> findBooksByAuthorsIn(List<Long> id, Pageable pageable);

    @Query("select b from Book b where b.authors in ?1")
    List<Book> findBooksByAuthorsIn(List<Long>id);

    List<Book> findBooksByAuthorsIdIn(List <Long> id);

    @Query("select b from Book b JOIN b.authors a ON a.id = ?1")
    List<Book> findBooksByAuthorsIn(Long id);

    List<Book> findBooksByLanguageId(Long id);

    List<Book> findBooksByLanguageIdIn(List<Long> id);

    @Query("select b from Book b where b.publisher.id = ?1")
    List<Book> findBooksByPublisherId(Long id);

    List<Book> findBooksByPublisherIdIn(List<Long> id);

    List<Book> findBooksByGenreId(Long id);

    List<Book> findBooksByGenreIdIn(List<Long> id);

    List<Book> findBooksByName(String name);

    Page<Book> findAllByGenreIdIn(List<Long> id, Pageable pageable);

    Page<Book> findAllByLanguageIdIn(List<Long> id, Pageable pageable);

    Page<Book> findAllByPublisherIdIn(List<Long> id, Pageable pageable);

    Page<Book> findAllByGenreIdInOrLanguageIdInOrPublisherIdIn(List<Long> idGenre, List<Long> idLanguage, List <Long> idPublisher, Pageable pageable);
}
