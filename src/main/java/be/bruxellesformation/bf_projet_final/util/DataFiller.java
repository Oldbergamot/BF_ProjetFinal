package be.bruxellesformation.bf_projet_final.util;

import be.bruxellesformation.bf_projet_final.model.dto.PublisherDTO;
import be.bruxellesformation.bf_projet_final.model.entity.*;
import be.bruxellesformation.bf_projet_final.repository.*;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataFiller implements InitializingBean {

    private final Logger log = LoggerFactory.getLogger(DataFiller.class);
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final LanguageRepository languageRepository;
    private final PublisherRepository publisherRepository;
    private final ReviewReposiroty reviewReposiroty;

    public DataFiller(UserRepository userRepository, AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository, LanguageRepository languageRepository, PublisherRepository publisherRepository, ReviewReposiroty reviewReposiroty, UserRepository userRepository1) {
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.languageRepository = languageRepository;
        this.publisherRepository = publisherRepository;
        this.reviewReposiroty = reviewReposiroty;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("hydratation de la DB");

        // Genre

        List<Genre> genreToInsert = List.of(
          new Genre("fantastique"),
          new Genre("policier"),
          new Genre("sci-fi")
        );

        // Publisher

        List<Publisher>publisherToInsert = List.of(
                new Publisher("pinguin"),
                new Publisher("fleuve noir"),
                new Publisher("harper collins"),
                new Publisher("dupuis")
        );

        // Language

        List<Language>languageToInsert = List.of(
          new Language("french"),
          new Language("english"),
          new Language("klingon")
        );

        // Author

        List<Genre>genre1 = List.of(genreToInsert.get(0),
                genreToInsert.get(1));
        List<Genre>genre2 = List.of(genreToInsert.get(1),
                genreToInsert.get(2));
        List<Genre>genre3 = List.of(genreToInsert.get(0));
        List<Genre>genre4 = List.of(genreToInsert.get(2));

        List<Author>authorToInsert = List.of(
            new Author("Jean-Luc", "Picard",genre1 ),
            new Author("Ghengis", "Khan", genre2) ,
            new Author("Patrick", "Sebastien", genre3),
            new Author("Lao", "tseu", genre4)
        );

        // User

        List<User> userToInsert = List.of(
          new User("LeGrandMalfaisant",
                  "LeGrandMalfaisant@hotmail.com",
                  "tufaisletype",
                  null,
                  null,
                  List.of(new Language("french")),
                  List.of(new Publisher("dupuis")),
                  List.of(genreToInsert.get(0)),
                  List.of(authorToInsert.get(0))),
          new User("Marc",
                  "plop@gmail.com",
                  "password",
                  null,
                  null,
                  List.of(languageToInsert.get(0)),
                  List.of(publisherToInsert.get(0)),
                  List.of(genreToInsert.get(1)),
                  List.of(authorToInsert.get(1)))
        );

        // Book

        List<Book>bookToInsert = List.of(
          new Book("L'art de la glande",
                  LocalDate.now(),
                  List.of(authorToInsert.get(0)),
                  publisherToInsert.get(0),
                  genreToInsert.get(0),
                  languageToInsert.get(0)),
          new Book("L'art de bosser",
                  LocalDate.now(),
                  List.of(authorToInsert.get(1)),
                  publisherToInsert.get(0),
                  genreToInsert.get(1),
                  languageToInsert.get(1)),
          new Book("L'art de l'entre deux",
                  LocalDate.now(),
                  List.of(authorToInsert.get(2)),
                  publisherToInsert.get(1),
                  genreToInsert.get(2),
                  languageToInsert.get(2))
        );

    }
}
