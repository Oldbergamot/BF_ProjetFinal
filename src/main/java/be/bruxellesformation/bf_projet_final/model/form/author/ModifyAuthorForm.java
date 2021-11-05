package be.bruxellesformation.bf_projet_final.model.form.author;

import be.bruxellesformation.bf_projet_final.model.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ModifyAuthorForm {

    private String firstName;
    private String lastName;
    private String biography;
    private List<Genre> genres;
}
