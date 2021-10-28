package be.bruxellesformation.bf_projet_final.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AUTHOR")
@EqualsAndHashCode
@ToString
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
    private List<Genre> genres;
}
