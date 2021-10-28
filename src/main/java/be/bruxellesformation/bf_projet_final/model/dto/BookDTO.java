package be.bruxellesformation.bf_projet_final.model.dto;

import be.bruxellesformation.bf_projet_final.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;
    private String name;
    private LocalDate publishedDate;
    private String summary;
    private List<Author> authors;
    private Publisher publisher;
    private List<Review> reviews;
    private Genre genre;
    private Language language;
}
