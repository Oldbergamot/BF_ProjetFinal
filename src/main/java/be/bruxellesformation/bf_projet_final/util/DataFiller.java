package be.bruxellesformation.bf_projet_final.util;


import be.bruxellesformation.bf_projet_final.model.entity.*;
import be.bruxellesformation.bf_projet_final.repository.*;
import be.bruxellesformation.bf_projet_final.security.entity.Group;
import be.bruxellesformation.bf_projet_final.security.entity.Role;
import be.bruxellesformation.bf_projet_final.security.entity.User;
import be.bruxellesformation.bf_projet_final.security.repository.GroupRepository;
import be.bruxellesformation.bf_projet_final.security.repository.RoleRepository;
import be.bruxellesformation.bf_projet_final.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;

    public DataFiller(UserRepository userRepository, AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository, LanguageRepository languageRepository, PublisherRepository publisherRepository, ReviewReposiroty reviewReposiroty, UserRepository userRepository1, PasswordEncoder passwordEncoder, RoleRepository roleRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.languageRepository = languageRepository;
        this.publisherRepository = publisherRepository;
        this.reviewReposiroty = reviewReposiroty;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (userRepository.findAll().size() > 0) {
            log.info("No insert data is required");
            return;
        }

        log.info("hydratation de la DB");

        // Genre

        List<Genre> genreToInsert = List.of(
                new Genre("fantastique"),
                new Genre("policier"),
                new Genre("sci-fi"),
                new Genre("romance"),
                new Genre("avanture"),
                new Genre("guerre")

        );
        genreRepository.saveAll(genreToInsert);

        // Publisher

        List<Publisher> publisherToInsert = List.of(
                new Publisher("pinguin"),
                new Publisher("fleuve noir"),
                new Publisher("harper collins"),
                new Publisher("dupuis")
        );
        publisherRepository.saveAll(publisherToInsert);

        // Language

        List<Language> languageToInsert = List.of(
                new Language("french"),
                new Language("english"),
                new Language("klingon")
        );
        languageRepository.saveAll(languageToInsert);

        // Author

        List<Genre> genre1 = List.of(genreToInsert.get(0),
                genreToInsert.get(1));
        List<Genre> genre2 = List.of(genreToInsert.get(1),
                genreToInsert.get(2));
        List<Genre> genre3 = List.of(genreToInsert.get(2), genreToInsert.get(3));
        List<Genre> genre4 = List.of(genreToInsert.get(3), genreToInsert.get(4));

        List<Author> authorToInsert = List.of(
                new Author("Jean-Luc", "Picard", genre1),
                new Author("Ghengis", "Khan", genre2),
                new Author("Patrick", "Sebastien", genre3),
                new Author("Lao", "tseu", genre4)
        );
        authorRepository.saveAll(authorToInsert);

        //Group & roles

        Role userRole = new Role();
        Role adminRole = new Role();
        Role moderatorRole = new Role();
        userRole.setLabel("ROLE_USER");
        adminRole.setLabel("ADMIN_ROLE");
        moderatorRole.setLabel("MODERATOR_ROLE");

        Group admin = new Group();
        Group user = new Group();
        Group moderator = new Group();

        admin.setLabel("ADMIN_GROUP");
        user.setLabel("USER_GROUP");
        moderator.setLabel("MODERATOR_GROUP");

        user.setRoles(Set.of(userRole));
        moderator.setRoles(Set.of(moderatorRole, userRole));
        admin.setRoles(Set.of(userRole,moderatorRole,adminRole));

        roleRepository.saveAll(admin.getRoles());
        groupRepository.saveAll(List.of(user, moderator, admin));



        // User

        List<User> userToInsert = List.of(
                new User("LeGrandMalfaisant",
                        "LeGrandMalfaisant@hotmail.com",
                        passwordEncoder.encode("tufaisletype"),
                        null,
                        null,
                        List.of(languageToInsert.get(0)),
                        List.of(publisherToInsert.get(1)),
                        List.of(genreToInsert.get(0)),
                        List.of(authorToInsert.get(0)),
                        admin.getRoles(),
                        Set.of(admin)
                ),
                new User("Marc",
                        "plop@gmail.com",
                        passwordEncoder.encode("password"),
                        null,
                        null,
                        List.of(languageToInsert.get(0)),
                        List.of(publisherToInsert.get(0)),
                        List.of(genreToInsert.get(1)),
                        List.of(authorToInsert.get(1)),
                        user.getRoles(),
                        Set.of(user)
                ),

                new User("Mod",
                        "mod@gmail.com",
                        passwordEncoder.encode("verysafepassword"),
                        null,
                        null,
                        List.of(languageToInsert.get(0)),
                        List.of(publisherToInsert.get(0)),
                        List.of(genreToInsert.get(1)),
                        List.of(authorToInsert.get(1)),
                        moderator.getRoles(),
                        Set.of(moderator)
                        )


        );
        userRepository.saveAll(userToInsert);

        // Book

        List<Book> bookToInsert = List.of(
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
                        languageToInsert.get(2)),
                new Book("filler",
                        LocalDate.now(),
                        List.of(authorToInsert.get(3)),
                        publisherToInsert.get(2),
                        genreToInsert.get(2),
                        languageToInsert.get(2)),
                new Book("La guerre klingonne",
                        LocalDate.now(),
                        List.of(authorToInsert.get(0)),
                        publisherToInsert.get(0),
                        genreToInsert.get(2),
                        languageToInsert.get(2)),
                new Book("deepspace 8",
                        LocalDate.now(),
                        List.of(authorToInsert.get(0)),
                        publisherToInsert.get(0),
                        genreToInsert.get(2),
                        languageToInsert.get(0)),
                new Book("Ma famille étendue",
                        LocalDate.of(800,12,15),
                        List.of(authorToInsert.get(1)),
                        publisherToInsert.get(1),
                        genreToInsert.get(5),
                        languageToInsert.get(1)),
                new Book("Le Japon c'est surfait",
                        LocalDate.of(1200,11,16),
                        List.of(authorToInsert.get(1)),
                        publisherToInsert.get(2),
                        genreToInsert.get(5),
                        languageToInsert.get(1)),
                new Book("on fait tourner les serviettes",
                        LocalDate.of(1989,9,16),
                        List.of(authorToInsert.get(2)),
                        publisherToInsert.get(2),
                        genreToInsert.get(3),
                        languageToInsert.get(2)),
                new Book("Méditation sous sabot de cheval",
                        LocalDate.of(200,8,19),
                        List.of(authorToInsert.get(3)),
                        publisherToInsert.get(3),
                        genreToInsert.get(4),
                        languageToInsert.get(1))
        );
        bookRepository.saveAll(bookToInsert);

        List<Review> reviewToInsert = List.of(new Review((short) 4,
                "Very good, would recommend",
                LocalDate.now(),
                bookToInsert.get(0)));

        reviewReposiroty.saveAll(reviewToInsert);

    }
}
