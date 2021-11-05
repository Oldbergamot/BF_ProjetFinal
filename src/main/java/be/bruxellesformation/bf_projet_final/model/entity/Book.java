package be.bruxellesformation.bf_projet_final.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "BOOK")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    private String name;

    @Getter @Setter
    private LocalDate publishedDate;

    @Getter @Setter
    private String summary;

    @Getter @Setter
    @Column(nullable = false)
    private boolean display;

    @Getter @Setter
    @ManyToMany(targetEntity = Author.class)
    private List<Author> authors;

    @ManyToOne()
    @Getter @Setter
    private Publisher publisher;

    @OneToMany
    @Getter @Setter
    private List<Review> reviews;

    @ManyToOne()
    @Getter @Setter
    private Genre genre;

    @ManyToOne(targetEntity = Language.class)
    @Getter @Setter
    private Language language;

    public Book(String name, LocalDate publishedDate, List<Author> authors, Publisher publisher, Genre genre, Language language) {
        this.name = name;
        this.publishedDate = publishedDate;
        this.authors = authors;
        this.publisher = publisher;
        this.genre = genre;
        this.language = language;
    }
}
