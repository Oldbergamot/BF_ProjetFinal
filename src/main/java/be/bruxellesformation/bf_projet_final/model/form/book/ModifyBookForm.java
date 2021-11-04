package be.bruxellesformation.bf_projet_final.model.form.book;


import be.bruxellesformation.bf_projet_final.model.entity.Author;
import be.bruxellesformation.bf_projet_final.model.entity.Genre;
import be.bruxellesformation.bf_projet_final.model.entity.Language;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ModifyBookForm {

    private String name;
    private LocalDate publishedDate;
    private String summary;
    private List<Author> authors;
    private Language language;
    private Genre genre;
}
