package be.bruxellesformation.bf_projet_final.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@ToString
@EqualsAndHashCode
@Table(name = "Review")
public class Review {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    private byte note;

    @Column(nullable = false)
    @Getter @Setter
    private String content;

    @Getter @Setter
    private LocalDate publishedDate = LocalDate.now();

    @Getter @Setter
    @Column(nullable = false)
    private boolean display = false;

    @ManyToOne
    private Book isAbout;

}
