package be.bruxellesformation.bf_projet_final.mapper;

import be.bruxellesformation.bf_projet_final.model.dto.AuthorDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Author;
import be.bruxellesformation.bf_projet_final.model.form.author.AddAuthorForm;
import org.springframework.stereotype.Service;

@Service
public class AuthorMapper {

    public Author toEntity(AuthorDTO dto) {
        if (dto == null) return null;
        return Author.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .biography(dto.getBiography())
                .genres(dto.getGenres())
                .build();
    }

    public AuthorDTO toDto(Author entity) {
        if(entity == null) return null;
        return AuthorDTO.builder()
                .biography(entity.getBiography())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .genres(entity.getGenres())
                .id(entity.getId())
                .build();
    }

    public Author fromAddAuthorFormToEntity(AddAuthorForm form) {
        if(form == null) return null;
        Author author = new Author();
        author.setBiography(form.getBiography());
        author.setFirstName(form.getFirstName());
        author.setLastName(form.getLastName());
        author.setGenres(form.getGenres());
        return author;
    }

    public Author fromModifyAuthorFormToEntity(AddAuthorForm form) {
        if(form == null) return null;
        Author author = new Author();
        author.setBiography(form.getBiography());
        author.setFirstName(form.getFirstName());
        author.setLastName(form.getLastName());
        author.setGenres(form.getGenres());
        return author;
    }
}
