package be.bruxellesformation.bf_projet_final.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "AUTHOR")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    private String firstName;

    @Getter @Setter
    @Column(nullable = false)
    private String lastName;

    @Getter @Setter
    private String biography;

    @Getter @Setter
    @Column(nullable = false)
    private boolean display;

    @ManyToMany
    @Getter @Setter
    private List<Genre> genres;

    public Author(String firstName, String lastName, List<Genre> genres) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id)
                && Objects.equals(firstName.toLowerCase(Locale.ROOT), author.firstName.toLowerCase(Locale.ROOT))
                && Objects.equals(lastName.toLowerCase(Locale.ROOT), author.lastName.toLowerCase(Locale.ROOT));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, biography, display, genres);
    }
}
