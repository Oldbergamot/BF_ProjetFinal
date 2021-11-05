package be.bruxellesformation.bf_projet_final.service;

import be.bruxellesformation.bf_projet_final.model.dto.AuthorDTO;
import be.bruxellesformation.bf_projet_final.model.form.author.AddAuthorForm;
import be.bruxellesformation.bf_projet_final.model.form.author.ModifyAuthorForm;
import be.bruxellesformation.bf_projet_final.model.form.book.ModifyBookForm;

public interface AuthorService {

    AuthorDTO insert(AddAuthorForm form);
    AuthorDTO modifyOne(Long id, ModifyAuthorForm form);
    AuthorDTO getOne(Long id);
    AuthorDTO displayAuthor(Long idAuthor, boolean b);
}
