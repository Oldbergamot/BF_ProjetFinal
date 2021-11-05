package be.bruxellesformation.bf_projet_final.model.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@EqualsAndHashCode
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
}
