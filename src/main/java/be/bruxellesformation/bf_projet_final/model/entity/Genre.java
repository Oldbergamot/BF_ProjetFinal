package be.bruxellesformation.bf_projet_final.model.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Locale;
import java.util.Objects;

@Entity
@ToString
@Table(name = "GENRE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    private String name;

    @Getter @Setter
    @Column(nullable = false)
    private boolean display = false;

    public Genre(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) && Objects.equals(name.toLowerCase(Locale.ROOT),
                genre.name.toLowerCase(Locale.ROOT));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, display);
    }
}

