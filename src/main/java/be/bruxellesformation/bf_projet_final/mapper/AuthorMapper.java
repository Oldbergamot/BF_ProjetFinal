package be.bruxellesformation.bf_projet_final.mapper;

import be.bruxellesformation.bf_projet_final.model.dto.AuthorDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Author;
import be.bruxellesformation.bf_projet_final.model.form.author.AddAuthorForm;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Stream;

@Service
public class AuthorMapper implements BaseMapper <AuthorDTO, AddAuthorForm,Author>{

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
    public Stream<Author> toEntities(Collection<AuthorDTO> dtos) {
        return dtos.stream().map(this::toEntity);
    }

    public AuthorDTO toDto(Author entity) {
        if(entity == null) return null;
        return AuthorDTO.builder()
                .biography(entity.getBiography())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
//                .genres(entity.getGenres())
                .id(entity.getId())
                .build();
    }



    public Stream<AuthorDTO> toDtos(Collection<Author> entities) {
        return entities.stream().map(this::toDto);
    }

    public Author fromFormToEntity(AddAuthorForm form) {
        if(form == null) return null;
        Author author = new Author();
        author.setBiography(form.getBiography());
        author.setFirstName(form.getFirstName());
        author.setLastName(form.getLastName());
        author.setGenres(form.getGenres());
        return author;
    }
}
