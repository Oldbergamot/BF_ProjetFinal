package be.bruxellesformation.bf_projet_final.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BOOK")
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return display == book.display && Objects.equals(id, book.id) && Objects.equals(name, book.name) && Objects.equals(publishedDate, book.publishedDate) && Objects.equals(summary, book.summary) && Objects.equals(authors, book.authors) && Objects.equals(publisher, book.publisher) && Objects.equals(reviews, book.reviews) && Objects.equals(genre, book.genre) && Objects.equals(language, book.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publishedDate, summary, display, authors, publisher, reviews, genre, language);
    }
}
