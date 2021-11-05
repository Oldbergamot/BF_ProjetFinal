package be.bruxellesformation.bf_projet_final.model.form.review;

import be.bruxellesformation.bf_projet_final.model.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModifyReviewForm {
    private byte note;
    private String content;
    private Book isAbout;
}
