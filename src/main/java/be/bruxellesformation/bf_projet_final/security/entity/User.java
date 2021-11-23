package be.bruxellesformation.bf_projet_final.security.entity;

import be.bruxellesformation.bf_projet_final.model.entity.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "SECURITYUSER")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String username;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false)
    private String password;

//    @Getter @Setter
//    @Column(nullable = false)
//    private boolean display;

    @ManyToMany
    @Getter
    @Setter
    private List<Book> wishToRead;

    @ManyToMany
    @Getter
    @Setter
    private List<Book> hasRead;

    @ManyToMany
    @Getter
    @Setter
    private List<Language> prefLang;

    @ManyToMany
    @Getter
    @Setter
    private List<Publisher> prefPub;

    @ManyToMany
    @Getter
    @Setter
    private List<Genre> prefGenre;

    @ManyToMany
    @Getter
    @Setter
    private List<Author> prefAuthor;

    @Getter
    @Setter
    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Singular
    private Set<Role> roles;

    @Getter
    @Setter
    @ManyToMany(targetEntity = Group.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Singular
    private Set<Group> groups;

    @Setter
    private boolean isAccountExpired = false;
    @Setter
    private boolean isAccountLocked = false;
    @Setter
    private boolean isCredentialsExpired = false;

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

    public User(String username, String email, String password, List<Book> wishToRead, List<Book> hasRead, List<Language> prefLang, List<Publisher> prefPub, List<Genre> prefGenre, List<Author> prefAuthor, Set<Role> roles, Set<Group> groups) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.wishToRead = wishToRead;
        this.hasRead = hasRead;
        this.prefLang = prefLang;
        this.prefPub = prefPub;
        this.prefGenre = prefGenre;
        this.prefAuthor = prefAuthor;
        this.roles = roles;
        this.groups = groups;
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
        return Objects.hash(id, username, email, password, wishToRead, hasRead, prefLang, prefPub, prefGenre, prefAuthor);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> groupAuthorities = new HashSet<>(groups);
        Set<GrantedAuthority> roleAuthorities = Stream.concat(
                groups.stream().flatMap(g -> g.getRoles().stream()),
                roles.stream()
        ).collect(Collectors.toSet());
        groupAuthorities.addAll(roleAuthorities);

        return groupAuthorities;
    }
    public boolean isAccountNonExpired() {
        return !this.isAccountExpired;
    }

    public boolean isAccountNonLocked() {
        return !this.isAccountLocked;
    }

    public boolean isCredentialsNonExpired() {
        return !this.isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
