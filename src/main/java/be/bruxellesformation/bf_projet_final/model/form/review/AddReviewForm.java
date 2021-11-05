package be.bruxellesformation.bf_projet_final.model.form.review;

import be.bruxellesformation.bf_projet_final.model.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddReviewForm {

    private byte note;
    private String content;
    private Book isAbout;
}
