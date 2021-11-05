package be.bruxellesformation.bf_projet_final.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@ToString
@EqualsAndHashCode
@Table(name = "Review")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    private short note;

    @Column(nullable = false)
    @Getter @Setter
    private String content;

    @Getter @Setter
    private LocalDate publishedDate = LocalDate.now();

    @Getter @Setter
    @Column(nullable = false)
    private boolean display = false;

    @ManyToOne
    @Getter @Setter
    private Book isAbout;

    public Review(short note, String content, LocalDate publishedDate, Book isAbout) {
        this.note = note;
        this.content = content;
        this.publishedDate = publishedDate;
        this.isAbout = isAbout;
    }
}
