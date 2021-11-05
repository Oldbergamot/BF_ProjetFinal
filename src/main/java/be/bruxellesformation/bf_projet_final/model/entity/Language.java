package be.bruxellesformation.bf_projet_final.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@EqualsAndHashCode
@Table(name = "LANGUAGE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Language {

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

    public Language(String name) {
        this.name = name;
    }


}
