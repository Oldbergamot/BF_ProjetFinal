package be.bruxellesformation.bf_projet_final.model.dto;

import be.bruxellesformation.bf_projet_final.model.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long id;
    private short note;
    private String content;
    private LocalDate publishedDate;
    private Book isAbout;
}
