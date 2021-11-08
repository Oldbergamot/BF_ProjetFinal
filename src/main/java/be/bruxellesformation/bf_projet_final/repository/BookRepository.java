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
    List<Book> findBooksByAuthors(List<Long> id, Pageable pageable);

    List<Book> findBooksByAuthors(List<Long>id);

    List<Book> findBooksByLanguageId(Long id);

    List<Book> findBooksByLanguageId(List<Long> id);

    List<Book> findBooksByPublishedDateYear(int year);

    List<Book> findBooksByPublisherId(Long id);

    List<Book> findBooksByPublisherId(List<Long> id);

    List<Book> findBooksByGenreId(Long id);

    List<Book> findBooksByGenreId(List<Long> id);

    List<Book> findBooksByName(String name);

    List<Book> findBooksByGenre(Genre genre);

    List<Book> findAllByGenreId(List<Long> id, Pageable pageable);

    List<Book> findAllByLanguageId(List<Long> id, Pageable pageable);

    List<Book> findAllByPublisherId(List<Long> id, Pageable pageable);

    List<Book> findAllByGenreIdOrLanguageIdOrPublisherId(List<Long> idGenre, List<Long> idLanguage, List <Long> idPublisher, Pageable pageable);
}
