package be.bruxellesformation.bf_projet_final.mapper;

import be.bruxellesformation.bf_projet_final.model.dto.GenreDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Genre;
import be.bruxellesformation.bf_projet_final.model.form.genre.AddGenreForm;
import be.bruxellesformation.bf_projet_final.model.form.genre.ModifyGenreForm;
import org.springframework.stereotype.Service;

@Service
public class GenreMapper implements BaseMapper<GenreDTO, AddGenreForm, Genre> {

    public GenreDTO toDto(Genre entity){
        if(entity == null) return null;
        return GenreDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public Genre toEntity(GenreDTO dto) {
        if(dto == null) return null;
        return Genre.builder()
                .id(dto.getId())
                .name((dto.getName()))
                .build();
    }

    public Genre fromFormToEntity(AddGenreForm form) {
       if (form == null)return null;
       Genre genre = new Genre();
       genre.setName(form.getName());
       return genre;
    }
}
