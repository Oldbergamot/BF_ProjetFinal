package be.bruxellesformation.bf_projet_final.model.dto;

import be.bruxellesformation.bf_projet_final.model.entity.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Setter @Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;
    private String name;
    private LocalDate publishedDate;
    private String summary;
    private List<AuthorDTO> authors;
    private Publisher publisher;
    private List<Review> reviews;
    private Genre genre;
    private Language language;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id)
                && Objects.equals(name.toLowerCase(Locale.ROOT), bookDTO.name.toLowerCase(Locale.ROOT))
                && Objects.equals(publishedDate, bookDTO.publishedDate)
                && Objects.equals(authors, bookDTO.authors)
                && Objects.equals(publisher, bookDTO.publisher)
                && Objects.equals(language, bookDTO.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publishedDate, authors, publisher, language);
    }
}
