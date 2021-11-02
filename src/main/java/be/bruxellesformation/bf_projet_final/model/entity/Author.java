package be.bruxellesformation.bf_projet_final.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AUTHOR")
@EqualsAndHashCode
@ToString
@NoArgsConstructor
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
}
