package be.bruxellesformation.bf_projet_final.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

@Entity
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return note == review.note
                && Objects.equals(id, review.id) && Objects.equals(content.toLowerCase(Locale.ROOT), review.content.toLowerCase(Locale.ROOT))
                && Objects.equals(publishedDate, review.publishedDate)
                && Objects.equals(isAbout, review.isAbout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, note, content, publishedDate, display, isAbout);
    }

    public boolean isDisplay() {
        return this.display;
    }
}
