package be.bruxellesformation.bf_projet_final.model.dto;

import be.bruxellesformation.bf_projet_final.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private List<Book> wishToRead;
    private List<Book>hasRead;
    private List<Language> prefLang;
    private List<Publisher> prefPub;
    private List<Genre> prefGenre;
    private List<Author>prefAuthor;

}
