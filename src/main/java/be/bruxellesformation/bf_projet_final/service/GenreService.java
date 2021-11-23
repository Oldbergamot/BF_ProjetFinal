package be.bruxellesformation.bf_projet_final.service;


import be.bruxellesformation.bf_projet_final.model.dto.AuthorDTO;
import be.bruxellesformation.bf_projet_final.model.dto.GenreDTO;
import be.bruxellesformation.bf_projet_final.model.form.genre.AddGenreForm;
import be.bruxellesformation.bf_projet_final.model.form.genre.ModifyGenreForm;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface GenreService {

    GenreDTO insert(AddGenreForm form);
    GenreDTO modifyOne(Long id, ModifyGenreForm form);
    GenreDTO getOne(Long id);
    GenreDTO displayGenre(Long idGenre, boolean b);

    List<GenreDTO> getAll();
    Page<GenreDTO> getAllWithPagination(int page, int size);

    GenreDTO deleteGenre(Long id);
}
