package be.bruxellesformation.bf_projet_final.repository;

import be.bruxellesformation.bf_projet_final.model.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {


    @Query(value = "SELECT distinct b FROM Book b JOIN b.authors a WHERE a.id=?1")
    List<Book> findBooksByAuthorsIn(List<Long> id, Pageable pageable);

    List<Book> findBooksByAuthorsIn(List<Long>id);

    List<Book> findBooksByLanguageId(Long id);

    List<Book> findBooksByLanguageIdIn(List<Long> id);

//    @Query("SELECT b from Book b JOIN b.publishedDate ")
//    List<Book> findBooksByPublishedDateYear(int year);

    List<Book> findBooksByPublishedDateContains(int year);

    List<Book> findBooksByPublisherId(Long id);

    List<Book> findBooksByPublisherIdIn(List<Long> id);

    List<Book> findBooksByGenreId(Long id);

    List<Book> findBooksByGenreIdIn(List<Long> id);

    List<Book> findBooksByName(String name);

    List<Book> findBooksByGenre(Genre genre);

    List<Book> findAllByGenreIdIn(List<Long> id, Pageable pageable);

    List<Book> findAllByLanguageIdIn(List<Long> id, Pageable pageable);

    List<Book> findAllByPublisherIdIn(List<Long> id, Pageable pageable);

    List<Book> findAllByGenreIdInOrLanguageIdInOrPublisherIdIn(List<Long> idGenre, List<Long> idLanguage, List <Long> idPublisher, Pageable pageable);
}
