package be.bruxellesformation.bf_projet_final.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SECURITYUSER")
@EqualsAndHashCode
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    @Column(nullable = false, unique = true)
    private String username;

    @Getter @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter @Setter
    @Column(nullable = false)
    private String password;

    @Getter @Setter
    @Column(nullable = false)
    private boolean display;

    @ManyToMany
    @Getter @Setter
    private List<Book>wishToRead;

    @ManyToMany
    @Getter @Setter
    private List<Book>hasRead;

    @ManyToMany
    @Getter @Setter
    private List<Language> prefLang;

    @ManyToMany
    @Getter @Setter
    private List<Publisher> prefPub;

    @ManyToMany
    @Getter @Setter
    private List<Genre> prefGenre;

    @ManyToMany
    @Getter @Setter
    private List<Author> prefAuthor;


}
