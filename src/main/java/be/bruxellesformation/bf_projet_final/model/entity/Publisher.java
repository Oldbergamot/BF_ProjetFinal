package be.bruxellesformation.bf_projet_final.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@ToString
@Table(name = "PUBLISHER")
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    private String name;

    @Getter @Setter
    @Column(nullable = false)
    private boolean display = false;

    public Publisher(String name) {
        this.name = name;
    }
}
