package be.bruxellesformation.bf_projet_final.model.form.user;

import be.bruxellesformation.bf_projet_final.model.entity.Author;
import be.bruxellesformation.bf_projet_final.model.entity.Genre;
import be.bruxellesformation.bf_projet_final.model.entity.Language;
import be.bruxellesformation.bf_projet_final.model.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserAddPrefForm {
    private List<Language> prefLang;
    private List<Publisher>prefPub;
    private List<Genre> prefGenre;
    private List<Author> prefAuthor;
}
