package be.bruxellesformation.bf_projet_final.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "SECURITYUSER")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public User(String username, String email, String password, List<Book> wishToRead, List<Book> hasRead, List<Language> prefLang, List<Publisher> prefPub, List<Genre> prefGenre, List<Author> prefAuthor) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.wishToRead = wishToRead;
        this.hasRead = hasRead;
        this.prefLang = prefLang;
        this.prefPub = prefPub;
        this.prefGenre = prefGenre;
        this.prefAuthor = prefAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(username.toLowerCase(Locale.ROOT), user.username.toLowerCase(Locale.ROOT))
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, display, wishToRead, hasRead, prefLang, prefPub, prefGenre, prefAuthor);
    }
}
